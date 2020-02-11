package com.whu.lysl.service.donation;

import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dto.DonationOrder;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 15:23
 * @Description:
 */
public interface DonationOrderService {

    /**
     * 查询符合条件的捐赠订单
     * @param donationOrderCondition 查询条件
     * @return 机构 list
     */
    List<DonationOrder> getDonationOrderByCondition(DonationOrderCondition donationOrderCondition);

    /**
     * 根据 donorId 查询捐赠订单
     * @param donorId 查询条件
     * @return 机构 list
     */
    List<DonationOrder> getDonationOrderByDonorId(Integer donorId);

    /**
     * 根据 status 查询捐赠订单
     * @param status 查询条件
     * @return 机构 list
     */
    List<DonationOrder> getDonationOrderByDonorId(String status);

    int insertDonationOrder(DonationOrder donationOrder);

    int updateDonationOrder(DonationOrder donationOrder);

    int checkPass(DonationOrder donationOrder);

    int checkFail(DonationOrder donationOrder);

    void validate(DonationOrder donationOrder);



}
