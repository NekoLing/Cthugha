package com.cthugha.service.play;

import com.cthugha.model.play.Drama;
import com.cthugha.model.play.Performer;
import com.cthugha.model.system.User;

import java.util.List;

/**
 * @Interfacename IPerformerService
 * @Description 剧本和用户的关系，业务逻辑接口
 * @Date 2021/12/21 16:08
 * @Created by gaoqi
 */
public interface IPerformerService {

    int add(Drama drama, User user, Integer position);

    int add(Performer performer);

    List<Drama> listByUserId(Integer id);
}
