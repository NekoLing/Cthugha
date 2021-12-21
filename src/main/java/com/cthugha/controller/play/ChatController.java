package com.cthugha.controller.play;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname ChatController
 * @Description
 * @Date 2021/12/21 22:02
 * @Created by gaoqi
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    @GetMapping
    public String chat() {
        return "chat";
    }
}