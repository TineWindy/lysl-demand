package com.whu.lysl.service.donation.impl;

import com.whu.lysl.base.converters.DonationOrderConverter;
import com.whu.lysl.base.enums.DonationOrderStatusEnum;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.LovePoolStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.dao.DonationOrderDAO;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dbobj.DonationOrderDO;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.service.donation.DonationOrderService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 15:24
 * @Description:
 */
@Service
public class DonationOrderServiceImpl implements DonationOrderService {
    @Resource
    private DonationOrderDAO donationOrderDAO;

    @Override
    public List<DonationOrder> getDonationOrderByCondition(DonationOrderCondition donationOrderCondition) {
        return DonationOrderConverter.batchDo2Model(donationOrderDAO.selectByCondition(donationOrderCondition));
    }

    @Override
    public List<DonationOrder> getDonationOrderByDonorId(Integer donorId) {
        AssertUtils.AssertNotNull(donorId);
        return DonationOrderConverter.batchDo2Model(
                donationOrderDAO.selectByCondition(new DonationOrderCondition.Builder().donorId(donorId).build()));
    }

    @Override
    public List<DonationOrder> getDonationOrderByStatus(String status) {
        AssertUtils.AssertNotNull(status);
        if (!EnumUtils.isValidEnum(DonationOrderStatusEnum.class, status)) {
            throw new LYSLException("Status 不属于支持的枚举值", LYSLResultCodeEnum.DATA_INVALID);
        }
        return DonationOrderConverter.batchDo2Model(
                donationOrderDAO.selectByCondition(new DonationOrderCondition.Builder().status(status).build()));
    }

    @Override
    public List<DonationOrder> getDonationOrderByStatusAndDonationType(String status, String donationType) {
        AssertUtils.AssertNotNull(status);
        AssertUtils.AssertNotNull(donationType);
        if (!EnumUtils.isValidEnum(DonationOrderStatusEnum.class, status)) {
            throw new LYSLException("status 不属于支持的枚举值", LYSLResultCodeEnum.DATA_INVALID);
        }
        if (!EnumUtils.isValidEnum(DonationTypeEnum.class, donationType)) {
            throw new LYSLException("donationType 不属于支持的枚举值", LYSLResultCodeEnum.DATA_INVALID);
        }
        return DonationOrderConverter.batchDo2Model(
                donationOrderDAO.selectByCondition(
                        new DonationOrderCondition.Builder().status(status).donationType(donationType).build()));
    }

    @Override
    public List<DonationOrder> getDonationOrderInLovePool(String lovePoolStatus) {
        if (!EnumUtils.isValidEnum(LovePoolStatusEnum.class, lovePoolStatus)) {
            throw new LYSLException("lovePoolStatus 不属于支持的枚举值", LYSLResultCodeEnum.DATA_INVALID);
        }
        return DonationOrderConverter.batchDo2Model(
                donationOrderDAO.selectByCondition(
                        new DonationOrderCondition.Builder().lovePoolStatus(lovePoolStatus).build()));
    }

    @Override
    public int updateDonationOrderLovePoolStatus(DonationOrder donationOrder, String lovePoolStatus) {
        donationOrder.setLovePoolStatus(lovePoolStatus);  // 这边是为了下面对 donationOrder 进行校验
        validateInsertDonatiionOrder(donationOrder);
        DonationOrder donationOrder1 = new DonationOrder();
        donationOrder1.setLovePoolStatus(donationOrder.getLovePoolStatus());
        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
        return updateDonationOrder(donationOrder1);
    }

    @Override
    public Boolean validateDonationOrderId(Integer donationOrderId) {
        if (donationOrderId!=null) {
            List<DonationOrderDO> donationOrderList = donationOrderDAO.selectByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            if (donationOrderList!=null && donationOrderList.size()>=1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public int insertDonationOrder(DonationOrder donationOrder) {
        donationOrder.setStatus(DonationOrderStatusEnum.UNCHECKED.getCode());
        donationOrder.setLovePoolStatus(LovePoolStatusEnum.NOT_IN_POOL.getCode());
        donationOrder.setDeleted(0);
        validateInsertDonatiionOrder(donationOrder);
        return donationOrderDAO.insertDonationOrder(DonationOrderConverter.model2Do(donationOrder));
    }

    @Override
    public int updateDonationOrder(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        return donationOrderDAO.updateDonationOrder(DonationOrderConverter.model2Do(donationOrder));
    }

//    public int checkPass(DonationOrder donationOrder) {
//        validateInsertDonatiionOrder(donationOrder);
//        DonationOrder donationOrder1 = new DonationOrder();
//        donationOrder1.setStatus(DonationOrderStatusEnum.APPROVED.getCode());
//        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
//        return updateDonationOrder(donationOrder1);
//    }
//
//    public int checkFail(DonationOrder donationOrder) {
//        validateInsertDonatiionOrder(donationOrder);
//        DonationOrder donationOrder1 = new DonationOrder();
//        donationOrder1.setStatus(DonationOrderStatusEnum.DISAPPROVED.getCode());
//        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
//        return updateDonationOrder(donationOrder1);
//    }

    public int check(DonationOrder donationOrder, String status) {
        donationOrder.setStatus(status);  // 这边是为了下面对 donationOrder 进行校验
        validateInsertDonatiionOrder(donationOrder);
        DonationOrder donationOrder1 = new DonationOrder();
        donationOrder1.setStatus(donationOrder.getStatus());
        // 如果审核通过且为非定向捐赠，则加入爱心池
        if (donationOrder.getStatus().equals(DonationOrderStatusEnum.APPROVED.getCode())
                && donationOrder.getDonationType().equals(DonationTypeEnum.UNDIRECTED.getCode())) {
            donationOrder1.setLovePoolStatus(LovePoolStatusEnum.IN_POOL.getCode());
        }
        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
        return updateDonationOrder(donationOrder1);
    }

    public int changeLovePoolStatus(DonationOrder donationOrder, String lovePoolStatus) {
        donationOrder.setLovePoolStatus(lovePoolStatus);
        validateInsertDonatiionOrder(donationOrder);
        DonationOrder donationOrder1 = new DonationOrder();
        donationOrder1.setStatus(donationOrder.getStatus());
        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
        return updateDonationOrder(donationOrder1);
    }

    public void validateInsertDonatiionOrder(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        AssertUtils.AssertNotNull(donationOrder.getDonorId());
        AssertUtils.AssertNotNull(donationOrder.getDonorName());
        AssertUtils.AssertNotNull(donationOrder.getDoneeId());
        AssertUtils.AssertNotNull(donationOrder.getDoneeName());
        AssertUtils.AssertNotNull(donationOrder.getMaterialId());
        AssertUtils.AssertNotNull(donationOrder.getMaterialName());
        AssertUtils.AssertNotNull(donationOrder.getMaterialAmount());

        if (!EnumUtils.isValidEnum(DonationTypeEnum.class, donationOrder.getDonationType())) {
            throw new LYSLException("donationType 不属于支持的枚举值 {DIRECTED, UNDIRECTED}", LYSLResultCodeEnum.DATA_INVALID);
        }
        if (!EnumUtils.isValidEnum(DonationOrderStatusEnum.class, donationOrder.getStatus())) {
            throw new LYSLException("donationStatus 不属于支持的枚举值 {UNCHECKED, APPROVED, DISAPPROVED}", LYSLResultCodeEnum.DATA_INVALID);
        }
        if (!EnumUtils.isValidEnum(LovePoolStatusEnum.class, donationOrder.getLovePoolStatus())) {
            throw new LYSLException("love_pool_status 不属于支持的枚举值 {NOT_IN_POOL, IN_POOL, ARTI_DISPATCHED, AUTO_DISPATCHED}", LYSLResultCodeEnum.DATA_INVALID);
        }
//        定向捐赠 donorId 非空， 非定向捐赠 donorId=-1
        if (StringUtils.equal(donationOrder.getDonationType(), DonationTypeEnum.DIRECTED.getCode())) {
            if (donationOrder.getDoneeId()==-1) {
                throw new LYSLException("定向捐赠 donorId 无效", LYSLResultCodeEnum.DATA_INVALID);
            }
            //TODO 校验donorId donorName 有效性

        } else {
            if (donationOrder.getDoneeId()!=-1) {
                throw new LYSLException("非定向捐赠 donorId应当为 -1", LYSLResultCodeEnum.DATA_INVALID);
            }
        }
        //TODO 校验 doneeId 和 doneeName 有效性
        //TODO 校验 materialId 和 materialName 有效性



    }

}
