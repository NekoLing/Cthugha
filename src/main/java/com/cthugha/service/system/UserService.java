package com.cthugha.service.system;

import com.cthugha.dao.system.UserMapper;
import com.cthugha.model.system.Account;
import com.cthugha.model.system.User;
import com.cthugha.model.system.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserService
 * @Description 用户的业务逻辑实现
 * @Date 2021/12/21 14:48
 * @Created by gaoqi
 */

@Service
public class UserService implements IUserService{
    final
    UserMapper userMapper;
    final
    IAccountService accountService;

    public UserService(UserMapper userMapper, IAccountService accountService) {
        this.userMapper = userMapper;
        this.accountService = accountService;
    }

    @Override
    public User getByNumber(String number) {
        return userMapper.selectByNumber(number);
    }

    @Override
    public int addUser(UserInfo userInfo) {
        User user = new User();
        user.setName(userInfo.getName());
        user.setNumber(userInfo.getNumber());
        userMapper.insert(user);

        Account account = new Account();
        account.setNumber(userInfo.getNumber());
        account.setEmail(userInfo.getNumber());
        account.setPassword(userInfo.getPassword());
        accountService.add(account);
        return 0;
    }
}
