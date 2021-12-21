package com.cthugha.service.play;

import com.cthugha.dao.play.LogMapper;
import com.cthugha.model.play.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname LogService
 * @Description
 * @Date 2021/12/21 21:34
 * @Created by gaoqi
 */
@Service
public class LogService implements ILogService{
    final LogMapper logMapper;

    public LogService(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public int add(Log log) {
        logMapper.insert(log);
        return 0;
    }

    @Override
    public List<Log> listByDramaId(Integer id) {
        return logMapper.selectByDramaId(id);
    }
}
