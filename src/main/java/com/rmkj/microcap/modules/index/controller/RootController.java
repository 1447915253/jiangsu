package com.rmkj.microcap.modules.index.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.modules.user.bean.RegBean;
import com.rmkj.microcap.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 */
@RestController
@RequestMapping("${v1}")
public class RootController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * wap端注册注册
     * @param regBean
     * @param errors
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity reg(@Valid RegBean regBean, Errors errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", parseErrors(errors).getBody());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            ResponseEntity responseEntity = userService.reg(regBean);
            return responseEntity;
        }
    }
}
