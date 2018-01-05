package com.rmkj.microcap.modules.trade.entity;/**
 * Created by Administrator on 2017/4/25.
 */

import com.rmkj.microcap.common.bean.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员单位保证金浮动记录表
 * @author k
 * @create -04-25-16:55
 **/

public class Ml3MemberUnitsMoneyChange extends DataEntity{
//    `units_id` varchar(32) DEFAULT NULL COMMENT '',
//    `units_name` varchar(600) DEFAULT NULL COMMENT '',
//    `dif_money` decimal(18,2) DEFAULT NULL COMMENT '',
//    `before_money` decimal(18,2) DEFAULT NULL COMMENT '',
//    `after_money` decimal(18,2) DEFAULT NULL COMMENT '',
//    `create_time` datetime DEFAULT NULL COMMENT '',
//    `end_time` datetime DEFAULT NULL COMMENT '',
//    `remark` varchar(500) DEFAULT NULL COMMENT '',
    //会员单位id
    private String unitsId;
    //会员单位名称
    private String unitsName;
    //盈亏金额
    private BigDecimal difMoney;
    //计算前金额
    private BigDecimal beforeMoney;
    //计算后金额
    private BigDecimal afterMoney;
    //计算开始时间
    private Date createTime;
    //计算结束时间
    private Date endTime;
    //备注
    private String remark;
    //保证金流水状态 1：浮动保证金 2：追加保证金
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public BigDecimal getDifMoney() {
        return difMoney;
    }

    public void setDifMoney(BigDecimal difMoney) {
        this.difMoney = difMoney;
    }

    public BigDecimal getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(BigDecimal beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public BigDecimal getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(BigDecimal afterMoney) {
        this.afterMoney = afterMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
