package com.whu.lysl.entity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author Caspar
 * @CreateTime 2020/2/12 11:31
 * @Description:
 */
@Data
public class MaterialOrderVO {


    private Integer materialOrderId;

    private Integer donationOrderId;

    /**
     * 物资id
     */
    @NotNull(message = "materialId is required")
    @Min(value = 0, message = "materialId must have the type of Integer and must be positive")
    private Integer materialId;

    /**
     * 物资名
     */
    @NotEmpty(message = "materialName is required")
    private String materialName;

    /**
     * 物资数量
     */
    @NotNull(message = "materialAmount is required")
    @Min(value = 0, message = "materialAmount must have the type of Integer and must be positives")
    private Integer materialAmount;

}
