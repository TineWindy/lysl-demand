package com.whu.lysl.entity.condition;

import com.whu.lysl.entity.dto.MaterialOrder;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/12 16:15
 * @Description:
 */
public class MaterialOrderCondition {

    private Integer materialOrderId;

    private Integer donationOrderId;

    private String materialName;

    private Integer materialId;

    private Integer materialAmount;

    public MaterialOrderCondition(Integer materialOrderId, Integer donationOrderId, Integer materialId, String materialName, Integer materialAmount) {
        this.materialOrderId = materialOrderId;
        this.donationOrderId = donationOrderId;
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialAmount = materialAmount;
    }

    public static final class Builder {
        private Integer materialOrderId;

        private Integer donationOrderId;

        private Integer materialId;

        private String materialName;

        private Integer materialAmount;

        public MaterialOrderCondition.Builder maaterialOrderId(Integer materialOrderId) {
            this.materialOrderId = materialOrderId;
            return this;
        }

        public MaterialOrderCondition.Builder donationOrderId(Integer donationOrderId) {
            this.donationOrderId = donationOrderId;
            return this;
        }

        public MaterialOrderCondition.Builder materialId(Integer materialId) {
            this.materialId = materialId;
            return this;
        }

        public MaterialOrderCondition.Builder materialName(String materialName) {
            this.materialName = materialName;
            return this;
        }

        public MaterialOrderCondition.Builder materialAmount(Integer materialAmount) {
            this.materialAmount = materialAmount;
            return this;
        }

        public MaterialOrderCondition build() {
            return new MaterialOrderCondition(
                    this.materialOrderId, this.donationOrderId, this.materialId
                  , this.materialName, this.materialAmount);
        }
    }

}
