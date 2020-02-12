package com.whu.lysl.service.donation;

import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.MaterialOrder;

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
     * @return 捐赠订单 list
     */
    List<DonationOrder> getDonationOrderByCondition(DonationOrderCondition donationOrderCondition);

    /**
     * 查询捐赠订单号查询所有物资清单
     * @param donationOrderId 订单号
     * @return 捐赠订单 list
     */
    List<MaterialOrder> getMaterialOrderByDonationOrderId(Integer donationOrderId);

    /**
     * 根据 donorId 查询捐赠订单
     * @param donorId 查询条件
     * @return 捐赠订单 list
     */
    List<DonationOrder> getDonationOrderByDonorId(Integer donorId);

    /**
     * 根据 status 查询捐赠订单
     * @param status 查询条件
     * @return 捐赠订单 list
     */
    List<DonationOrder> getDonationOrderByStatus(String status);

    /**
     * 根据 status 和 donationType 查询捐赠订单
     * @param status 查询条件
     * @param donationType 捐赠类型
     * @return 捐赠订单 list
     */
    List<DonationOrder> getDonationOrderByStatusAndDonationType(String status, String donationType);

    /**
     * 查询爱心池订单
     * @return 捐赠订单 list
     */
    List<DonationOrder> getDonationOrderInLovePool(String lovePoolStatus);

    /**
     * 更新爱心池状态
     * @return 捐赠订单 list
     */
    int updateDonationOrderLovePoolStatus(DonationOrder donationOrder, String lovePoolStatus);

    /**
     * 校验捐赠单号是否有效
     * @param donationOrderId 捐赠单号id
     * @return Boolean
     */
    Boolean validateDonationOrderId(Integer donationOrderId);

    // 仅仅插入捐赠单一张表，不插入物资清单表
    int insertDonationOrder(DonationOrder donationOrder);

    // 不止插入捐赠单一张表，还会插入物资清单表
    int insertDonationOrderDetail(DonationOrder donationOrder);

    int insertMaterialOrder(MaterialOrder materialOrder);

    int updateDonationOrder(DonationOrder donationOrder);

    int check(DonationOrder donationOrder, String status);

    /**
     * 校验插入方法中的 donationOrder 对象是否有效
     * @param donationOrder 捐赠单号id
     * @return Boolean
     */
    void validateInsertDonatiionOrder(DonationOrder donationOrder);

    /**
     * 逻辑删除 捐赠订单
     * @param donationOrderId 捐赠单号id
     * @return 1-成功删除 0-未找到该订单
     */
    int deleteDonationOrder(Integer donationOrderId);

}
