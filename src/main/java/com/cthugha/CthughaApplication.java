package com.cthugha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com/cthugha/filter")
@SpringBootApplication
public class CthughaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CthughaApplication.class, args);
    }

}