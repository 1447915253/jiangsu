package com.rmkj.microcap.modules.index.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.modules.sys.service.TokenService;
import com.rmkj.microcap.common.modules.trademarket.MarketPointBean;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.ContextUtils;
import com.rmkj.microcap.common.utils.DateUtils;
import com.rmkj.microcap.modules.index.entity.Broadcast;
import com.rmkj.microcap.modules.market.bean.KDataBean;
import com.rmkj.microcap.modules.market.service.MarketService;
import com.rmkj.microcap.modules.money.service.CashCouponService;
import com.rmkj.microcap.modules.trade.entity.Contract;
import com.rmkj.microcap.modules.trade.entity.Trade;
import com.rmkj.microcap.modules.trade.service.TradeService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 123 on 2016/11/10.
 */
@Service
public class RootService {

    @Autowired
    private BroadcastService broadcastService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private CashCouponService cashCouponService;
    /**
     * 跳转首页
     * @param model
     */
    public String toHome(Model model){
        homeService.index(model);
        List<Broadcast> broadcasts = broadcastService.findList();
        model.addAttribute("broadcastsJson", JSON.toJSONString(broadcasts));
        User user = (User) userService.get().getBody();
        model.addAttribute("user", user);
        List<Contract> contracts = tradeService.contractList();
        model.addAttribute("contracts", contracts);
        //String [] _codes = marketService.codes();
        //model.addAttribute("_codes", JSON.toJSONString(_codes));
        model.addAttribute("_user_can_use_coupons", cashCouponService.findUserCashCouponCanUse());
        boolean isMarketOpen = tradeService.isMarketOpen();
        model.addAttribute("isMarketOpen", isMarketOpen);
        model.addAttribute("_isMarketOpen", JSON.toJSONString(isMarketOpen));
        JSONObject jsonObject = new JSONObject();
        KDataBean kDataBean = new KDataBean();
        contracts.forEach(c ->{
            kDataBean.setCode(c.getCode());
            kDataBean.setInterval("1");
            List<MarketPointBean> list = marketService.kdata(kDataBean);
            jsonObject.put(c.getCode(),list);
        });
        model.addAttribute("data",jsonObject.toJSONString());
        countNewMessage(model);
        return "/wap/home";
    }

    /**
     *
     * 跳转到交易页
     */
    public String toTrade(Model model){
        homeService.index(model);

        List<Broadcast> broadcasts = broadcastService.findList();
        model.addAttribute("broadcastsJson", JSON.toJSONString(broadcasts));

        String [] _codes = marketService.codes();
        model.addAttribute("_codes", JSON.toJSONString(_codes));
        model.addAttribute("_user_can_use_coupons", cashCouponService.findUserCashCouponCanUse());

        //当日订单
        List<Trade> toDayTradeList = tradeService.toDayTradeList();
        model.addAttribute("toDayTradeList",toDayTradeList);

        //持仓订单
//        List<Trade> list = tradeService.tradeList();
//        model.addAttribute("list", list);
//        model.addAttribute("_list", JSON.toJSONString(list));
        model.addAttribute("now", new Date().getTime());

        //取得最新交易10条记录
        List<Trade> newTradeRecord = tradeService.getNewTradeRecord();
        model.addAttribute("newTradeRecord",newTradeRecord);
        model.addAttribute("current_date_time",DateUtils.getDateTime());
        model.addAttribute("current_time",DateUtils.getTime());


        boolean isMarketOpen = tradeService.isMarketOpen();
        model.addAttribute("isMarketOpen", isMarketOpen);
        model.addAttribute("_isMarketOpen", JSON.toJSONString(isMarketOpen));
        model.addAttribute("_user_coupons", JSON.toJSONString(cashCouponService.findUserCashCouponCanUse()));
        //从首页跳转到交易页需要的交易种类的id
        model.addAttribute("countNewMessage", userService.countNewMessage());

        //如果flag为false,说明用户未登录，只能查看数据，不进行操作
        if(TokenService.checkToken()){
            model.addAttribute("loginStatus", "1");
            User user = (User) userService.get().getBody();
            if(user.getStatus() == 1){
                return "/wap/invalid";
            }
            model.addAttribute("user", user);
            //持仓订单
            List<Trade> list = tradeService.tradeList();
            model.addAttribute("list", list);
            model.addAttribute("_list", JSON.toJSONString(list));
        }else{
            model.addAttribute("loginStatus", "0");
//            return "/wap/previewTrade";
        }

//        Cookie[] cookies = ContextUtils.getRequest().getCookies();
//        for(int i = 0; cookies != null && i < cookies.length; i++){
//            if(cookies[i].getName().equals("_auth")){
//                try {
//                    model.addAttribute("_auth", URLDecoder.decode(cookies[i].getValue(), "utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return "/wap/trade";
    }

    public void countNewMessage(Model model){
        model.addAttribute("countNewMessage", userService.countNewMessage());
    }


}
