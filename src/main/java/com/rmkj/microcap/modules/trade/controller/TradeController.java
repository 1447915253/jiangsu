package com.rmkj.microcap.modules.trade.controller;

import com.mysql.fabric.Response;
import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.trade.bean.MakeTradeBean;
import com.rmkj.microcap.modules.trade.bean.SellBean;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * Created by 123 on 2016/10/18.
 */
@RestController
@RequestMapping("${v1}/trade")
public class TradeController extends BaseController {

    private final Logger Log = Logger.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "/contractList", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity contractList(){
        return new ResponseEntity(tradeService.contractList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity make(@RequestBody @Valid MakeTradeBean makeTradeBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        return makeLock(makeTradeBean);
    }
    //private Map<String, SoftReference<String>> lockMap = new HashedMap();
    /**
     *
     * @param makeTradeBean
     * @return
    public ResponseEntity makeLock(MakeTradeBean makeTradeBean){
        String userId = AuthContext.getUserId();
        String lock;
        synchronized (this){
            lock=CacheFacade.getObject(userId);
            if (StringUtils.isEmpty(lock)){
                CacheFacade.set(userId,userId,0);
                lock=CacheFacade.getObject(userId);
            }
        }
        synchronized (lock){
            ResponseEntity make = tradeService.make(makeTradeBean);
            return make;
        }
    }*/
    private Map<String, SoftReference<Object>> lockMap = new HashedMap();
    /**
     *
     * @param makeTradeBean
     * @return
     */
    public ResponseEntity makeLock(MakeTradeBean makeTradeBean){
        String userId = AuthContext.getUserId();
        Object lock;
        synchronized (this){
            SoftReference<Object> objectSoftReference = lockMap.get(userId);
            if(objectSoftReference == null || (lock = objectSoftReference.get()) == null){
                lock = new Object();
                lockMap.put(userId, new SoftReference<>(lock));
            }
        }
        synchronized (lock){
            ResponseEntity make = tradeService.make(makeTradeBean);
            return make;
        }
    }

//    @RequestMapping(value = "/sell", method = RequestMethod.PUT)
//    @LoginAnnot
//    public ResponseEntity sell(@RequestBody @Valid SellBean sellBean, Errors errors){
//        if(errors.hasErrors()){
//            return parseErrors(errors);
//        }
//        return tradeService.sell(sellBean);
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity list(){
        return new ResponseEntity(tradeService.tradeList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/holdId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getHoldId(@Valid String id){
        return tradeService.getHoldId(id);
    }

    @RequestMapping(value = "/lasts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity lasts(){
        return new ResponseEntity(tradeService.lasts(), HttpStatus.OK);
    }

}
