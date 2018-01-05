package com.rmkj.microcap.modules.index.controller.wap;

import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.modules.index.entity.Broadcast;
import com.rmkj.microcap.modules.index.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 123 on 2016/10/18.
 */
@Controller
@RequestMapping("${wap}/index")
public class IndexController {
    @Autowired
    private BroadcastService broadcastService;

    @RequestMapping(value = "/broadcastDetail/{id}", method = RequestMethod.GET)
    @LoginAnnot
    public String broadcastDetail(@PathVariable String id, Model model){
        Broadcast broadcast = broadcastService.findById(id);
        model.addAttribute("model", broadcast);
        return "/wap/index/broadcast";
    }
}
