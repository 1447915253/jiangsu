package com.rmkj.microcap.common.bean;

/**
 * Created by zhangbowen on 2015/12/25.
 */
public class AuthEntity {
    //请求用户
    private String userId;
    //请求token
    private String token;
    //请求设备类型（1.android 2.ios 3.wap）
    private String terminal;
    //请求版本code
    private String version;
    //加密后的key
    private String authKey;
    //时间戳
    private String timestamp;
    //ip
    private String ip;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

}
