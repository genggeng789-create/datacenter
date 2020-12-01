package deepblueai.quximart.entity;

import java.sql.Date;

public class DashDeviceStatus {
    Date registerTime;
    String merchantId;
    Double rateOf1stWithin0;
    Double rateOf1st;
    Double rateOf2ndWithin0;
    Double rateOf2ndWithin7;
    Double rateOf2ndWithin14;
    Double rateOf2ndwithin21;
    Double rateOf2nd;
    Integer registerCount;
    Integer ifFirst;
    Integer ifSecond;

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Double getRateOf1stWithin0() {
        return rateOf1stWithin0;
    }

    public void setRateOf1stWithin0(Double rateOf1stWithin0) {
        this.rateOf1stWithin0 = rateOf1stWithin0;
    }

    public Double getRateOf1st() {
        return rateOf1st;
    }

    public void setRateOf1st(Double rateOf1st) {
        this.rateOf1st = rateOf1st;
    }

    public Double getRateOf2ndWithin0() {
        return rateOf2ndWithin0;
    }

    public void setRateOf2ndWithin0(Double rateOf2ndWithin0) {
        this.rateOf2ndWithin0 = rateOf2ndWithin0;
    }

    public Double getRateOf2ndWithin7() {
        return rateOf2ndWithin7;
    }

    public void setRateOf2ndWithin7(Double rateOf2ndWithin7) {
        this.rateOf2ndWithin7 = rateOf2ndWithin7;
    }

    public Double getRateOf2ndWithin14() {
        return rateOf2ndWithin14;
    }

    public void setRateOf2ndWithin14(Double rateOf2ndWithin14) {
        this.rateOf2ndWithin14 = rateOf2ndWithin14;
    }

    public Double getRateOf2ndwithin21() {
        return rateOf2ndwithin21;
    }

    public void setRateOf2ndwithin21(Double rateOf2ndwithin21) {
        this.rateOf2ndwithin21 = rateOf2ndwithin21;
    }

    public Double getRateOf2nd() {
        return rateOf2nd;
    }

    public void setRateOf2nd(Double rateOf2nd) {
        this.rateOf2nd = rateOf2nd;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getIfFirst() {
        return ifFirst;
    }

    public void setIfFirst(Integer ifFirst) {
        this.ifFirst = ifFirst;
    }

    public Integer getIfSecond() {
        return ifSecond;
    }

    public void setIfSecond(Integer ifSecond) {
        this.ifSecond = ifSecond;
    }
}
