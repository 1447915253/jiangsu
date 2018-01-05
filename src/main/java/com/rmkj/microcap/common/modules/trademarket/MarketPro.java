package com.rmkj.microcap.common.modules.trademarket;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by renwp on 2017/4/19.
 */
public interface MarketPro {

    String[] reqIntervalsParams();

    String filterNumber(Object obj, Integer precision);

    MarketPointBean toMarketPointBean(JSONObject obj, Integer precision);

}
