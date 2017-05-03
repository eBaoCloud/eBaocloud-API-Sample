package com.ebaocloud.sample.comparequotes;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Guo Rui on 5/3/17.
 */
public class AggregatorPlan {
    private String insurerTenantcode;
    private String productCode;
    private String prdtVer;
    private String planCode;
    private Map benefit;
    private Premium premium;

    public String getInsurerTenantcode() {
        return insurerTenantcode;
    }

    public void setInsurerTenantcode(String insurerTenantcode) {
        this.insurerTenantcode = insurerTenantcode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPrdtVer() {
        return prdtVer;
    }

    public void setPrdtVer(String prdtVer) {
        this.prdtVer = prdtVer;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public Map getBenefit() {
        return benefit;
    }

    public void setBenefit(Map benefit) {
        this.benefit = benefit;
    }

    public Premium getPremium() {
        return premium;
    }

    public void setPremium(Premium premium) {
        this.premium = premium;
    }


    static final class Premium {
        private boolean uwNeeded = false;
        private BigDecimal sgp;
        private BigDecimal agp;
        private BigDecimal snp;
        private BigDecimal anp;
        private BigDecimal totalDiscountAmt;
        private BigDecimal totalLoadingAmt;
        private BigDecimal app;

        public boolean isUwNeeded() {
            return uwNeeded;
        }

        public void setUwNeeded(boolean uwNeeded) {
            this.uwNeeded = uwNeeded;
        }

        public BigDecimal getSgp() {
            return sgp;
        }

        public void setSgp(BigDecimal sgp) {
            this.sgp = sgp;
        }

        public BigDecimal getAgp() {
            return agp;
        }

        public void setAgp(BigDecimal agp) {
            this.agp = agp;
        }

        public BigDecimal getSnp() {
            return snp;
        }

        public void setSnp(BigDecimal snp) {
            this.snp = snp;
        }

        public BigDecimal getAnp() {
            return anp;
        }

        public void setAnp(BigDecimal anp) {
            this.anp = anp;
        }

        public BigDecimal getTotalDiscountAmt() {
            return totalDiscountAmt;
        }

        public void setTotalDiscountAmt(BigDecimal totalDiscountAmt) {
            this.totalDiscountAmt = totalDiscountAmt;
        }

        public BigDecimal getTotalLoadingAmt() {
            return totalLoadingAmt;
        }

        public void setTotalLoadingAmt(BigDecimal totalLoadingAmt) {
            this.totalLoadingAmt = totalLoadingAmt;
        }

        public BigDecimal getApp() {
            return app;
        }

        public void setApp(BigDecimal app) {
            this.app = app;
        }
    }
}
