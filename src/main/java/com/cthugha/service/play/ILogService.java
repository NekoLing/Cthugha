package com.cthugha.service.play;

import com.cthugha.model.play.Log;

import java.util.List;

/**
 * @Classname ILogService
 * @Description
 * @Date 2021/12/21 21:34
 * @Created by gaoqi
 */
public interface ILogService {
    int add(Log log);

    Log updateByID(Log log);

    List<Log> listByDramaId(Integer id);

    void positionUp(Integer id);

    void positionDown(Integer id);
}
