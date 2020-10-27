package com.jt.controller;

import com.jt.Service.UserService;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("findAll")
    public String findAll(Model model){
        List<User>userList=userService.findAll();
        model.addAttribute("userList",userList);
        System.out.println(userList);
       return "userList";
    }
    @RequestMapping("ajax")
    public String Ajax(){
        return "ajax";
    }
}
