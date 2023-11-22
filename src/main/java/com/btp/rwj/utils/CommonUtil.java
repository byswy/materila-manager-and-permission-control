package com.btp.rwj.utils;

import java.util.List;

public class CommonUtil {
    public static String List2String(List<String> list) {
        String s = list.toString();
        return s.substring(1, s.length() - 1);
    }

    public static String[] String2array(String s) {
        return s.split(", ");
    }
}
