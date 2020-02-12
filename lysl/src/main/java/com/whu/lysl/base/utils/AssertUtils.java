package com.whu.lysl.base.utils;



import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 断言工具
 * @author Visionary
 * @since 2019/8/28 11:59 PM
 */
public class AssertUtils {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AssertUtils.class);

    /**
     * 对象不为空
     * @param object 对象
     */
    public static void AssertNotNull(Object object) {
        if(object == null) {
            logger.error("empty object");
            throw new LYSLException("对象为空", LYSLResultCodeEnum.OBJECT_NULL);
        }
    }

    /**
     * 字符串不为空
     * @param s 字符串
     */
    public static void StringNotEmpty(String s) {
        if(!StringUtils.isNotEmpty(s)) {
            logger.error("empty string");
            throw new LYSLException("字符串为空", LYSLResultCodeEnum.DATA_INVALID);
        }
    }

    /**
     * 对象不为空
     * @param object 对象
//     * @param message
     */
    public static void AssertNotNullWithMessage(Object object, String message) {
        if(object == null) {
            logger.error("empty object");
            throw new LYSLException(message, LYSLResultCodeEnum.OBJECT_NULL);
        }
    }

}
