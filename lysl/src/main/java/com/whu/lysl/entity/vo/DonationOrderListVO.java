package com.whu.lysl.entity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 18:55
 * @Description:
 */

@Data
public class DonationOrderListVO {


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
    @NotEmpty(message = "donationType is required")
    private String donationType;

    /**
     * 捐赠对象id
     */
    @NotNull(message = "doneeId is required")
    private Integer doneeId;

    /**
     * 捐赠对象名称
     */
    @NotEmpty(message = "doneeName is required")
    private String doneeName;

    @NotNull(message = "donationOrderVOList is required")
    private List<MaterialOrderVO> materialOrderList;

    /**
     * 审核状态 0-未审核 1-已审核 2-审核未通过
     */
    private String status;

    private String lovePoolStatus;

}
