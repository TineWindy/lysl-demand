package com.whu.lysl.entity.condition;

import com.whu.lysl.base.utils.StringUtils;
import lombok.Data;

/**
 * 匹配单查询条件
 *
 * @author Jzh
 * @since 2020-02-11 11:46
 **/
@Data
public class MatchOrderCondition {

    private int pageNo;

    private int pageSize;

    private int doneeId;

    private int donorId;

    private int donationOrderId;

    private int demandOrderId;

    private String status;

    private String matchingMethod;

    public boolean isAllNull(){
        return doneeId == 0 && donorId == 0 && !StringUtils.isNotEmpty(status) && !StringUtils.isNotEmpty(matchingMethod);
    }

}
