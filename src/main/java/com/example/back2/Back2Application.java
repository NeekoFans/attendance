package com.example.back2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//声明mybatis的执行入口dao
@MapperScan({"com.example.back2.dao"})
@SpringBootApplication
public class Back2Application {

    public static void main(String[] args) {
        SpringApplication.run(Back2Application.class, args);
    }

}
