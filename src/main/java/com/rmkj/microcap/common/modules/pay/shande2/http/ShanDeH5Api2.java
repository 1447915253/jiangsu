package com.rmkj.microcap.common.modules.pay.shande2.http;

import com.rmkj.microcap.common.modules.pay.shande2.bean.ShanDeH5CommonReqBean2;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/21.
 */
@HttpApi(value = "https://shq-api.51fubei.com/")
public interface ShanDeH5Api2 {

    @POST(value = "gateway")
    Call<String> createShanDeH5Order(@Body ShanDeH5CommonReqBean2 shanDeH5CommonReqBean);

    @POST(value = "gateway")
    Call<String> shanDeH5Pay(@Body ShanDeH5CommonReqBean2 shanDeH5CommonReqBean);
}
