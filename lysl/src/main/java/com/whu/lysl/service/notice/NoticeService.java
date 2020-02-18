package com.whu.lysl.service.notice;

import com.whu.lysl.base.enums.LYSLMessageEnum;

/**
 * 通知服务
 * @author Visionary
 * @since 2020/2/18 10:04 PM
 */
public interface NoticeService {

    /**
     * 发送单条短信
     * @param lyslMessageEnum 短信模板
     * @param phone 电话号码
     * @param params 短信内容参数
     */
    void sendSingleMessage(LYSLMessageEnum lyslMessageEnum, String phone, String... params);

}
