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

    private Integer materialOrderId;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer donationOrderId;

    private Integer materialId;

    private String materialName;

    private Integer materialAmount;

}
