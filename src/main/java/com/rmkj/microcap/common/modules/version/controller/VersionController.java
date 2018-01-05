package com.rmkj.microcap.common.modules.version.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.modules.version.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zhangbowen on 2016/1/12.
 * 版本
 */
@RestController
@RequestMapping("${v1}/version")
public class VersionController extends BaseController {
    @Autowired
    private VersionService versionService;

    /**
     * 版本
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get() {
        return new ResponseEntity(versionService.lastVersion(), HttpStatus.OK);
    }
}
