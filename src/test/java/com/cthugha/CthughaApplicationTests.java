package com.cthugha;

import com.cthugha.service.system.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CthughaApplicationTests {
    @Autowired
    IAccountService service;

    @Test
    void contextLoads() {
        System.out.println(service.getById(1));
    }

}
