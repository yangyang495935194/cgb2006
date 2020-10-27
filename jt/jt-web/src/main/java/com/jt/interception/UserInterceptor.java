package com.jt.interception;

import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class UserInterceptor implements HandlerInterceptor {
    /**
     * 需求：拦截cart开头的所有的请求，并校验用户是否登录 用preHandle 在处理之前拦截请求
     * 如何判断用户是否登录：1，检查cookie 2.redis有没记录
     * true请求应该放行， false请求应该拦截，返回登录页面重定向跳转到登录页面，使程序流转起来
     */
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        String ticket = CookieUtil.getCookieValue(request, "JT-Ticket");
        //检验ticket
        if (StringUtils.isEmpty(ticket)) {
            //判断redis中是否有值
            if (jedisCluster.exists(ticket)) {
                //动态获取jeson串信息
                String userJSON = jedisCluster.get(ticket);
                User user = ObjectMapperUtil.toObj(userJSON, User.class);
                request.setAttribute("JT-USER", user);

                return true;
            }
        }
        response.sendRedirect("/user/login.html");
        return false;
    }
    //销毁对象
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        request.removeAttribute("JT-USER");
    }
}