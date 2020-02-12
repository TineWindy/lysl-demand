package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/12 15:50
 * @Description:
 */

@Data
public class MaterialOrderDO {

    private Integer donationOrderId;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer donation_order_id;

    private Integer material_id;

    private String material_name;

    private Integer material_amount;


}
