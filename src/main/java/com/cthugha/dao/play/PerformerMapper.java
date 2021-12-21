package com.cthugha.dao.play;

import com.cthugha.model.play.Drama;
import com.cthugha.model.play.Performer;
import com.cthugha.model.play.PerformerKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PerformerMapper {
    int deleteByPrimaryKey(PerformerKey key);

    int insert(Performer record);

    int insertSelective(Performer record);

    Performer selectByPrimaryKey(PerformerKey key);

    int updateByPrimaryKeySelective(Performer record);

    int updateByPrimaryKey(Performer record);

    List<Drama> selectAllDrama(Integer userId);
}