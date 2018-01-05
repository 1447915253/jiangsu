package com.rmkj.microcap.common.modules.pay.shande.http;

import com.rmkj.microcap.common.modules.pay.shande.bean.ShanDeH5CommonReqBean;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/21.
 */
@HttpApi(value = "https://shq-api.51fbei.com/")
//@HttpApi(value = "http://shq-api.51fpay.com/")
public interface ShanDeH5Api {

    @POST(value = "gateway")
    Call<String> createShanDeH5Order(@Body ShanDeH5CommonReqBean shanDeH5CommonReqBean);

    @POST(value = "gateway")
    Call<String> shanDeH5Pay(@Body ShanDeH5CommonReqBean shanDeH5CommonReqBean);


}
