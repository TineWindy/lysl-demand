package com.whu.lysl.base.utils;


/**
 * 字符串工具
 * @author Visionary
 * @since 2019/8/19 4:49 PM
 */
public class StringUtils {

    /**
     * 两字符串进行比较
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 是否相同
     */
    public static boolean equal(String s1, String s2) {
        if(s1 == null || s2 == null) {
            return false;
        }
        return s1.equals(s2);
    }

    /**
     * 判断字符串是否不为空
     * @param s 目标字符串
     */
    public static boolean isNotEmpty(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        return true;
    }


}
