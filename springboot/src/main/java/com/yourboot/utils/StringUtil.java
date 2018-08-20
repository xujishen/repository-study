package com.yourboot.utils;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {

    public static boolean areNotBlank(Object ... objs) {
        if (objs == null || objs.length == 0) {
            return false;
        }

        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
            String s = String.valueOf(obj);
            if (isBlank(s)) {
                return false;
            }
        }
        return true;
    }

}
