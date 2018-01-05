package com.rmkj.microcap.common.modules.pay.tonglian.bean;/**
 * Created by Administrator on 2017/3/20.
 */

/**
 * TODO　通联微信扫码支付
 * @author k
 * @create -03-20-14:43
 **/

public class WeiFuTongScanCodeBean {

    //接口类型
    private String service;
    //版本号   版本号， version 默认值是 2.0
    private String version;
    //字符集   可选值 UTF-8 ， 默认为 UTF-8
    private String charset;
    //签名方式  签名类型， 取值： MD5 默认： MD5
    private String sign_type;
    //商户号
    private String mch_id;
    //授权渠道编号;
    private String sign_agentno;
    //商户订单号
    private String out_trade_no;
    //设备号
    private String device_info;
    //商品描述
    private String body;
    //附加信息
    private String attach;
    //总金额
    private Integer total_fee;
    //终端
    private String mch_create_ip;
    //通知地址
    private String notify_url;
    //订单生成时间
    private String time_start;
    //订单超时时间
    private String time_expire;
    //操作员
    private String op_user_id;
    //商品标记
    private String goods_tag;
    //商品 ID预留字段  ,此 id 为静态可打印的二维码中包含的商户ID， 商户自行维护。
    private String product_id;
    //随机字符串
    private String nonce_str;
    //签名
    private String sign;

    //限定用户使用微信支付时能否使用信用卡，值为1，禁用信用卡；值为0或者不传此参数则不禁用
    private String limit_credit_pay;

    public String getLimit_credit_pay() {
        return limit_credit_pay;
    }

    public void setLimit_credit_pay(String limit_credit_pay) {
        this.limit_credit_pay = limit_credit_pay;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

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

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign_agentno() {
        return sign_agentno;
    }

    public void setSign_agentno(String sign_agentno) {
        this.sign_agentno = sign_agentno;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getMch_create_ip() {
        return mch_create_ip;
    }

    public void setMch_create_ip(String mch_create_ip) {
        this.mch_create_ip = mch_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
