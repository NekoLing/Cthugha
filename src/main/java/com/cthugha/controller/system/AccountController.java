package com.cthugha.controller.system;

import com.cthugha.model.system.Account;
import com.cthugha.model.system.User;
import com.cthugha.model.system.UserInfo;
import com.cthugha.service.system.IAccountService;
import com.cthugha.service.system.IUserService;
import com.cthugha.util.JsonResponse;
import com.cthugha.util.JwtUitls;
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
    final JwtUitls jwtUitls;

    public AccountController(IAccountService accountService, IUserService userService, JwtUitls jwtUitls) {
        this.accountService = accountService;
        this.userService = userService;
        this.jwtUitls = jwtUitls;
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

    @PostMapping("/check")
    public JsonResponse check(@RequestBody Account inputAccount) {
        UserInfo userInfo = new UserInfo();
        Account saveAccount = accountService.getByNumber(inputAccount.getNumber());

        //生成token
        if (saveAccount != null) {
            userInfo.setAccountId(saveAccount.getId());
            userInfo.setNumber(saveAccount.getNumber());
            userInfo.setEmail(saveAccount.getEmail());
            userInfo.setPassword(saveAccount.getPassword());

            User user = userService.getByNumber(saveAccount.getNumber());
            userInfo.setUserId(user.getId());
            userInfo.setName(user.getName());
            userInfo.setToken(jwtUitls.createToken(saveAccount.getId().toString(), saveAccount.getNumber()));
        }

        if (saveAccount == null)
            return new JsonResponse(1, "用户不存在");
        else if (!saveAccount.getPassword().equals(inputAccount.getPassword()))
            return new JsonResponse(1, "密码错误");
        else
            return new JsonResponse(userInfo, "登录成功");
    }
}