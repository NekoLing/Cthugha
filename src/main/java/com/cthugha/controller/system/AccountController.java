package com.cthugha.controller.system;

import com.cthugha.model.system.Account;
import com.cthugha.service.system.IAccountService;
import com.cthugha.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    final
    IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public JsonResponse create(@RequestBody Account account) {
        if (account == null)
            return new JsonResponse(2, "账号为null");
        else if (accountService.selectByNumber(account.getNumber()) != null)
            return new JsonResponse(1, "账号已存在，请直接登录");
        else {
            accountService.insert(account);
            return new JsonResponse(0,"账号创建成功");
        }
    }
}