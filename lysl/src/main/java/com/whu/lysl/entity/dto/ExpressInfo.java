package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 快递信息
 *
 * @author Jzh
 * @since 2020-02-12 21:11
 **/
@Data
public class ExpressInfo {

    private String eBusinessID;
    private int orderCode;
    private String shipperCode;
    private String logisticCode;
    private boolean success;
    private String Reason;
    private List<Trace> traces;
    private int state;

    @Data
    public class Trace{

        private Date acceptTime;
        private String AcceptStation;
        private String Remark;

    }
}
