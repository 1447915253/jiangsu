package com.rmkj.microcap.modules.index.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.utils.DateUtils;
import com.rmkj.microcap.modules.index.entity.Broadcast;
import com.rmkj.microcap.modules.index.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 123 on 2016/10/18.
 */
@RestController
@RequestMapping("${v1}/index")
public class IndexController extends BaseController {

    @Autowired
    private BroadcastService broadcastService;

    @RequestMapping(value = "/broadcastList", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity broadcastList(){
        List<Broadcast> list = broadcastService.findList();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCurrentTime",method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity getCurrentTime(){
        return new ResponseEntity(DateUtils.getTime(),HttpStatus.OK);
    }
}
