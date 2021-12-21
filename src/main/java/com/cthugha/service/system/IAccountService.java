package com.cthugha.service.system;

import com.cthugha.model.system.Account;

/**
 * @Interfacename IAccountService
 * @Description 用户账户的业务逻辑接口
 * @Date 2021/12/20 23:02
 * @Created by gaoqi
 */
public interface IAccountService {

    /** 通过id获取账户 */
    Account getById(Integer id);

    /** 通过账号获取账户 */
    Account getByNumber(String number);

    /** 创建账户 */
    int add(Account account);
}
