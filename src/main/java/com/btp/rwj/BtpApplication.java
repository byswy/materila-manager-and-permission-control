package com.btp.rwj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.btp.rwj.mapper")
public class BtpApplication {
    public static void main(String[] args) {
        SpringApplication.run(BtpApplication.class, args);
    }

}
