package com.rmkj.microcap.modules.trade.http;

/**
 * Created by Administrator on 2017/3/1.
 */

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

@HttpApi(value = "trade_url")
public interface MrGaoApi {
    @POST("insertOrder.php")
    Call<String> mrGaoReq(@Query("direction") String direction, @Query("money") String money, @Query("code") String code,
                          @Query("StartPoint") String StartPoint, @Query("orderno") String orderno, @Query("userid") String userid,
                          @Query("tradingTime") String tradingTime, @Query("buyTime") String buyTime);
}
