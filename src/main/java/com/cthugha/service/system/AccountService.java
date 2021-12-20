package com.cthugha.service.system;

import com.cthugha.dao.system.AccountMapper;
import com.cthugha.model.system.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname AccountService
 * @Description 用户账户的业务逻辑实现
 * @Date 2021/12/20 23:02
 * @Created by gaoqi
 */
@Service
public class AccountService implements IAccountService{
    final
    AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account selectById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public Account selectByNumber(String number) {
        return accountMapper.selectByNumber(number);
    }

    @Override
    public int insert(Account account) {
       return accountMapper.insert(account);
    }
}
