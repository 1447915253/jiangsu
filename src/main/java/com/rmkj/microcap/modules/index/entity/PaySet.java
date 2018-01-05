package com.rmkj.microcap.modules.index.entity;/**
 * Created by Administrator on 2017/3/22.
 */

import com.rmkj.microcap.common.bean.DataEntity;

/**
 * @author k
 * @create -03-22-12:20
 **/

public class PaySet extends DataEntity{

    /**
     * `id` varchar(32) NOT NULL COMMENT 'id',
     `wei_fu_tong_pay_mch_id` varchar(32) DEFAULT NULL COMMENT '威富通扫码支付商户号',
     `wei_fu_tong_pay_key` varchar(255) NOT NULL COMMENT '威富通扫码支付秘钥',
     `wei_fu_tong_no` varchar(255) DEFAULT NULL,
     `wei_fu_tong_name` varchar(255) DEFAULT NULL,
     `wei_fu_tong_key` varchar(255) NOT NULL,
     `wei_fu_tong_business_code` varchar(255) DEFAULT NULL,
     `wei_fu_tong_private_key_cert` varchar(255) DEFAULT NULL COMMENT '威富通代付私钥',
     `wei_fu_tong_public_key_cert` varchar(255) DEFAULT NULL COMMENT '威富通代付公钥',
     */
    //威富通扫码支付商户号
    private String weiFuTongPayMchId;
    //威富通扫码支付秘钥
    private String weiFuTongPayKey;

    private String weiFuTongNo;

    private String weiFuTongName;

    private String weiFutongKey;

    private String weiFuTongBusinessCode;
    //威富通代付私钥
    private String weiFuTongPrivateKeyCert;
    //威富通代付公钥
    private String weiFuTongPublicKeyCert;


    public String getWeiFuTongPayMchId() {
        return weiFuTongPayMchId;
    }

    public void setWeiFuTongPayMchId(String weiFuTongPayMchId) {
        this.weiFuTongPayMchId = weiFuTongPayMchId;
    }

    public String getWeiFuTongPayKey() {
        return weiFuTongPayKey;
    }

    public void setWeiFuTongPayKey(String weiFuTongPayKey) {
        this.weiFuTongPayKey = weiFuTongPayKey;
    }

    public String getWeiFuTongNo() {
        return weiFuTongNo;
    }

    public void setWeiFuTongNo(String weiFuTongNo) {
        this.weiFuTongNo = weiFuTongNo;
    }

    public String getWeiFuTongName() {
        return weiFuTongName;
    }

    public void setWeiFuTongName(String weiFuTongName) {
        this.weiFuTongName = weiFuTongName;
    }

    public String getWeiFutongKey() {
        return weiFutongKey;
    }

    public void setWeiFutongKey(String weiFutongKey) {
        this.weiFutongKey = weiFutongKey;
    }

    public String getWeiFuTongBusinessCode() {
        return weiFuTongBusinessCode;
    }

    public void setWeiFuTongBusinessCode(String weiFuTongBusinessCode) {
        this.weiFuTongBusinessCode = weiFuTongBusinessCode;
    }

    public String getWeiFuTongPrivateKeyCert() {
        return weiFuTongPrivateKeyCert;
    }

    public void setWeiFuTongPrivateKeyCert(String weiFuTongPrivateKeyCert) {
        this.weiFuTongPrivateKeyCert = weiFuTongPrivateKeyCert;
    }

    public String getWeiFuTongPublicKeyCert() {
        return weiFuTongPublicKeyCert;
    }

    public void setWeiFuTongPublicKeyCert(String weiFuTongPublicKeyCert) {
        this.weiFuTongPublicKeyCert = weiFuTongPublicKeyCert;
    }
}
