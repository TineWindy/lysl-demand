package com.whu.lysl.base.utils;

import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类,用于字符串与Date类型转换
 * 
 * @author flm
 * @since 2019/8/23 15:39
 */

public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private  static final SimpleDateFormat dateFormatWithSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取合适的格式日期类
     * @param withSeconds bool
     * @return simpleDate
     */
    public static SimpleDateFormat getDateFormat(boolean withSeconds) {
        if (withSeconds) {
            return dateFormatWithSeconds;
        } else {
            return dateFormat;
        }
    }

    /**
     * 字符串转时间
     * @param dates 日期字符串
     * @param withSeconds 是否带s
     * @return 时间
     */
    public static Date string2Date(String dates, boolean withSeconds) {
        try {
            AssertUtils.StringNotEmpty(dates);

            return getDateFormat(withSeconds).parse(dates);
        } catch (Exception e) {
            throw new LYSLException("时间解析错误", LYSLResultCodeEnum.DATE_FORMAT_ERROR);
        }
    }

    /**
     * 校验日期是否符合格式
     * @param strDate 时间字符串
     * @param withSeconds 是否带秒
     * @return 是否符合格式
     */
    public static boolean isFormatDate(String strDate, boolean withSeconds) {
        try {
            string2Date(strDate, withSeconds);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    /**
     * 日期转字符串
     * @param date 日期
     * @param withSeconds 是否带秒
     * @return 字符串
     */
    public static String date2String(Date date, boolean withSeconds) {
        try {
            AssertUtils.AssertNotNull(date);

            return getDateFormat(withSeconds).format(date);
        } catch (Exception e) {
            throw new LYSLException("时间对象转换错误", LYSLResultCodeEnum.DATE_FORMAT_ERROR);
        }
    }

}
