package com.whu.lysl.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.whu.lysl.base.converters.MatchOrderConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.MatchingMethodEnum;
import com.whu.lysl.base.enums.MatchingStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.dao.MatchOrderDAO;
import com.whu.lysl.entity.dbobj.MatchOrderDo;
import com.whu.lysl.entity.dto.MatchOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单匹配的具体实现类
 * @author Jzh
 * @since 2020-02-09 19:53
 **/
@Service
public class OrderMatchServiceImpl implements OrderMatchService{

    @Resource
    MatchOrderDAO matchOrderDAO;

    /**
     * 定向捐赠后的匹配接口（在人工审核后调用）
     * @param matchOrder 匹配单
     * @throws LYSLException 主要是参数异常
     */
    @Override
    public void saveMatchOrder(MatchOrder matchOrder) throws LYSLException {
        // 将DTO转换成DO，同时进行参数检查

        List<MatchOrderDo> matchOrderDoList = MatchOrderConverter.Model2DO(matchOrder);
        for (int i = 0;i< matchOrderDoList.size();i++){
            MatchOrderDo matchOrderDo = matchOrderDoList.get(i);
            // 调用DAO将数据存入数据库
            matchOrderDAO.saveMatchOrder(matchOrderDo);
        }

    }

    /**
     * 根据捐赠者名字查询匹配单
     * @param honorName
     * @return
     * @throws LYSLException
     */
    @Override
    public List<MatchOrder> getMatchOrderByDonorName(String honorName) throws LYSLException {
        // TODO 去捐赠模块检查donorId是否正确
        int donorId = 1;
        List<Integer> donationOrderIdList = matchOrderDAO.selectDonationOrderIdByDonorId(donorId);
        List<MatchOrder> matchOrderList = new ArrayList<>();
        for (int i =0;i<donationOrderIdList.size();i++){

            List<MatchOrderDo> matchOrderDoList = matchOrderDAO.selectByDonorIdAndDonationOrderId(donorId,donationOrderIdList.get(i));
            MatchOrder matchOrder = MatchOrderConverter.DO2Model(matchOrderDoList);
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;

    }

    /**
     * 根据受赠者名字查询匹配单
     * @param doneeName 受赠者名字
     * @return
     * @throws LYSLException
     */
    @Override
    public List<MatchOrder> getMatchOrderByDoneeName(String doneeName) throws LYSLException {
        // TODO 去需求模块检查donorId是否正确
        int doneeId = 1;
        List<Integer> demandOrderIdList = matchOrderDAO.selectDemandOrderIdByDoneeId(doneeId);
        List<MatchOrder> matchOrderList = new ArrayList<>();
        for (int i =0;i<demandOrderIdList.size();i++){

            List<MatchOrderDo> matchOrderDoList = matchOrderDAO.selectByDoneeIdAndDonationOrderId(doneeId,demandOrderIdList.get(i));
            MatchOrder matchOrder = MatchOrderConverter.DO2Model(matchOrderDoList);
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;
    }

    /**
     * 更新匹配单状态
     * @param matchOrderId
     * @param status
     * @throws LYSLException
     */
    @Override
    public void updateMatchOrderStatus(int matchOrderId, String status) throws LYSLException {

        if (MatchingStatusEnum.getEnumByCode(status) == null){
            throw new LYSLException("状态值不存在",LYSLResultCodeEnum.DATA_INVALID);
        }

        matchOrderDAO.updateStatus(matchOrderId,status);

    }

    @Override
    public void updateTrackingNumber(int matchOrderId, String trackingNumber) throws LYSLException {
        matchOrderDAO.updateTrackingNumber(matchOrderId,trackingNumber);
    }


}
