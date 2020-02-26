package com.whu.lysl.service.transportation.impl;

import com.whu.lysl.base.converters.TransportationConverter;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.dao.TransportationDAO;
import com.whu.lysl.entity.condition.TransportationCondition;
import com.whu.lysl.entity.dbobj.TransportationDO;
import com.whu.lysl.entity.dto.News;
import com.whu.lysl.entity.dto.Transportation;
import com.whu.lysl.service.transportation.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 14:30
 * @Description:
 */
@Service
@Slf4j
public class TransportationServiceImpl implements TransportationService {
    @Resource
    TransportationDAO transportationDAO;
    @Override
    public List<Transportation> getTransportationByCondition(TransportationCondition transportationCondition) {
        return TransportationConverter.batchDo2Model(transportationDAO.selectByCondition(transportationCondition));
    }

    @Override
    public List<Transportation> getTransportationApproved() {
        return getTransportationByCondition(
                new TransportationCondition.Builder().deleted(0)
                        .checkStatus(OrderStatusEnum.APPROVED.getCode()).build());
    }

    @Override
    public List<Transportation> getTransportationByPartitionOfName(String name) {
        return TransportationConverter.batchDo2Model(transportationDAO.queryByPartitionOfName(name));
    }

    @Override
    public int insertTransportation(Transportation transportation) {
        AssertUtils.AssertNotNull(transportation);
        transportation.setCheckStatus(OrderStatusEnum.UNCHECKED.getCode());
        transportation.setDeleted(0);
        validateTransportation(transportation);
        TransportationDO transportationDO = TransportationConverter.model2DO(transportation);
        try {
            transportationDAO.insert(transportationDO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LYSLException("插入记录失败",LYSLResultCodeEnum.ERROR);
        }
        return transportationDO.getId();
    }

    @Override
    public int updateTransportation(Transportation transportation) {
        return transportationDAO.update(TransportationConverter.model2DO(transportation));
    }

    @Override
    public int deleteTransportation(Integer id) {
        return transportationDAO.delete(id);
    }

    @Transactional(rollbackFor = LYSLException.class)
    public int check(Transportation transportation, String status) {
        // 只有未审核状态的信息允许进行审核操作
        if (!transportation.getCheckStatus().equals(OrderStatusEnum.UNCHECKED.getCode())){
            throw new LYSLException("操作失败，该条记录已经进行过审核操作", LYSLResultCodeEnum.ERROR);
        }
        transportation.setCheckStatus(status);
        validateTransportation(transportation);
        Transportation transportation1 = new Transportation();
        transportation1.setId(transportation.getId());
        transportation1.setCheckStatus(transportation.getCheckStatus());
        int updateAns;
        try {
            updateAns = transportationDAO.update(TransportationConverter.model2DO(transportation1));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LYSLException("更新审核状态失败", LYSLResultCodeEnum.ERROR);
        }
        return updateAns;
    }

    public int checkTranspirtatinById(Integer id, String status) {

        List<Transportation> listTransportation = getTransportationByCondition(
                new TransportationCondition.Builder().id(id).deleted(0).build());
        AssertUtils.AssertNotNull(listTransportation, "查询id失败");
        if (listTransportation.size()==0) {
            throw new LYSLException("未查询到该条运输记录", LYSLResultCodeEnum.ERROR);
        }
        return check(listTransportation.get(0), status);
    }

    public void validateTransportation(Transportation transportation) {
        AssertUtils.AssertNotNull(transportation);
        AssertUtils.AssertNotNull(transportation.getLinkMan(), "linkMan为空");
        AssertUtils.AssertNotNull(transportation.getLinkMobile(), "linkMobile为空");
        AssertUtils.AssertNotNull(transportation.getDeliveryScope(), "deliveryScope为空");
        AssertUtils.AssertNotNull(transportation.getCheckStatus(), "checkStatus为空");
        AssertUtils.AssertNotNull(transportation.getDeleted(), "deleted为空");
        AssertUtils.AssertNotNull(transportation.getRemark(), "remark为空");
        if (!EnumUtils.isValidEnum(OrderStatusEnum.class, transportation.getCheckStatus())) {
            throw new LYSLException("checkStatus 不属于支持的枚举值 {UNCHECKED, APPROVED, DISAPPROVED}", LYSLResultCodeEnum.DATA_INVALID);
        }

    }

}
