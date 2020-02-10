package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @Date 2020/2/9 14:34
 * @Description:
 */
@Data
public class DonationOrder {

    private Integer donationOrderId;

    private Date gmdCreated;

    private Date gmdModified;

    /**
     * 捐赠主体 id
     */
    private Integer donorId;

    /**
     * 捐赠主体名称
     */
    private String donorName;

    /**
     * 捐赠类型 0-定向 1-非定向
     */
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
    private Integer materialId;

    /**
     * 物资名
     */
    private String materialName;

    /**
     * 物资数量
     */
    private Integer materialAmount;

    private Integer deleted;

    /**
     * 审核状态 0-未审核 1-已审核 2-审核未通过
     */
    private String status;

}
