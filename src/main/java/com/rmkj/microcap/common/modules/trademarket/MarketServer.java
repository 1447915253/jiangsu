package com.rmkj.microcap.common.modules.trademarket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 123 on 2016/12/15.
 */
@Service
public final class MarketServer {

    protected final Logger Log = Logger.getLogger(MarketServer.class);

    // 行情数据缓存
    protected final Map<String, MarketPointBean> _cacheNew = new HashMap<>();
    protected final Map<String, List<MarketPointBean>> _cacheKData = new HashMap<>();

    @Autowired
    protected AbstractMarketService marketService;

    public MarketPointBean getCacheNew(String code){
        return _cacheNew.get(code);
    }

    public List<MarketPointBean> getCacheKData(String key){
        return _cacheKData.get(key);
    }

    private boolean isInitNew = true;
    private boolean isInitKData = true;

    protected boolean [] interval_init = new boolean[]{false,false,false,false,false,false};

    @Autowired
    protected ThreadPoolTaskExecutor threadPoolTaskExecutor = null;

    /**
     * 开盘的产品代码
     * @return
     */
    public String[] _codes(){
        if(isInitNew || isInitKData){
            return marketService.codes();
        }
        return marketService.codesOpen();
    }

    /**0
     * 定时获取K线图
     * 不同K线图，获取频率不一样，获取条数不一样
     *
     * 获取失败，总是会每分钟都去重新获取数据。无数据也算作失败
     */
    protected final String[] interval_rows = new String[]{"200", "30", "30", "30", "30", "30"};

    public String cacheKey(String code, String interval){
        return code.concat("_").concat(interval);
    }

    /**
     * 重置K线图缓存数据
     */
    public void reset(){
        interval_init = new boolean[]{false,false,false,false,false,false};
    }

    /**
     * 清空code缓存数据
     * @param code
     */
    public void clearCodeCache(String code){
        Set<String> keys = _cacheKData.keySet();
        keys.forEach(key -> {
            if(key.startsWith(code.concat("_"))){
                _cacheKData.remove(key);
            }
        });
        _cacheNew.remove(code);
    }

    public void putNew2Cache(MarketPointBean marketPointBean, String code) {
        // 触发行情变更事件
        MarketPointBean prePoint = _cacheNew.get(code);
        // 放入缓存
        _cacheNew.put(code, marketPointBean);
        if(prePoint != null && !prePoint.getPrice().equals(marketPointBean.getPrice())){
            threadPoolTaskExecutor.execute(() -> marketService.changeNew(marketPointBean));
        }
    }

    public void putKDate2Cache(String code, String interval, int position, List<MarketPointBean> list){
        _cacheKData.put(cacheKey(code, interval), list);
        interval_init[position] = true;
    }

    public boolean isInitNew() {
        return isInitNew;
    }

    public boolean isInitKData() {
        return isInitKData;
    }

    public void setInitNew(boolean initNew) {
        isInitNew = initNew;
    }

    public void setInitKData(boolean initKData) {
        isInitKData = initKData;
    }

    public String[] getInterval_rows() {
        return interval_rows;
    }

    public boolean[] getInterval_init() {
        return interval_init;
    }
}
