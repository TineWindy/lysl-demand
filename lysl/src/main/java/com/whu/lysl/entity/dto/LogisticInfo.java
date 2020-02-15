package com.whu.lysl.entity.dto;

import lombok.Data;

/**
 * 物流单号和快递公司缩写
 *
 * @author Jzh
 * @since 2020-02-12 23:04
 **/
@Data
public class LogisticInfo {
    private int id;
    private String logisticCode;
    private String shipperCode;

    public String getKey(){
        return shipperCode+logisticCode;
    }

    public LogisticInfo(String shipperCode,String logisticCode){
        this.logisticCode = logisticCode;
        this.shipperCode = shipperCode;
    }
}
