package com.cthugha.service.play;

import com.cthugha.dao.play.DramaMapper;
import com.cthugha.dao.play.PerformerMapper;
import com.cthugha.model.play.Drama;
import com.cthugha.model.system.User;
import org.springframework.stereotype.Service;

/**
 * @Classname DramaService
 * @Description 剧本的业务逻辑实现
 * @Date 2021/12/21 15:27
 * @Created by gaoqi
 */
@Service
public class DramaService implements IDramaService{
    final DramaMapper dramaMapper;
    final IPerformerService performerService;

    public DramaService(DramaMapper dramaMapper, IPerformerService performerService) {
        this.dramaMapper = dramaMapper;
        this.performerService = performerService;
    }

    @Override
    public int add(Drama drama, User createUser) {
        drama.setOwnerId(createUser.getId());
        dramaMapper.insert(drama);
        performerService.add(drama, createUser, 0);
        return 0;
    }

    @Override
    public Drama getById(Integer id) {
        return dramaMapper.selectByPrimaryKey(id);
    }
}
