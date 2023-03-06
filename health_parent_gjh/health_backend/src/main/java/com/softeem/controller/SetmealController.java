package com.softeem.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.softeem.constant.MessageConstant;
import com.softeem.constant.RedisConstant;
import com.softeem.entity.PageResult;
import com.softeem.entity.QueryPageBean;
import com.softeem.entity.Result;
import com.softeem.pojo.CheckGroup;
import com.softeem.pojo.Setmeal;
import com.softeem.service.CheckGroupService;
import com.softeem.service.SetmealService;
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
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetmealService setmealService;
    @Reference
    private CheckGroupService checkGroupService;
    @RequestMapping("/generateHtml")
    private Result generateHtml(){
        try {
            setmealService.generateMobileStaticHtml();
            return new Result(true,"静态页面生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"静态页面生成失败");
        }
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
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        System.out.println("checkGroupList = " + checkGroupList);
        if(checkGroupList != null && checkGroupList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
            result.setData(checkGroupList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            setmealService.saveOrUpdate(setmeal,checkgroupIds);
            return new Result(true,"套餐操作成功");
        }catch (Exception e){
            //新增套餐失败
            return new Result(false,"套餐操作失败");
        }
        //新增套餐成功
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            setmealService.delete(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
    @RequestMapping("/findById")
    public  Result findById(Integer id){
        try {
            Map map=setmealService.findById(id);
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_SETMEAL_SUCCESS);
        }
    }
}
