package com.whu.lysl.base.converters;

import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.entity.dbobj.DonationOrderDO;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.vo.DonationOrderListVO;
import com.whu.lysl.entity.vo.DonationOrderVO;
import com.whu.lysl.entity.vo.MaterialOrderVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构类型转换
 * @author Visionary
 * @since 2020/2/8 8:40 PM
 */
public class DonationOrderConverter {

    /**
     * do 2 model
     * @param donationOrderDO do
     * @return model
     */
    public static DonationOrder do2Model(DonationOrderDO donationOrderDO) {
        if (donationOrderDO == null) {
            return null;
        }

        DonationOrder donationOrder = new DonationOrder();
        donationOrder.setDonationOrderId(donationOrderDO.getDonationOrderId());
        donationOrder.setDonationType(donationOrderDO.getDonationType());
        donationOrder.setDonorId(donationOrderDO.getDonorId());
        donationOrder.setDonorName(donationOrderDO.getDonorName());
        donationOrder.setDoneeId(donationOrderDO.getDoneeId());
        donationOrder.setDoneeName(donationOrderDO.getDoneeName());
        donationOrder.setMaterialId(donationOrderDO.getMaterialId());
        donationOrder.setMaterialName(donationOrderDO.getMaterialName());
        donationOrder.setMaterialAmount(donationOrderDO.getMaterialAmount());
        donationOrder.setDeleted(donationOrderDO.getDeleted());
        donationOrder.setStatus(donationOrderDO.getStatus());
        donationOrder.setGmtCreated(donationOrderDO.getGmtCreated());
        donationOrder.setGmtModified(donationOrderDO.getGmtModified());
        donationOrder.setLovePoolStatus(donationOrderDO.getLovePoolStatus());

        return donationOrder;
    }

    /**
     * model 2 do
     * @param  donationOrder
     * @return donationOrderDO do
     */
    public static DonationOrderDO model2Do(DonationOrder donationOrder) {
        if (donationOrder == null) {
            return null;
        }

        DonationOrderDO donationOrderDO = new DonationOrderDO();
        donationOrderDO.setDonationOrderId(donationOrder.getDonationOrderId());
        donationOrderDO.setDonationType(donationOrder.getDonationType());
        donationOrderDO.setDonorId(donationOrder.getDonorId());
        donationOrderDO.setDonorName(donationOrder.getDonorName());
        donationOrderDO.setDoneeId(donationOrder.getDoneeId());
        donationOrderDO.setDoneeName(donationOrder.getDoneeName());
        donationOrderDO.setMaterialId(donationOrder.getMaterialId());
        donationOrderDO.setMaterialName(donationOrder.getMaterialName());
        donationOrderDO.setMaterialAmount(donationOrder.getMaterialAmount());
        donationOrderDO.setDeleted(donationOrder.getDeleted());
        donationOrderDO.setStatus(donationOrder.getStatus());
        donationOrderDO.setGmtCreated(donationOrder.getGmtCreated());
        donationOrderDO.setGmtModified(donationOrder.getGmtModified());
        donationOrderDO.setLovePoolStatus(donationOrder.getLovePoolStatus());


        return donationOrderDO;
    }

    /**
     * model 2 vo
     * @param  donationOrder
     * @return donationOrderVO vo
     */
    public static DonationOrderVO model2Vo(DonationOrder donationOrder) {
        if (donationOrder == null) {
            return null;
        }

        DonationOrderVO donationOrderVO = new DonationOrderVO();
        donationOrderVO.setDonationOrderId(donationOrder.getDonationOrderId());
        donationOrderVO.setDonationType(donationOrder.getDonationType());
        donationOrderVO.setDonorId(donationOrder.getDonorId());
        donationOrderVO.setDonorName(donationOrder.getDonorName());
        donationOrderVO.setDoneeId(donationOrder.getDoneeId());
        donationOrderVO.setDoneeName(donationOrder.getDoneeName());
        donationOrderVO.setMaterialId(donationOrder.getMaterialId());
        donationOrderVO.setMaterialName(donationOrder.getMaterialName());
        donationOrderVO.setMaterialAmount(donationOrder.getMaterialAmount());
        donationOrderVO.setStatus(donationOrder.getStatus());
        donationOrderVO.setLovePoolStatus(donationOrder.getLovePoolStatus());


        return donationOrderVO;
    }


    /**
     * vo 2 model
     * @param  donationOrderVO
     * @return donationOrder model
     */
    public static DonationOrder vo2Model(DonationOrderVO donationOrderVO) {
        if (donationOrderVO == null) {
            return null;
        }

        DonationOrder donationOrder = new DonationOrder();
        donationOrder.setDonationOrderId(donationOrderVO.getDonationOrderId());
        donationOrder.setDonationType(donationOrderVO.getDonationType());
        donationOrder.setDonorId(donationOrderVO.getDonorId());
        donationOrder.setDonorName(donationOrderVO.getDonorName());
        donationOrder.setDoneeId(donationOrderVO.getDoneeId());
        donationOrder.setDoneeName(donationOrderVO.getDoneeName());
        donationOrder.setMaterialId(donationOrderVO.getMaterialId());
        donationOrder.setMaterialName(donationOrderVO.getMaterialName());
        donationOrder.setMaterialAmount(donationOrderVO.getMaterialAmount());
        donationOrder.setStatus(donationOrderVO.getStatus());
        donationOrder.setLovePoolStatus(donationOrderVO.getLovePoolStatus());

        return donationOrder;
    }

    /**
     * batch do 2 model
     * @param donationOrderDOS do list
     * @return model list
     */
    public static List<DonationOrder> batchDo2Model(List<DonationOrderDO> donationOrderDOS) {
        List<DonationOrder> donationOrders = new ArrayList<>();

        if(donationOrderDOS == null) {
            return donationOrders;
        }

        for (DonationOrderDO donationOrderDO: donationOrderDOS) {
            donationOrders.add(do2Model(donationOrderDO));
        }

        return donationOrders;
    }

    /**
     * batch model 2 do
     * @param donationOrders model list
     * @return do list
     */
    public static List<DonationOrderDO> batchModel2Do(List<DonationOrder> donationOrders) {
        List<DonationOrderDO> donationOrdersDOS = new ArrayList<>();

        if(donationOrders == null) {
            return donationOrdersDOS;
        }

        for (DonationOrder donationOrder: donationOrders) {
            donationOrdersDOS.add(model2Do(donationOrder));
        }

        return donationOrdersDOS;
    }

    /**
     * batch vo 2 model
     * @param donationOrderVOS vo list
     * @return model list
     */
    public static List<DonationOrder> batchVo2Model(List<DonationOrderVO> donationOrderVOS) {
        List<DonationOrder> donationOrders = new ArrayList<>();

        if(donationOrderVOS == null) {
            return donationOrders;
        }

        for (DonationOrderVO donationOrderVO: donationOrderVOS) {
            donationOrders.add(vo2Model(donationOrderVO));
        }

        return donationOrders;
    }

    /**
     * batch model 2 vo
     * @param donationOrders model list
     * @return vo list
     */
    public static List<DonationOrderVO> batchModel2Vo(List<DonationOrder> donationOrders) {
        List<DonationOrderVO> donationOrdersVOS = new ArrayList<>();

        if(donationOrders == null) {
            return donationOrdersVOS;
        }

        for (DonationOrder donationOrder: donationOrders) {
            donationOrdersVOS.add(model2Vo(donationOrder));
        }

        return donationOrdersVOS;
    }

    public static List<DonationOrderVO> donationOrderList2ListDonationOrder(DonationOrderListVO donationOrderListVO) {
        List<DonationOrderVO> donationOrdersVOS = new ArrayList<>();

        if (donationOrderListVO==null) {
            return donationOrdersVOS;
        }

        List<MaterialOrderVO> materialOrderVOS = donationOrderListVO.getMaterialOrderList();

        for (MaterialOrderVO materialOrderVO: materialOrderVOS) {
            DonationOrderVO donationOrderVO = new DonationOrderVO();
            donationOrderVO.setDonationOrderId(donationOrderListVO.getDonationOrderId());
            donationOrderVO.setDonorId(donationOrderListVO.getDonorId());
            donationOrderVO.setDonorName(donationOrderListVO.getDonorName());
            donationOrderVO.setDonationType(donationOrderListVO.getDonationType());
            donationOrderVO.setDoneeId(donationOrderListVO.getDoneeId());
            donationOrderVO.setDoneeName(donationOrderListVO.getDoneeName());

            donationOrderVO.setDonationOrderId(materialOrderVO.getDonationOrderId());
            donationOrderVO.setMaterialId(materialOrderVO.getMaterialId());
            donationOrderVO.setMaterialName(materialOrderVO.getMaterialName());
            donationOrderVO.setMaterialAmount(materialOrderVO.getMaterialAmount());

            donationOrdersVOS.add(donationOrderVO);
        }

        return donationOrdersVOS;
    }

}
