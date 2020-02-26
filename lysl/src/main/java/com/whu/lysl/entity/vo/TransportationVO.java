package com.whu.lysl.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 13:40
 * @Description:
 */
@Data
public class TransportationVO {

    private Integer id;

    private String name;

    private String linkMan;

    private String linkMobile;

    private String deliveryScope;

    private String remark;

    private String checkStatus;

}
