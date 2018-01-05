package com.rmkj.microcap.modules.corps.controller.wap;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.corps.bean.Percent3;
import com.rmkj.microcap.modules.corps.dao.CorpsDao;
import com.rmkj.microcap.modules.corps.service.CorpsService;
import com.rmkj.microcap.modules.index.entity.ParameterSet;
import com.rmkj.microcap.modules.index.service.ParameterService;
import com.rmkj.microcap.modules.user.dao.UserDao;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 123 on 2016/11/23.
 */
@Controller
@RequestMapping("${wap}/corps")
public class CorpsController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CorpsDao corpsDao;

    @Autowired
    private CorpsService corpsService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "")
    @LoginAnnot
    public String index(Model model){
        String userId = AuthContext.getUserId();
        User userById = userDao.findUserById(userId);
        ParameterSet parameterSet = parameterService.findParameterSetList();
        model.addAttribute("percent3",  new Percent3(parameterSet.getPercentBubing().doubleValue(), parameterSet.getPercentQibing().doubleValue()));
        if(userById.getIdCard() == null || "".equals(userById.getIdCard())){
            return "wap/myteam/create";
        }
        model.addAttribute("user", userById);

        model.addAttribute("corpsCount", corpsDao.queryUserCorpsCount(userId));
        model.addAttribute("corpsMoney", corpsDao.queryUserCorpsMoney(userId));
        return "wap/myteam/myteam";
    }

    @RequestMapping(value = "/members")
    @LoginAnnot
    public String corpsDetail(Model model){
        User user = new User();
        model.addAttribute("users", corpsService.queryUserCorpsDetail(user));
        return "wap/myteam/members";
    }

    @RequestMapping(value = "/profit")
    @LoginAnnot
    public String corpsMoneyDetail(Model model){
        String userId = AuthContext.getUserId();
        User userById = userDao.findUserById(userId);
        ParameterSet parameterSet = parameterService.findParameterSetList();
        model.addAttribute("user", userById);
        model.addAttribute("_percent3", JSON.toJSONString(new Percent3(parameterSet.getPercentBubing().doubleValue(), parameterSet.getPercentQibing().doubleValue())));
        return "wap/myteam/profit";
    }

    @RequestMapping(value = "/create")
    @LoginAnnot
    public String create(Model model){
        model.addAttribute("percent3", new Percent3());
        return "wap/myteam/reg";
    }
}
