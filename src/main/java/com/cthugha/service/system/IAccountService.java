package com.cthugha.service.system;

import com.cthugha.model.system.Account;

/**
 * @Interfacename IAccountService
 * @Description 用户账户的业务逻辑接口
 * @Date 2021/12/20 23:02
 * @Created by gaoqi
 */
public interface IAccountService {
    Account selectById(Integer id);

    Account selectByNumber(String account);

    int insert(Account account);
}
