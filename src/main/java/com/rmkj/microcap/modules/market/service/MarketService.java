package com.rmkj.microcap.modules.market.service;

import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.modules.trademarket.AbstractMarketService;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.modules.trademarket.MarketServer;
import com.rmkj.microcap.modules.market.bean.KDataBean;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 123 on 2016/10/19.
 * 行情数据
 * 提供查询服务
 */
@Service
public class MarketService extends AbstractMarketService {

    private final Logger Log = Logger.getLogger(MarketService.class);

    @Autowired
    private TradeService tradeService;

    @Autowired
    private MarketServer marketServer;

    String [] _codes = null;

    @Override
    public String[] codes() {
        return tradeService.contractCodes();
    }

    @Override
    public String[] codesOpen() {
        return tradeService.contractCodesOpen();
    }

    @Override
    public void changeNew(MarketPointBean marketPointBean) {
//        tradeService.checkToSell(marketPointBean);
    }

    /**
     * 获取当前最新行情
     * 当前价 昨收 今开 最高 最低 时间 时间戳
     * @param getType
     * @param codes
     * @return
     */
    public List<MarketPointBean> latest(Constants.Market.GetType getType, String... codes) {
        List<MarketPointBean> list = new ArrayList<>(codes.length);
        MarketPointBean marketPointBean = null;

        for (String code : codes){
            marketPointBean = marketServer.getCacheNew(code);
            // 缓存取不到则 模拟生成
            if(marketPointBean == null){

            }
            list.add(marketPointBean);
            marketPointBean = null;
        }

        return list;
    }

    /**
     * 只获取最新行情的 当前价
     * @param codes
     * @return
     */
    public List<MarketPointBean> latest(String... codes) {
        return latest(Constants.Market.GetType.DEFAULT, codes);
    }

    /**
     * 只获取最新行情的 当前价
     * @param code
     * @return
     */
    public MarketPointBean latest(String code) {
        return latest(Constants.Market.GetType.DEFAULT, code).get(0);
    }

    public List<MarketPointBean> kdata(KDataBean kDataBean) {
        List<MarketPointBean> list = marketServer.getCacheKData(kDataBean.getCode().concat("_").concat(kDataBean.getInterval()));
        if(list == null){
            return Collections.emptyList();
        }
        return list;
    }

}
