package com.rmkj.microcap.common.modules.trademarket;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by renwp on 2017/4/19.
 */
public class GaoMarket implements MarketPro {

    @Override
    public String[] reqIntervalsParams() {
        return new String[]{"1", "5", "15", "30", "60", "1440"};
    }

    @Override
    public String filterNumber(Object obj, Integer precision){
        return String.format("%1.0".concat(precision.toString()).concat("f"), Double.parseDouble(obj.toString())) + "";
//        return Math.round(Double.parseDouble(obj.toString())*Math.pow(11d, Double.parseDouble(precision+"")))+"";
    }

    @Override
    public MarketPointBean toMarketPointBean(JSONObject obj, Integer precision) {
        MarketPointBean marketPointBean = new MarketPointBean();
        marketPointBean.setCode(obj.getString("StockCode"));
        marketPointBean.setPrice(filterNumber(obj.getString("Price"), precision));
        marketPointBean.setClose(filterNumber(obj.getString("LastClose"), precision));
        marketPointBean.setOpen(filterNumber(obj.getString("Open"), precision));
        marketPointBean.setHigh(filterNumber(obj.getString("High"), precision));
        marketPointBean.setLow(filterNumber(obj.getString("Low"), precision));
        marketPointBean.setTimestamp(obj.getDouble("LastTime").longValue()*1000);
        marketPointBean.setTime(new Date(marketPointBean.getTimestamp()));
        return marketPointBean;
    }

}
