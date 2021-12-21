package com.cthugha.service.system;

import com.cthugha.model.system.User;
import com.cthugha.model.system.UserInfo;

/**
 * @Classname IUserService
 * @Description 用户的业务逻辑接口
 * @Date 2021/12/21 14:44
 * @Created by gaoqi
 */
public interface IUserService {

    User getByNumber(String number);

    int addUser(UserInfo userInfo);

}
