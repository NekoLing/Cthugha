package com.cthugha.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname IndexController
 * @Description 首页相关的页面跳转
 * @Date 2021/12/20 23:41
 * @Created by gaoqi
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/test")
    public String test() {
        return "chat-input";
    }
}