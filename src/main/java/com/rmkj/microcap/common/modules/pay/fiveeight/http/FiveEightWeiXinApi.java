package com.rmkj.microcap.common.modules.pay.fiveeight.http;

import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean.FiveEightOpenPayReqByServiceBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayReqBean;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import com.rmkj.microcap.common.modules.weixin.bean.ResponseToken;
import com.rmkj.microcap.common.modules.weixin.bean.TicketBean;
import com.rmkj.microcap.common.modules.weixin.bean.WeiXinUserInfo;
import com.rmkj.microcap.common.modules.weixin.http.interceptor.WeiXinInterceptor;
import retrofit2.Call;
import retrofit2.http.*;

@HttpApi(value = "http://pay.openapi.net.cn")
public interface FiveEightWeiXinApi {
    /**
     * 58支付 微信openPay
     */
    @POST(value = "/aa")
    Call<String> createOrder(@Body FiveEightOpenPayReqBean fiveEightOpenPayReqBean);

    /**
     * 58支付 微信openPay 接口访问
     */
    @POST(value = "/")
    Call<String> createOrderByService(@Body FiveEightOpenPayReqByServiceBean fiveEightOpenPayReqByServiceBean);
}
