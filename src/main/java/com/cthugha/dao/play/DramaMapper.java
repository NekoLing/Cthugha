package com.cthugha.dao.play;

import com.cthugha.model.play.Drama;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DramaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Drama record);

    int insertSelective(Drama record);

    Drama selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Drama record);

    int updateByPrimaryKey(Drama record);
}