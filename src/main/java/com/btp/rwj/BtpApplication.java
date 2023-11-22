package com.btp.rwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BtpApplication {
    public static void main(String[] args) {
        SpringApplication.run(BtpApplication.class, args);
    }

}
