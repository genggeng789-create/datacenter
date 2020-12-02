package deepblueai.quximart.entity;

public class DashDeviceStatus {
    String registerTime;
    String merchantId;
    String rateOf1stWithin0;
    String rateOf1st;
    String rateOf2ndWithin0;
    String rateOf2ndWithin7;
    String rateOf2ndWithin14;
    String rateOf2ndwithin21;
    String rateOf2nd;
    int registerCount;
    int ifFirst;
    int ifSecond;

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getRateOf1stWithin0() {
        return rateOf1stWithin0;
    }

    public void setRateOf1stWithin0(String rateOf1stWithin0) {
        this.rateOf1stWithin0 = rateOf1stWithin0;
    }

    public String getRateOf1st() {
        return rateOf1st;
    }

    public void setRateOf1st(String rateOf1st) {
        this.rateOf1st = rateOf1st;
    }

    public String getRateOf2ndWithin0() {
        return rateOf2ndWithin0;
    }

    public void setRateOf2ndWithin0(String rateOf2ndWithin0) {
        this.rateOf2ndWithin0 = rateOf2ndWithin0;
    }

    public String getRateOf2ndWithin7() {
        return rateOf2ndWithin7;
    }

    public void setRateOf2ndWithin7(String rateOf2ndWithin7) {
        this.rateOf2ndWithin7 = rateOf2ndWithin7;
    }

    public String getRateOf2ndWithin14() {
        return rateOf2ndWithin14;
    }

    public void setRateOf2ndWithin14(String rateOf2ndWithin14) {
        this.rateOf2ndWithin14 = rateOf2ndWithin14;
    }

    public String getRateOf2ndwithin21() {
        return rateOf2ndwithin21;
    }

    public void setRateOf2ndwithin21(String rateOf2ndwithin21) {
        this.rateOf2ndwithin21 = rateOf2ndwithin21;
    }

    public String getRateOf2nd() {
        return rateOf2nd;
    }

    public void setRateOf2nd(String rateOf2nd) {
        this.rateOf2nd = rateOf2nd;
    }

    public int getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(int registerCount) {
        this.registerCount = registerCount;
    }

    public int getIfFirst() {
        return ifFirst;
    }

    public void setIfFirst(int ifFirst) {
        this.ifFirst = ifFirst;
    }

    public int getIfSecond() {
        return ifSecond;
    }

    public void setIfSecond(int ifSecond) {
        this.ifSecond = ifSecond;
    }

    public String toJson() {
        return "{" +
                "\"registerTime\":\"" + registerTime +"\"" +
                ", \"merchantId\":\"" + merchantId + "\"" +
                ", \"rateOf1stWithin0\":\"" + rateOf1stWithin0 +"\"" +
                ", \"rateOf1st\":\"" + rateOf1st +"\"" +
                ", \"rateOf2ndWithin0\":\"" + rateOf2ndWithin0 +"\"" +
                ", \"rateOf2ndWithin7\":\"" + rateOf2ndWithin7 +"\"" +
                ", \"rateOf2ndWithin14\":\"" + rateOf2ndWithin14 +"\"" +
                ", \"rateOf2ndwithin21\":\"" + rateOf2ndwithin21 +"\"" +
                ", \"rateOf2nd\":\"" + rateOf2nd +"\"" +
                ", \"registerCount\":" + registerCount +
                ", \"ifFirst\":" + ifFirst +
                ", \"ifSecond\":" + ifSecond +
                '}';
    }
}
