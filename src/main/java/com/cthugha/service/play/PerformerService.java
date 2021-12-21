package com.cthugha.service.play;

import com.cthugha.dao.play.PerformerMapper;
import com.cthugha.model.play.Drama;
import com.cthugha.model.play.Performer;
import com.cthugha.model.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname PerformerService
 * @Description 剧本和用户的关系，业务逻辑实现
 * @Date 2021/12/21 16:10
 * @Created by gaoqi
 */
@Service
public class PerformerService implements IPerformerService{
    final
    PerformerMapper performerMapper;

    public PerformerService(PerformerMapper performerMapper) {
        this.performerMapper = performerMapper;
    }

    @Override
    public int add(Drama drama, User user, Integer position) {
        Performer performer = new Performer();
        performer.setDramaId(drama.getId());
        performer.setUserId(user.getId());
        performer.setPosition(position);
        performerMapper.insert(performer);
        return 0;
    }

    @Override
    public int add(Performer performer) {
        performerMapper.insert(performer);
        return 0;
    }

    @Override
    public List<Drama> listByUserId(Integer id) {
        return performerMapper.selectAllDrama(id);
    }
}
