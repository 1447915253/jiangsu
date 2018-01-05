package com.rmkj.microcap.common.modules.trademarket.http;

import com.rmkj.microcap.common.modules.retrofit.annotation.HttpApi;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by 123 on 2016/10/13.
 */

@HttpApi(value = "http://open.icairon.com/Api/")
public interface MarketHttpApi {

    @GET("GetPriceData")
    Call<String> getPrice(@Query("Uname") String uname, @Query("Upass") String upass, @Query("Code") String code);

    @GET("GetChartData")
    Call<String> getChart(@Query("Uname") String uname, @Query("Upass") String upass, @Query("Code") String code,
                          @Query("Interval") String interval, @Query("Rows") String rows);
}
