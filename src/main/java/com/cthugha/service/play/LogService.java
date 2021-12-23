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
    public Log updateByID(Log log) {
        logMapper.updateByPrimaryKeySelective(log);
        return null;
    }

    @Override
    public List<Log> listByDramaId(Integer id) {
        return logMapper.selectByDramaId(id);
    }

    @Override
    public void positionUp(Integer id) {
        Log current = logMapper.selectByPrimaryKey(id);
        current.setId(id);
        List<Log> logs = listByDramaId(current.getDramaId());
        int index = 0;
            for (int i = 0; i < logs.size(); i++) {
                if (current.getId() == logs.get(i).getId()) {
                    index = i;
                    break;
                }
            }
        if (index > 0) {
            System.out.println("++++++");
            Log target = logMapper.selectByPrimaryKey(logs.get(index - 1).getId());
            int temp = current.getPosition();
            current.setPosition(target.getPosition());
            target.setPosition(temp);
            logMapper.updateByPrimaryKey(current);
            logMapper.updateByPrimaryKey(target);
        }
        else
            return;
    }

    @Override
    public void positionDown(Integer id) {
        Log current = logMapper.selectByPrimaryKey(id);
        current.setId(id);
        List<Log> logs = listByDramaId(current.getDramaId());
        int index = 0;
        for (int i = 0; i < logs.size(); i++) {
            if (current.getId() == logs.get(i).getId()) {
                index = i;
                break;
            }
        }
        if (index < logs.size() - 1) {
            System.out.println("++++++");
            Log target = logMapper.selectByPrimaryKey(logs.get(index + 1).getId());
            int temp = current.getPosition();
            current.setPosition(target.getPosition());
            target.setPosition(temp);
            logMapper.updateByPrimaryKey(current);
            logMapper.updateByPrimaryKey(target);
        }
        else
            return;
    }
}
