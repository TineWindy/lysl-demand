package com.whu.lysl.entity.condition;

import lombok.Data;

@Data
public class DonationOrderCondition {

    private Integer donationOrderId;

    private Integer donorId;

    private String donorName;

    private Integer donationType;

    private Integer doneeId;

    private String doneeName;

    private Integer materialId;

    private String materialName;

    private Integer materialAmount;

    private String status;

    private Integer deleted;

    public DonationOrderCondition(Integer donationOrderId, Integer donorId, Integer donationType, Integer doneeId, Integer materialId, String status, Integer deleted) {
        this.donationOrderId = donationOrderId;
        this.donorId = donorId;
        this.donationType = donationType;
        this.doneeId = doneeId;
        this.materialId = materialId;
        this.status = status;
        this.deleted = deleted;
    }

    public static final class Builder {

        private Integer donationOrderId;

        private Integer donorId;

        private Integer donationType;

        private Integer doneeId;

        private Integer materialId;

        private String status;

        private Integer deleted;

        public Builder donationOrderId(Integer donationOrderId) {
            this.donationOrderId = donationOrderId;
            return this;
        }

        public Builder donorId(Integer donorId) {
            this.donorId = donorId;
            return this;
        }

        public Builder donationType(Integer donationType) {
            this.donationType = donationType;
            return this;
        }

        public Builder doneeId(Integer doneeId) {
            this.doneeId = doneeId;
            return this;
        }

        public Builder materialId(Integer materialId) {
            this.materialId = materialId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder deleted(Integer deleted) {
            this.deleted = deleted;
            return this;
        }

        public DonationOrderCondition build() {
            return new DonationOrderCondition(this.donationOrderId, this.donorId, this.donationType, this.doneeId, this.materialId, this.status, this.deleted);
        }
    }

}
