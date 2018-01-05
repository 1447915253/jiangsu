package com.rmkj.microcap.common.modules.pay.yizhifu.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/12/30.
 */
@HttpApi(value = "https://pay.yizhifubj.com/")
public interface ShouXinYiApi {
    @FormUrlEncoded
    @POST(value = "customer/otherpay/scanCodePay.jsp")
    Call<String> createOrder(@Field("v_mid") String v_mid, @Field("v_oid") String v_oid, @Field("v_rcvname") String v_rcvname,
                             @Field("v_rcvaddr") String v_rcvaddr, @Field("v_rcvtel") String v_rcvtel, @Field("v_rcvpost") String v_rcvpost,
                             @Field("v_amount") String v_amount, @Field("v_ymd") String v_ymd, @Field("v_orderstatus") String v_orderstatus,
                             @Field("v_ordername") String v_ordername, @Field("v_moneytype") String v_moneytype, @Field("v_url") String v_url,
                             @Field("v_md5info") String v_md5info, @Field("v_ordip") String v_ordip, @Field("v_merdata5") String v_merdata5,
                             @Field("v_pmode") String v_pmode);
    @FormUrlEncoded
    @POST(value = "merchant/order/order_ack_oid_list.jsp")
    Call<String> selectPayResult(@Field("v_mid") String v_mid, @Field("v_oid") String v_oid, @Field("v_mac") String v_mac);

    @FormUrlEncoded
    @POST(value = "customer/gb/pay_bank.jsp")
    Call<String> createBankPayOrder();

    @FormUrlEncoded
    @POST(value = "prs/user_payment.checkit")
    Call<String> recievePayOrderResult();
}
