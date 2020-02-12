package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.MaterialOrderDO;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.MaterialOrder;
import com.whu.lysl.entity.vo.InstitutionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构类型转换
 * @author Visionary
 * @since 2020/2/8 8:40 PM
 */
public class MaterialOrderConverter {

    /**
     * do 2 model
     * @param materialOrderDO do
     * @return model
     */
    public static MaterialOrder do2Model(MaterialOrderDO materialOrderDO) {
        if (materialOrderDO == null) {
            return null;
        }

        MaterialOrder materialOrder = new MaterialOrder();
        materialOrder.setMaterialOrderId(materialOrderDO.getMaterialOrderId());
        materialOrder.setMaterialAmount(materialOrderDO.getMaterialAmount());
        materialOrder.setDonationOrderId(materialOrderDO.getDonationOrderId());
        materialOrder.setMaterialId(materialOrderDO.getMaterialId());
        materialOrder.setMaterialName(materialOrderDO.getMaterialName());

        return materialOrder;
    }

    /**
     * batch do 2 model
     * @param materialOrderDOS do list
     * @return model list
     */
    public static List<MaterialOrder> batchDo2Model(List<MaterialOrderDO> materialOrderDOS) {
        List<MaterialOrder> materialOrders = new ArrayList<>();

        if(materialOrderDOS == null) {
            return materialOrders;
        }

        for (MaterialOrderDO materialOrderDO: materialOrderDOS) {
            materialOrders.add(do2Model(materialOrderDO));
        }

        return materialOrders;
    }

    /**
     * batch models 2 vos
     * @param materialOrders models
     * @return vos
     */
    public static List<MaterialOrderDO> batchModel2DO(List<MaterialOrder> materialOrders) {
        List<MaterialOrderDO> materialOrderDOS = new ArrayList<>();

        if (materialOrders != null) {
            for (MaterialOrder materialOrder: materialOrders) {
                materialOrderDOS.add(model2Do(materialOrder));
            }
        }

        return materialOrderDOS;
    }

    /**
     * vo 2 model
     * @param materialOrder do
     * @return model
     */
    public static MaterialOrderDO model2Do(MaterialOrder materialOrder) {
        if (materialOrder == null) {
            return null;
        }

        MaterialOrderDO materialOrderDO = new MaterialOrderDO();

        materialOrderDO.setMaterialOrderId(materialOrder.getMaterialOrderId());
        materialOrderDO.setDonationOrderId(materialOrder.getDonationOrderId());
        materialOrderDO.setMaterialId(materialOrder.getMaterialId());
        materialOrderDO.setMaterialName(materialOrder.getMaterialName());
        materialOrderDO.setMaterialAmount(materialOrder.getMaterialAmount());

        return materialOrderDO;
    }

}
