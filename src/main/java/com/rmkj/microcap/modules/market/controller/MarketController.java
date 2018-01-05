package com.rmkj.microcap.modules.market.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.modules.market.bean.CodesBean;
import com.rmkj.microcap.modules.market.bean.KDataBean;
import com.rmkj.microcap.modules.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 123 on 2016/10/19.
 */
@RestController
@RequestMapping("${v1}/market")
public class MarketController extends BaseController {

    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    //@LoginAnnot
    public ResponseEntity _new(@RequestBody @Valid CodesBean codesBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        Constants.Market.GetType type = Constants.Market.GetType.ALL;
        // 只返回 当前价
        if("0".equals(codesBean.getType())){
            type = Constants.Market.GetType.DEFAULT;
        }
        return new ResponseEntity(marketService.latest(type, codesBean.getCodes().split(",")), HttpStatus.OK);
    }

    @RequestMapping(value = "/kdata", method = RequestMethod.POST)
    //@LoginAnnot
    public ResponseEntity kdata(@RequestBody @Valid KDataBean kDataBean, Errors errors){
        if(errors.hasErrors()){
            return parseErrors(errors);
        }
        List<MarketPointBean> market =  marketService.kdata(kDataBean);
        return new ResponseEntity(market, HttpStatus.OK);
    }

}
