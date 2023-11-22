package com.btp.rwj.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

public class Md5Util {
    @Value("${salt}")
    private static String salt;

    public static String md5pwd(String pwd) {
        return DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
    }
}
