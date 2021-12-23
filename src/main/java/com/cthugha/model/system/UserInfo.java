package com.cthugha.model.system;

import lombok.Data;

/**
 * @Classname UserInfo
 * @Description 用户相关的全部信息
 * @Date 2021/12/21 14:51
 * @Created by gaoqi
 */
@Data
public class UserInfo {

    private Integer accountId;

    private String number;

    private String email;

    private String password;

    private Integer userId;

    private String name;

    private String token;

    public UserInfo() {
    }

    public UserInfo(Integer accountId, String number, String email, String password, Integer userId, String name, String token) {
        this.accountId = accountId;
        this.number = number;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.name = name;
        this.token = token;
    }

    public UserInfo(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.number = user.getNumber();
    }
}
