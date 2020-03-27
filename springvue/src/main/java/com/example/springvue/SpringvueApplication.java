package com.example.springvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springvue.Dao")
public class SpringvueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringvueApplication.class, args);
    }

}
