package com.whu.lysl.base.converters;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dbobj.MatchOrderDo;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.donation.DonationOrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 匹配单转换
 *
 * @author Jzh
 * @since 2020-02-09 20:25
 **/
@Component
public class MatchOrderConverter {



    /**
     * 将matchOrder的Model转换成相应的DO，并包含有参数检查
     * @param matchOrder 匹配单
     * @return 返回matchOrderDo的列表
     */
    public static List<MatchOrderDo> Model2DO(MatchOrder matchOrder){
        if (matchOrder == null){
            return null;
        }
        List<MatchOrderDo> matchOrderDoList = new ArrayList<>();
        MatchOrderDo matchOrderDo = new MatchOrderDo();

        if (matchOrder.getDemandOrderId() <= 0) {
            throw new LYSLException("需求单id不能为空", LYSLResultCodeEnum.DATA_INVALID);
        }

        matchOrderDo.setDemandOrderId(matchOrder.getDemandOrderId());

        if (matchOrder.getDonationOrderId() <= 0){
            throw new LYSLException("捐赠单id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDonationOrderId(matchOrder.getDonationOrderId());

        if (matchOrder.getDonorId() <= 0){
            throw new LYSLException("捐赠者id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDonorId(matchOrder.getDonorId());

        if (matchOrder.getDoneeId() <= 0){
            throw new LYSLException("受赠者id不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setDoneeName(matchOrder.getDoneeName());
        matchOrderDo.setDonorName(matchOrder.getDonorName());
        matchOrderDo.setDoneeId(matchOrder.getDoneeId());
        if (matchOrder.getGmtCreated() == null){
            matchOrderDo.setGmtCreated(new Date());
        }
        else{
            matchOrderDo.setGmtCreated(matchOrder.getGmtCreated());
        }
        if (matchOrder.getMaterialNameList().size() == 0){
            throw new LYSLException("物资名称列表不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        if (matchOrder.getMaterialNameList().size()!=matchOrder.getMaterialQuantityList().size()){
            throw new LYSLException("物资名称列表与物资数量列表不匹配",LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDo.setMaterialId(matchOrder.getMaterialIdList().get(0));
        matchOrderDo.setMaterialQuantity(matchOrder.getMaterialQuantityList().get(0));
        matchOrderDo.setMaterialName(matchOrder.getMaterialNameList().get(0));
        matchOrderDo.setGmtModified(new Date());
        matchOrderDo.setMatchingMethod(matchOrder.getMatchingMethod());
        matchOrderDo.setStatus(matchOrder.getStatus());
        matchOrderDo.setTrackingNumber(matchOrder.getTrackingNumber());

        matchOrderDoList.add(matchOrderDo);
        for (int i = 1;i<matchOrder.getMaterialIdList().size();i++){
            try{
                MatchOrderDo matchOrderDo1 = matchOrderDo.clone();
                matchOrderDo1.setMaterialId(matchOrder.getMaterialIdList().get(i));
                matchOrderDo1.setMaterialQuantity(matchOrder.getMaterialQuantityList().get(i));
                matchOrderDo1.setMaterialName(matchOrder.getMaterialNameList().get(i));
                matchOrderDoList.add(matchOrderDo1);
            }
            catch (CloneNotSupportedException e) {
                throw new LYSLException("读入数据出现异常",LYSLResultCodeEnum.SYSTEM_ERROR);
            }

        }
        return matchOrderDoList;
    }

    /**
     * 将MatchOrderDoList转换成matchOrder
     * @param matchOrderDoList
     * @return
     */
    public static MatchOrder DO2Model(List<MatchOrderDo> matchOrderDoList){
        MatchOrder matchOrder = new MatchOrder();
        if (matchOrderDoList.size()==0){
            return null;
        }
        MatchOrderDo matchOrderDo = matchOrderDoList.get(0);
        matchOrder.setDemandOrderId(matchOrderDo.getDemandOrderId());
        matchOrder.setDonationOrderId(matchOrderDo.getDonationOrderId());
        matchOrder.setDonorId(matchOrderDo.getDonorId());
        matchOrder.setDoneeId(matchOrderDo.getDoneeId());
        matchOrder.setDoneeName(matchOrderDo.getDoneeName());
        matchOrder.setDonorName(matchOrderDo.getDonorName());
        matchOrder.setGmtCreated(matchOrderDo.getGmtCreated());
        matchOrder.setStatus(matchOrderDo.getStatus());
        matchOrder.setTrackingNumber(matchOrderDo.getTrackingNumber());
        matchOrder.setMaterialIdList(new ArrayList<>());
        matchOrder.setMaterialQuantityList(new ArrayList<>());
        matchOrder.setMaterialNameList(new ArrayList<>());
        for (int i =0;i<matchOrderDoList.size();i++){
            matchOrder.getMaterialIdList().add(matchOrderDoList.get(i).getMaterialId());
            matchOrder.getMaterialQuantityList().add(matchOrderDoList.get(i).getMaterialQuantity());
            matchOrder.getMaterialNameList().add(matchOrderDoList.get(i).getMaterialName());
        }

        return matchOrder;
    }
}
