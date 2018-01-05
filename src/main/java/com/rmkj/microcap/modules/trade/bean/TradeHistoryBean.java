package com.rmkj.microcap.modules.trade.bean;

/**
 * Created by Administrator on 2016/12/8.
 */
public class TradeHistoryBean {
    private String userId;
    //查询时间
    private String  selDate;

    //合约名称
    private String contractName;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSelDate() {
        return selDate;
    }

    public void setSelDate(String selDate) {
        this.selDate = selDate;
    }
}
