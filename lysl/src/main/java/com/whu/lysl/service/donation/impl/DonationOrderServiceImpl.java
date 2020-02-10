package com.whu.lysl.service.donation.impl;

import com.whu.lysl.base.converters.DonationOrderConverter;
import com.whu.lysl.base.enums.DonationOrderStatusEnum;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.dao.DonationOrderDAO;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dbobj.DonationOrderDO;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.service.donation.DonationOrderService;
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
    public List<DonationOrder> selectAllDonationOrder() {
        return DonationOrderConverter.batchDo2Model(donationOrderDAO.selectAll());
    }

    @Override
    public int insertDonationOrder(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        donationOrder.setStatus(DonationOrderStatusEnum.UNCHECKED.getCode());
        donationOrder.setDeleted(0);
        return donationOrderDAO.insertDonationOrder(DonationOrderConverter.model2Do(donationOrder));
    }

    @Override
    public int updateDonationOrder(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        return donationOrderDAO.updateDonationOrder(DonationOrderConverter.model2Do(donationOrder));
    }

    public int checkPass(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        AssertUtils.AssertNotNull(donationOrder.getDonationOrderId());
        DonationOrder donationOrder1 = new DonationOrder();
        donationOrder1.setStatus(DonationOrderStatusEnum.APPROVED.getCode());
        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
        return updateDonationOrder(donationOrder1);
    }

    public int checkFail(DonationOrder donationOrder) {
        AssertUtils.AssertNotNull(donationOrder);
        AssertUtils.AssertNotNull(donationOrder.getDonationOrderId());
        DonationOrder donationOrder1 = new DonationOrder();
        donationOrder1.setStatus(DonationOrderStatusEnum.DISAPPROVED.getCode());
        donationOrder1.setDonationOrderId(donationOrder.getDonationOrderId());
        return updateDonationOrder(donationOrder1);
    }

}
