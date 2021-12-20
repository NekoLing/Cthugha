package com.cthugha.dao.system;

import com.cthugha.model.system.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    Account selectByNumber(String number);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}