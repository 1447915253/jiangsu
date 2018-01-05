package com.rmkj.microcap.modules.corps.bean;

import java.util.Date;

/**
 * Created by 123 on 2016/11/23.
 */
public class CorpsMoneyWithTime {

    private Date startTime;
    private Date endTime;
    private String userId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
