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

    private String status;

    private Integer deleted;

    private String lovePoolStatus;

    private Integer userId;

    public DonationOrderCondition(Integer donationOrderId, Integer donorId, String donationType, Integer doneeId, String status, Integer deleted, String lovePoolStatus, Integer userId) {
        this.donationOrderId = donationOrderId;
        this.donorId = donorId;
        this.donationType = donationType;
        this.doneeId = doneeId;
        this.status = status;
        this.deleted = deleted;
        this.lovePoolStatus = lovePoolStatus;
        this.userId = userId;

    }

    public static final class Builder {

        private Integer donationOrderId;

        private Integer donorId;

        private String donationType;

        private Integer doneeId;

        private String status;

        private Integer deleted;

        private String lovePoolStatus;

        private Integer userId;

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
        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public DonationOrderCondition build() {
            return new DonationOrderCondition(this.donationOrderId, this.donorId, this.donationType, this.doneeId, this.status, this.deleted, this.lovePoolStatus, this.userId);
        }
    }

}
