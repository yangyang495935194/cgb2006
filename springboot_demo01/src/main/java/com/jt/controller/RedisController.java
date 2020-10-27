package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class RedisController {
    @Value("${redis.host}")
      private String host;
    @Value("${redis.port}")
    private  String post;
    @Value("${pro.redis.host}")
    private String proHost;
    @Value("${pro.redis.port}")
    private String proPort;

    @RequestMapping("/getMsg")
    public String getMsg(){
        return host+","+post;
    }
    @RequestMapping("/getPro")
    public String getPro(){
        return proHost+","+proPort;
    }
}
