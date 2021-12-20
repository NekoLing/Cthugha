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
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
