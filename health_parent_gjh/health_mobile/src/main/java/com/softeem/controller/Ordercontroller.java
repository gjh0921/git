package com.softeem.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.softeem.constant.MessageConstant;
import com.softeem.constant.RedisMessageConstant;
import com.softeem.entity.Result;
import com.softeem.pojo.Order;
import com.softeem.service.OrderService;
import com.softeem.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class Ordercontroller {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/submit")
    public Result submitOder(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        //从Redis中获取缓存的验证码，key为手机号+RedisConstant.SENDTYPE_ORDER
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //校验手机验证码
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result =null;
        //调用体检预约服务
        try{
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        }catch (Exception e){
            e.printStackTrace();
            //预约失败
            return new Result(false,"预约失败！");
        }
        if(result.isFlag()){
            //预约成功，发送短信通知
            String orderDate = (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage("908e94ccf08b4476ba6c876d13f084ad",telephone,"6666");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
