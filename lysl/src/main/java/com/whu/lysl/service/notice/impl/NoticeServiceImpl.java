package com.whu.lysl.service.notice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.constants.LYSLConstants;
import com.whu.lysl.base.enums.LYSLMessageEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信服务实现
 *
 * @author Visionary
 * @since 2020/2/18 10:08 PM
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Override
    public void sendSingleMessage(LYSLMessageEnum lyslMessageEnum, String phone, String... params) {
        AssertUtils.AssertNotNull(lyslMessageEnum, "sending message cant be empty");
        AssertUtils.StringNotEmpty(phone, "sending phone number cant be empty");
        String msgText = generateMsgStr(lyslMessageEnum.getCode(), params);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(LYSLConstants.SEND_SMS_ADDRESS);
        CloseableHttpResponse response = null;
        try {

            List<NameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("apikey", LYSLConstants.YUNPIAN_API_KEY));
            formParams.add(new BasicNameValuePair("mobile", phone));
            formParams.add(new BasicNameValuePair("text", msgText));
            HttpEntity reqEntity = new UrlEncodedFormEntity(formParams, "utf-8");
            httpPost.setEntity(reqEntity);

            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String resp = EntityUtils.toString(responseEntity);
            JSONObject jsonObject = JSON.parseObject(resp);
            if ("0".equals(jsonObject.get("code") + "")) {
                log.info("success to send sms msg, phone: " + phone + ", msg: " + msgText);
            }
        } catch (Exception e) {
            throw new LYSLException("fail to send sms message, phone: " + phone + ", msg: " +
                    lyslMessageEnum.getCode(), LYSLResultCodeEnum.DATA_INVALID);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理模板，生成短信内容
     * @param template 模板
     * @param params 参数
     * @return 处理后的字符串
     */
    private static String generateMsgStr(String template, String... params) {
        if (params != null) {
            for (String string : params) {
                template = template.replaceFirst("_param_", string);
            }
        }

        return template;
    }

}
