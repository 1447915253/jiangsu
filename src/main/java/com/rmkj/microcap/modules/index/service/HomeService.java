package com.rmkj.microcap.modules.index.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.modules.market.service.MarketService;
import com.rmkj.microcap.modules.trade.entity.Contract;
import com.rmkj.microcap.modules.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by 123 on 2016/10/21.
 */
@Service
@Transactional
public class HomeService {

    @Autowired
    private MarketService marketService;

    @Autowired
    private TradeService tradeService;

    public void index(Model model){
        List<Contract> contracts = tradeService.contractList();
        model.addAttribute("contracts", contracts);
        model.addAttribute("_contracts", JSON.toJSONString(contracts));
        String[] codes = marketService.codes();
        model.addAttribute("markets", marketService.latest(Constants.Market.GetType.ALL, codes));
    }


}
