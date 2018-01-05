package com.rmkj.microcap.modules.weChatPublic.entity;

import com.rmkj.microcap.common.bean.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by 123 on 2017/2/11.
 */
public class WeChatPublic extends DataEntity {
    private String id;
    @NotBlank
    private String name;

    private String agentInviteCode;

    @NotBlank
    private String appId;
    private String secret;
    @NotBlank
    private String domainName;
    @NotBlank
    private String status;

    private String accessToken;

    private Date accessTokenExpireTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentInviteCode() {
        return agentInviteCode;
    }

    public void setAgentInviteCode(String agentInviteCode) {
        this.agentInviteCode = agentInviteCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public void setAccessTokenExpireTime(Date accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }
}
