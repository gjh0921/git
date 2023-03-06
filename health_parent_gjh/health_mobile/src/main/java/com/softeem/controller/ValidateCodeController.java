package com.softeem.controller;

import com.aliyuncs.exceptions.ClientException;
import com.softeem.constant.MessageConstant;
import com.softeem.constant.RedisMessageConstant;
import com.softeem.entity.Result;
import com.softeem.utils.SMSUtils;
import com.softeem.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            SMSUtils.sendShortMessage("908e94ccf08b4476ba6c876d13f084ad",telephone,code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis           18073665234 001,3分钟,验证码
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,5 * 60,code.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        try {
            //发送短信
            SMSUtils.sendShortMessage("908e94ccf08b4476ba6c876d13f084ad",telephone,code.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,
                5 * 60,
                code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


}
