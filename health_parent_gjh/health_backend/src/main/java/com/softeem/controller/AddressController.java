package com.softeem.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.softeem.constant.MessageConstant;
import com.softeem.constant.RedisConstant;
import com.softeem.entity.PageResult;
import com.softeem.entity.QueryPageBean;
import com.softeem.entity.Result;
import com.softeem.pojo.Address;
import com.softeem.service.AddressService;
import com.softeem.utils.AliyunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private AddressService addressService;
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Address> addressList =addressService.findAll();
            return new Result(true,"",addressList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"地图加载失败");
        }
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = addressService.findPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try {
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            AliyunUtils.upload2aliyun(imgFile.getBytes(),fileName);
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    //将图片名称保存到Redis
    private void savePic2Redis(String pic){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Address address){
        try {
            addressService.add(address);
            savePic2Redis(address.getImg());
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/findUpdate")
    public Result findUpdate(Integer id){
        try {
            Map map = addressService.findUpdate(id);
            return new Result(true,"成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody Address address){
        try {
            addressService.edit(address);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            addressService.delete(id);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
