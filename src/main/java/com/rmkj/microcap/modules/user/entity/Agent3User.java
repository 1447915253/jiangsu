package com.rmkj.microcap.modules.user.entity;

/**
 * Created by 123 on 2016/11/17.
 */
public class Agent3User extends User {

    private String agentId;
    private String unitsId;
    private String centerId;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}
