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

    List<DonationOrder> selectAllDonationOrder();

    int insertDonationOrder(DonationOrder donationOrder);

    int updateDonationOrder(DonationOrder donationOrder);

    int checkPass(DonationOrder donationOrder);

    int checkFail(DonationOrder donationOrder);

}
