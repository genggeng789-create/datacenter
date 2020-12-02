package deepblueai.quximart.entity;

public class DashDeviceStatus {
    String deviceCode;
    String problemStatus1;
    String problemStatus2;
    String problemStatus3;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getProblemStatus1() {
        return problemStatus1;
    }

    public void setProblemStatus1(String problemStatus1) {
        this.problemStatus1 = problemStatus1;
    }

    public String getProblemStatus2() {
        return problemStatus2;
    }

    public void setProblemStatus2(String problemStatus2) {
        this.problemStatus2 = problemStatus2;
    }

    public String getProblemStatus3() {
        return problemStatus3;
    }

    public void setProblemStatus3(String problemStatus3) {
        this.problemStatus3 = problemStatus3;
    }

    public String toJson() {
        return "{" +
                "deviceCode:\"" + deviceCode + '\"' +
                ", problemStatus1:\"" + problemStatus1 + '\"' +
                ", problemStatus2:\"" + problemStatus2 + '\"' +
                ", problemStatus3:\"" + problemStatus3 + '\"' +
                '}';
    }

}
