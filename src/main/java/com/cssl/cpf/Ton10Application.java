package com.cssl.cpf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.cssl.cpf.mapper")
@SpringBootApplication
public class Ton10Application {

    public static void main(String[] args) {
        SpringApplication.run(Ton10Application.class, args);
    }

}
