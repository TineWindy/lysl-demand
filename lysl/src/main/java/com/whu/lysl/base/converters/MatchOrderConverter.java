package com.whu.lysl.base.converters;

import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.entity.dbobj.MatchOrderDo;
import com.whu.lysl.entity.dto.MatchOrder;

import java.util.Date;

/**
 * 匹配单转换
 *
 * @author Jzh
 * @since 2020-02-09 20:25
 **/
public class MatchOrderConverter {

    public static MatchOrderDo Model2DO(MatchOrder matchOrder){
        MatchOrderDo matchOrderDo = new MatchOrderDo();

        if (matchOrderDo.getDemand_order_id() <= 0) {
            throw new LYSLException("需求单id不能为空", LYSLResultCodeEnum.DATA_INVALID);
        }
        // TODO 去需求模块查询id是否正确
        matchOrderDo.setDemand_order_id(matchOrder.getDemand_order_id());

        if (matchOrderDo.getDonation_order_id() <= 0){
            throw new LYSLException("捐赠单id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDonation_order_id(matchOrder.getDonation_order_id());

        if (matchOrderDo.getDonor_id() <= 0){
            throw new LYSLException("捐赠者id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDonor_id(matchOrder.getDonor_id());

        if (matchOrderDo.getDonee_id() <= 0){
            throw new LYSLException("受赠者id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDonee_id(matchOrder.getDonee_id());

        matchOrderDo.setGmt_created(matchOrder.getGmt_created());
        matchOrderDo.setGmt_modified(new Date());
        matchOrderDo.setMatching_method(matchOrder.getMatching_method());
        matchOrderDo.setMaterial_id(matchOrder.getMaterial_id());
        matchOrderDo.setStatus(matchOrder.getStatus());
        matchOrderDo.setTracking_number(matchOrder.getTracking_number());
        return matchOrderDo;
    }


}
