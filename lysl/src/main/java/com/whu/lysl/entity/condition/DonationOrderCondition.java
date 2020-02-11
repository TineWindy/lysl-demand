package com.whu.lysl.entity.condition;

import lombok.Data;

@Data
public class DonationOrderCondition {

    private Integer donationOrderId;

    private Integer donorId;

    private String donorName;

    private String donationType;

    private Integer doneeId;

    private String doneeName;

    private Integer materialId;

    private String materialName;

    private Integer materialAmount;

    private String status;

    private Integer deleted;

    private String lovePoolStatus;

    public DonationOrderCondition(Integer donationOrderId, Integer donorId, String donationType, Integer doneeId, Integer materialId, String status, Integer deleted, String lovePoolStatus) {
        this.donationOrderId = donationOrderId;
        this.donorId = donorId;
        this.donationType = donationType;
        this.doneeId = doneeId;
        this.materialId = materialId;
        this.status = status;
        this.deleted = deleted;
        this.lovePoolStatus = lovePoolStatus;
    }

    public static final class Builder {

        private Integer donationOrderId;

        private Integer donorId;

        private String donationType;

        private Integer doneeId;

        private Integer materialId;

        private String status;

        private Integer deleted;

        private String lovePoolStatus;

        public Builder donationOrderId(Integer donationOrderId) {
            this.donationOrderId = donationOrderId;
            return this;
        }

        public Builder donorId(Integer donorId) {
            this.donorId = donorId;
            return this;
        }

        public Builder donationType(String donationType) {
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

        public Builder lovePoolStatus(String lovePoolStatus) {
            this.lovePoolStatus = lovePoolStatus;
            return this;
        }

        public DonationOrderCondition build() {
            return new DonationOrderCondition(this.donationOrderId, this.donorId, this.donationType, this.doneeId, this.materialId, this.status, this.deleted, this.lovePoolStatus);
        }
    }

}
