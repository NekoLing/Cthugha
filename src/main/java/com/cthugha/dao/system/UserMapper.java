package com.cthugha.dao.system;

import com.cthugha.model.system.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByNumber(String number);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}