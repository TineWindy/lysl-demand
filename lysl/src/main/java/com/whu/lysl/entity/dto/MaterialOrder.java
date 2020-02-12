package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/12 15:54
 * @Description:
 */

@Data
public class MaterialOrder {

    private Integer donationOrderId;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer donation_order_id;

    private Integer material_id;

    private String material_name;

    private Integer material_amount;

}
