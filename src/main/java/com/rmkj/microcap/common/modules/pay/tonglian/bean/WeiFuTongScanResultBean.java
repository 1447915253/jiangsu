package com.rmkj.microcap.common.modules.pay.tonglian.bean;/**
 * Created by Administrator on 2017/3/20.
 */

/**
 * TODO 通联微信扫码支付返回结果
 * @author k
 * @create -03-20-15:12
 **/

public class WeiFuTongScanResultBean {
    //版本号
    private String version;
    //字符集
    private String charset;
    //签名方式
    private String sign_type;
    //返回状态码
    private String status;
    //返回信息
    private String message;
    /**
     * 以下字段在 status 为 0 的时候有返回
     */
    //业务结果
    private String result_code;
    //商户号
    private String mch_id;
    //设备号
    private String device_info;
    //随机字符串
    private String nonce_str;
    //错误代码
    private String err_code;
    //错误代码描述
    private String err_msg;
    //签名
    private String sign;
    //以下字段在 status 和 result_code 都为 0 的时候有返回
    //二维码链接
    private String code_url;
    //二维码图片
    private String code_img_url;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getCode_img_url() {
        return code_img_url;
    }

    public void setCode_img_url(String code_img_url) {
        this.code_img_url = code_img_url;
    }
}
