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

    private Integer materialOrderId;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer donationOrderId;

    private Integer materialId;

    private String materialName;

    private Integer materialAmount;


}
