package com.cthugha.controller.system;

import com.cthugha.model.system.UserInfo;
import com.cthugha.service.system.IAccountService;
import com.cthugha.service.system.IUserService;
import com.cthugha.util.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AccountController
 * @Description 用户账户的控制类
 * @Date 2021/12/20 23:03
 * @Created by gaoqi
 */

@RestController
@RequestMapping("/account")
public class AccountController {
    final IAccountService accountService;
    final IUserService userService;

    public AccountController(IAccountService accountService, IUserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public JsonResponse create(@RequestBody UserInfo userInfo) {
        if (userInfo == null)
            return new JsonResponse(2, "账户信息为null");
        else if (accountService.getByNumber(userInfo.getNumber()) != null)
            return new JsonResponse(1, "账号已存在，请直接登录");
        else {
            userService.addUser(userInfo);
            return new JsonResponse(0,"账号创建成功");
        }
    }
}