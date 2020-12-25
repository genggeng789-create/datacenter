package com.deepblue.SDKServer.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.impl.HopeRequest;
import com.deepblue.SDKServer.impl.HopeResponse;
import com.deepblue.SDKServer.service.UserService;
import com.deepblue.SDKServer.tools.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class ApiAbstract<R> {
    @Autowired
    UserService userService;

    /**
     * 公钥
     * */
    //private static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCllRJyNyA5/kOKpF+VV322IN7fownz5GMltjnWLHJPE+xdusVYHz/3C0ck27sv7mHP0TrJ7PLxUHyeUJ9PGOZ2fyrBRikKNE4ce1ihNgQxorIJ68G+70eHyOr65mQxRYa4lUOHMMPHgicN/2vGCjwL/ET8eQU0yIRAoOnO8avAuQIDAQAB";
    //private static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBBWabwUP2izzyRHF4EF2u3W2TJDIde+bZt5cO+PuxliKhp+zvAZ46WTmLDohj1tDgJeDayNrLpRvRLQTYvVaM3sO8lHOi1edk+ssURhg/YZyrJw/6FWrpfF9IpPWZ0oR0KbpHoEPKhoYp/1X5YdvTdf+cwz/3sImCWdgkdLesbwIDAQAB";
    private String PUBLIC_KEY_STRING;
    public void setPublicKey(String userName){
        this.PUBLIC_KEY_STRING = userService.queryPublicKey(userName);
    }

    /**
     * 统一处理
     *
     * @param hopeRequest 统一请求
     * @return 统一响应
     */
    public HopeResponse process(HopeRequest hopeRequest) {
        try {
            log.info(hopeRequest.getAppId());
            setPublicKey(hopeRequest.getAppId());
            JSONObject o = before(hopeRequest.getData(), PUBLIC_KEY_STRING);
            System.out.println(o);
            R r = work(o);
            return after(r, PUBLIC_KEY_STRING);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ServerAbstract process exception! hopeRequest={}", hopeRequest, e);
            return HopeResponse.builder()
                    .success(false)
                    .message("系统异常")
                    .build();
        }
    }

    /**
     * 处理前置解密
     *
     * @param data       请求密文
     * @param publicKey  公钥
     * @return 请求参数
     */
    private JSONObject before(String data, String publicKey) {
        return JSON.parseObject(RsaUtil.decrypt(publicKey, data));
    }

    /**
     * 业务处理
     *
     * @param o 入参
     * @return 返回
     */
    public abstract R work(JSONObject o);

    /**
     * 后置处理加密
     *
     * @param r          业务数据
     * @param publicKey 公钥
     * @return 统一返回
     */
    private HopeResponse after(R r, String publicKey) {
        return HopeResponse.builder()
                .success(true)
                .message("交易成功")
                .data(RsaUtil.encrypt(publicKey, JSON.toJSONString(r)))
                .build();
    }

    /**
     * 通过appId获取对应的私钥，不同的接入方提供不同的公私钥。
     * 实际业务开发中这些会存在文件中或者配置中心中如阿波罗，这里简单实现
     *
     * @param appId appId
     * @return 私钥
     */
//    private String getPrivateKey(String appId) {
//        return PRIVATE_KEY_MAP.get(appId);
//    }

}
