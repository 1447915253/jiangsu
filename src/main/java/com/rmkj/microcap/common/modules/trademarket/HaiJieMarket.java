package com.rmkj.microcap.common.modules.trademarket;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by renwp on 2017/4/19.
 */
public class HaiJieMarket implements MarketPro {

    @Override
    public String[] reqIntervalsParams() {
        return new String[]{"1", "5", "15", "30", "1h", "1d"};
    }

    @Override
    public String filterNumber(Object obj, Integer precision){
        return Double.parseDouble(obj.toString())*1 + "";
    }

    @Override
    public MarketPointBean toMarketPointBean(JSONObject obj, Integer precision) {
        MarketPointBean marketPointBean = new MarketPointBean();
        marketPointBean.setPrice(filterNumber(obj.getString("Price"), precision));
        marketPointBean.setClose(filterNumber(obj.getString("Close"), precision));
        marketPointBean.setOpen(filterNumber(obj.getString("Open"), precision));
        marketPointBean.setHigh(filterNumber(obj.getString("High"), precision));
        marketPointBean.setLow(filterNumber(obj.getString("Low"), precision));
        marketPointBean.setCode(obj.getString("Code"));
        marketPointBean.setTimestamp(obj.getLong("UpdateTime")*1000);
        marketPointBean.setTime(new Date(marketPointBean.getTimestamp()));
        return marketPointBean;
    }
}
