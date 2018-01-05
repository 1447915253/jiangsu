package com.rmkj.microcap.common.modules.pay.tonglian.http;/**
 * Created by Administrator on 2017/3/20.
 */

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import org.apache.http.entity.StringEntity;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author k
 * @create -03-20-10:59
 **/
@HttpApi(value = "https://pay.swiftpass.cn")
public interface WeiFuTongScanPayApi {

    /**
     * TODO 微信扫码支付
     * @param xml
     * @return
     */
    @FormUrlEncoded
    @POST(value = "/pay/gateway")
    Call<String> geteway(@Field("xml") StringEntity xml);
}
