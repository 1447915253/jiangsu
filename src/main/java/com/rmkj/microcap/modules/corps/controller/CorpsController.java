package com.rmkj.microcap.modules.corps.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.bean.ResponseErrorEntity;
import com.rmkj.microcap.common.bean.StatusCode;
import com.rmkj.microcap.common.bean.annotation.LoginAnnot;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.corps.bean.CreateCorpsBean;
import com.rmkj.microcap.modules.corps.service.CorpsService;
import com.rmkj.microcap.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 123 on 2016/11/22.
 */
@RestController
@RequestMapping("${v1}/corps")
public class CorpsController extends BaseController{

    @Autowired
    private CorpsService corpsService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @LoginAnnot
    public ResponseEntity create(@RequestBody @Valid CreateCorpsBean createCorpsBean, Errors errors){
        if(errors.hasErrors()){
            parseErrors(errors);
        }
        StatusCode statusCode = corpsService.create(createCorpsBean);
        if(StatusCode.OK == statusCode){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseErrorEntity(statusCode, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity corpsDetail(User user){
        return new ResponseEntity(corpsService.queryUserCorpsDetail(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/profit/{type}", method = RequestMethod.GET)
    @LoginAnnot
    public ResponseEntity corpsMoneyDetail(@PathVariable String type){
        return new ResponseEntity(corpsService.queryUserCorpsMoneyWithTime(type), HttpStatus.OK);
    }
}
