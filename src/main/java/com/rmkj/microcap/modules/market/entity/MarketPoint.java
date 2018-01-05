package com.rmkj.microcap.modules.market.entity;

import java.util.Date;

/**
 * Created by 123 on 2016/10/19.
 */
public class MarketPoint {
    private String id;
    private String code;
    private Integer price;
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
