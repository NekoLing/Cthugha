package com.cthugha.model.system;

import lombok.Data;

/**
 * @Classname Account
 * @Description 用户账户的模型类
 * @Date 2021/12/20 22:33
 * @Created by gaoqi
 */
@Data
public class Account {

    private Integer id;

    private String account;

    private String email;

    private String password;

}
