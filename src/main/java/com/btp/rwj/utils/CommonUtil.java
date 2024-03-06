package com.btp.rwj.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    public static <T> List<T> Object2list(Object obj, Class<T> cla) {
        List<T> list = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }
}
