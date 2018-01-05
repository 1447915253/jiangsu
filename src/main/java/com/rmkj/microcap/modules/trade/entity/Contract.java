package com.rmkj.microcap.modules.trade.entity;

import com.rmkj.microcap.common.bean.DataEntity;
import com.rmkj.microcap.common.utils.Utils;

/**
 * Created by 123 on 2016/10/19.
 */
public class Contract extends DataEntity {

    /**
     * name : 合约名称
     * code : 合约代码
     * status : 状态：0 正常 1 关闭
     * stepMoneys : 购买金额 格式如下 10,50,100,200,500,2000（英文逗号）
     * pointMoneys : 波动一个点，盈亏多少钱 （同购买金额）
     * fees : 手续费（同购买金额）
     * profitPercentages : 盈利百分比
     * lossPercentages : 亏损百分比
     * precision: 点位精度
     */

    private String name;
    private String code;
    private Integer orderNo;
    private Integer status;
    private String stepMoneys;
    private String pointMoneys;
    private String fees;
    private String profitPercentages;
    private String lossPercentages;

    private String offTimes;
    private String percentProfits;

    private String[] _stepMoneys;
    private String[] _pointMoneys;
    private String[] _fees;

    private String[] _offTimes;
    private String[] _percentProfits;

    private boolean isMarketOpen;

    public boolean isMarketOpen() {
        return isMarketOpen;
    }

    public void setMarketOpen(boolean marketOpen) {
        isMarketOpen = marketOpen;
    }

    private Integer precision;

    /**
     * 开始时间 08:00
     */
    private String beginTime;
    /**
     * 结束时间 20:00
     */
    private String endTime;

    private Integer exemptClosed;

    public String fee(String money){
        return Utils.samePosition(_stepMoneys, _fees, money);
    }

    public String pointMoney(String money){
        return Utils.samePosition(_stepMoneys, _pointMoneys, money);
    }

    public String percentProfit(String offTime){
        return Utils.samePosition(_offTimes, _percentProfits, offTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStepMoneys() {
        return stepMoneys;
    }

    public void setStepMoneys(String stepMoneys) {
        this.stepMoneys = stepMoneys;
        this._stepMoneys = stepMoneys.split(",");
    }

    public String getPointMoneys() {
        return pointMoneys;
    }

    public void setPointMoneys(String pointMoneys) {
        this.pointMoneys = pointMoneys;
        this._pointMoneys = pointMoneys.split(",");
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
        this._fees = fees.split(",");
    }

    public String getProfitPercentages() {
        return profitPercentages;
    }

    public void setProfitPercentages(String profitPercentages) {
        this.profitPercentages = profitPercentages;
    }

    public String getLossPercentages() {
        return lossPercentages;
    }

    public void setLossPercentages(String lossPercentages) {
        this.lossPercentages = lossPercentages;
    }

    public String getOffTimes() {
        return offTimes;
    }

    public void setOffTimes(String offTimes) {
        this.offTimes = offTimes;
        this._offTimes = offTimes.split(",");
    }

    public String getPercentProfits() {
        return percentProfits;
    }

    public void setPercentProfits(String percentProfits) {
        this.percentProfits = percentProfits;
        this._percentProfits = percentProfits.split(",");
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getExemptClosed() {
        return exemptClosed;
    }

    public void setExemptClosed(Integer exemptClosed) {
        this.exemptClosed = exemptClosed;
    }
}
