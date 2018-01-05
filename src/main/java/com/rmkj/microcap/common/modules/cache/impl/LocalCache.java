package com.rmkj.microcap.common.modules.cache.impl;

import com.rmkj.microcap.common.modules.cache.Cache;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbowen on 2016/4/5.
 */
public class LocalCache implements Cache {

    private final Logger Log = Logger.getLogger(LocalCache.class);
    // 缓存
    private Map<String, Object> maps = null;

    private Thread thread = null;

    private volatile boolean stopFlag = false;

    public LocalCache() {
        maps = new ConcurrentHashMap<>();

        thread = new Thread(){

            @Override
            public void run() {
                while (!stopFlag){
                    try {
                        TimeUnit.SECONDS.sleep(5*60);
                    } catch (InterruptedException e) {
                        break;
                    }
                    if(stopFlag){
                        break;
                    }
                    for (String key : maps.keySet()){
                        maps.get(key);
                    }
                }
            }

        };
        thread.start();
    }

    @Override
    public void set(String key, Object value, int exp) {
        CacheObj cacheObj = new CacheObj();
        cacheObj.setObj(value);
        cacheObj.setSaveTime(System.currentTimeMillis());
        cacheObj.setExp(exp);
        maps.put(key, cacheObj);
    }

    /*
             * 删除缓存数据
             */
    @Override
    public void delete(String key) {
        maps.remove(key);
    }

    /*
     * 获取缓存数据,如果关键字不存在返回null
     */
    @Override
    public Object get(String key) {
        CacheObj cacheObj = (CacheObj) maps.get(key);
        //说明没过期
        if (cacheObj != null && (cacheObj.getExp() == 0 || ((System.currentTimeMillis() - cacheObj.getSaveTime()) <= cacheObj.getExp() * 1000))) {
            return cacheObj.getObj();
        }else {
            if(cacheObj != null){
                maps.remove(key);
            }
        }
        return null;
    }
    @Override
    public void shutdown() {
        try {
            stopFlag = true;
            if(!thread.isInterrupted()){
                // 定时睡眠状态 唤醒，执行完毕
                if(thread.getState().equals(Thread.State.TIMED_WAITING)
                        || thread.getState().equals(Thread.State.WAITING)){
                    thread.notify();
                }else if(thread.getState().equals(Thread.State.BLOCKED)){
                    // 阻塞直接打断
                    thread.interrupt();
                }
            }
            if(thread.isAlive()){
                thread.join(3000);
                if(thread.isAlive()){
                    thread.interrupt();
                }
            }
            Log.info("LocalCache has been closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        maps.clear();
    }
    private class CacheObj {
        //对象
        private Object obj;
        //缓存时间
        private int exp;
        //存入时间
        private long saveTime;

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public int getExp() {
            return exp;
        }

        public void setExp(int exp) {
            this.exp = exp;
        }
    }
}
