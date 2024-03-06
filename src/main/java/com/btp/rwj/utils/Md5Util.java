package com.btp.rwj.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5Util {
    private static String salt;

    @Value("${static.md5.salt}")
    private void setSalt(String salt) {
        Md5Util.salt = salt;
    }

    public static String md5pwd(String pwd) {
        return DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
    }
}
