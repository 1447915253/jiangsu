package com.rmkj.microcap.common.modules.trademarket;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by 123 on 2016/10/19.
 */
public class MarketPointBean {
    /**
     * code : 合约代码
     * price : 价格
     * open : 开盘价格
     * close : 收盘价格
     * high : 最高价格
     * low : 最低价格
     * time : 行情时间戳
     */

    private String code;
    private String price;
    private String open;
    private String close;
    private String high;
    private String low;
    private Date time;
    private long timestamp;

    public String getCode() {
        return code;
    }

    @JSONField(name="Code")
    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    @JSONField(name="Price")
    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpen() {
        return open;
    }

    @JSONField(name="Open")
    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    @JSONField(name="Close")
    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    @JSONField(name="High")
    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    @JSONField(name="Low")
    public void setLow(String low) {
        this.low = low;
    }

    public Date getTime() {
        return time;
    }

    @JSONField(name="AddTime")
    public void setTime(Date time) {
        this.time = time;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @JSONField(name="QuoteTime")
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
