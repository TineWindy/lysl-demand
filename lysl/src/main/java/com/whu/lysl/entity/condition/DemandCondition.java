package com.whu.lysl.entity.condition;

import lombok.Data;

/**
 * 需求查询
 * @author Powen
 */
@Data
public class DemandCondition {

    private String demandId;

    private String status;

    private Integer institutionId;

    public DemandCondition(String demandId, String status, Integer institutionId) {
        this.demandId = demandId;
        this.status = status;
        this.institutionId = institutionId;
    }

    public DemandCondition() {
    }

    public static final class Builder {
        private Integer institutionId;

        private String demandId;

        private String status;

        public DemandCondition.Builder institutionId(Integer institutionId) {
            this.institutionId = institutionId;
            return this;
        }

        public DemandCondition.Builder demandId(String demandId) {
            this.demandId = demandId;
            return this;
        }

        public DemandCondition.Builder status(String status) {
            this.status = status;
            return this;
        }

        public DemandCondition build() {
            return new DemandCondition(this.demandId, this.status, this.institutionId);
        }
    }
}
