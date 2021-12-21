package com.cthugha;

import com.cthugha.dao.play.PerformerMapper;
import com.cthugha.model.play.Drama;
import com.cthugha.model.system.Account;
import com.cthugha.model.system.User;
import com.cthugha.service.play.IDramaService;
import com.cthugha.service.system.IAccountService;
import com.cthugha.service.system.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CthughaApplicationTests {
    @Autowired
    IAccountService service;
    @Autowired
    IDramaService dramaService;
    @Autowired
    IUserService userService;
    @Autowired
    PerformerMapper performerMapper;

    @Test
    void contextLoads() {
        List<Drama> dramas = performerMapper.selectAllDrama(3);
        System.out.println(dramas);
    }

}
