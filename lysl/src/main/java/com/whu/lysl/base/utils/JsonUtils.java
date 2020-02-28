package com.whu.lysl.base.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Author Caspar
 * @CreateTime 2020/2/28 16:30
 * @Description:
 */
public class JsonUtils {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

}
