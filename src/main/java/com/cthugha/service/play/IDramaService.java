package com.cthugha.service.play;

import com.cthugha.model.play.Drama;
import com.cthugha.model.system.User;

/**
 * @Classname IDramaService
 * @Description 剧本的业务逻辑接口
 * @Date 2021/12/21 15:25
 * @Created by gaoqi
 */
public interface IDramaService {
    
    int add(Drama drama, User createUser);

    Drama getById(Integer id);
}
