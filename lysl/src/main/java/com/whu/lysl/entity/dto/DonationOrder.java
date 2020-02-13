package com.whu.lysl.entity.dto;

import com.whu.lysl.entity.dbobj.MaterialOrderDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author Caspar
 * @Date 2020/2/9 14:34
 * @Description:
 */
@Data
public class DonationOrder {

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
     * 捐赠对象id
     */
    private Integer doneeId;

    /**
     * 捐赠对象名称
     */
    private String doneeName;

    /**
     * 捐赠对象名称
     */
    private List<MaterialOrder> materialOrderList;

    private Integer deleted;

    /**
     * 审核状态 UNCHECKED-未审核 APPROVED-已审核 DISAPPROVED-审核未通过
     */
    private String status;

    private String lovePoolStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发货地
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    private Integer userId;

}
