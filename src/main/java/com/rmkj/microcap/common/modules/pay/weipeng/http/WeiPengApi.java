package com.rmkj.microcap.common.modules.pay.weipeng.http;/**
 * Created by Administrator on 2017/3/6.
 */

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author k
 * @create -03-06-15:48
 **/
@HttpApi(value = "http://139.224.61.115:3030/hmpay/online/")
public interface WeiPengApi {

    /**
     * TODO 获取威鹏支付扫码下单信息
     * @param merchant_no 商户号
     * @param total_fee 金额
     * @param pay_num 商户自己系统订单号
     * @param sign 签名
     * @param notifyurl 异步通知
     * @param productname 商品名称
     * @return
     */
    @FormUrlEncoded
    @POST(value = "createYyWxOrderD0.do")
    Call<String> getCreateYyWxOrderD0Info(@Field("merchant_no") String merchant_no, @Field("total_fee") String total_fee, @Field("pay_num") String pay_num,
                                          @Field("sign")String sign, @Field("notifyurl") String notifyurl, @Field("productname")String productname);
    @FormUrlEncoded
    @POST(value = "createYyWxOrderD0.do")
    Call<String> getCreateYyWxOrderD0InfoMap(@FieldMap Map<String, String> result);

    /**
     * 微信H5支付
     * @param result
     * @return
     */
    @FormUrlEncoded
    @POST(value = "createWxH5.do")
    Call<String> getCreateWxH5InfoMap(@FieldMap Map<String, String> result);
}
