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
}
