package com.whu.lysl.entity.dto;

import lombok.Data;

/**
 * 确认收货的请求参数
 *
 * @author Jzh
 * @since 2020-02-18 15:17
 **/
@Data
public class ConfirmReceiptRequest {

    private int matchOrderId;

}
