package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @Date 2020/2/9 14:43
 * @Description:
 */
@Data
public class DonationOrderDO {

    private Integer donationOrderId;

    private Date gmtCreated;

    private Date gmtModified;

    /**
     * 捐赠主体 id
     */
    private Integer donorId;

    /**
     * 捐赠主体名称
     */
    private String donorName;

    /**
     * 捐赠类型 DIRECTED-定向 UNDIRECTED-非定向
     */
    private String donationType;

    /**
     * 捐赠对象机构id
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
     * 审核状态 UNCHECKED-未审核 APPROVED-已审核 DISAPPROVED-审核未通过
     */
    private String status;

    /**
     * 爱心池状态 NOT_IN_POOL-未加入爱心池 IN_POOL-已加入爱心池 ARTI_DISPATCHED-人工派单 AUTO_DISPATCHED-人工派单
     */
    private String lovePoolStatus;

}
