package com.whu.lysl.entity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 18:55
 * @Description:
 */

@Data
public class DonationOrderVO {


    private Integer donationOrderId;

//    private Date gmdCreated;

//    private Date gmdModified;

    /**
     * 捐赠主体 id
     */
    @NotNull(message = "donorId is required")
    @Min(value = 0, message = "donorId must have the type of Integer")
    private Integer donorId;

    /**
     * 捐赠主体名称
     */
    @NotEmpty(message = "donorName is required")
    private String donorName;

    /**
     * 捐赠类型 0-定向 1-非定向
     */
    @NotNull(message = "donationType is required")
    @Min(value = 0, message = "donationType must have the type of Integer and must be positive")
    private Integer donationType;

    /**
     * 捐赠对象id
     */
    private Integer doneeId;

    /**
     * 捐赠对象名称
     */
    private String doneeName;

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

//    private Integer deleted;

    /**
     * 审核状态 0-未审核 1-已审核 2-审核未通过
     */
    private Integer status;
}
