package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 查询订单是否存在的相应信息
 *
 * @author Jzh
 * @since 2020-02-16 14:25
 **/
@Data
public class IdentifyOrderResponse {

    private String eBusinessID;
    private boolean success;
    private String logisticCode;
    private List<Shipper> shippers;

    @Data
    public class Shipper{
        private String shipperCode;
        private String shipperName;
    }

}
