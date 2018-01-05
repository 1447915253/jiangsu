package com.rmkj.microcap.common.modules.trademarket;

/**
 * Created by 123 on 2016/10/27.
 */
public abstract class AbstractMarketService {

    /**
     * 期货种类行情代码
     * @return
     */
    public abstract String[] codes();

    /**
     * 最新行情变化是，触发此方法
     * @param marketPointBean
     */
    public abstract void changeNew(MarketPointBean marketPointBean);

    /**
     * 开盘的期货种类行情代码
     * @return
     */
    public abstract String[] codesOpen();
}
