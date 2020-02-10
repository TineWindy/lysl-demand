package com.whu.lysl.entity.condition;

import lombok.Data;

/**
 * 需求查询
 * @author Powen
 */
@Data
public class DemandCondition {

    private Integer id;

    private String Recipient;

    private String status;

    public DemandCondition(Integer id, String Recipient, String status) {
        this.id = id;
        this.Recipient = Recipient;
        this.status = status;
    }

    public static final class Builder {
        private Integer id;

        private String Recipient;

        private String status;

        public DemandCondition.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public DemandCondition.Builder Recipient(String Recipient) {
            this.Recipient = Recipient;
            return this;
        }

        public DemandCondition.Builder status(String status) {
            this.status = status;
            return this;
        }

        public DemandCondition build() {
            return new DemandCondition(this.id, this.Recipient, this.status);
        }
    }
}
