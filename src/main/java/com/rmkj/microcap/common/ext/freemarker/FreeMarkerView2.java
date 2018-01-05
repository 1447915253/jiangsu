//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.rmkj.microcap.common.ext.freemarker;

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.handler.SpringContextHolder;
import com.rmkj.microcap.modules.index.service.ScheduleService;
import com.rmkj.microcap.modules.weChatPublic.entity.WeChatPublic;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 重写内置request对象
 */
public class FreeMarkerView2 extends FreeMarkerView {

    private ScheduleService scheduleService;

    protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        SimpleHash templateModel = super.buildTemplateModel(model, request, response);
        templateModel.put("request", request);

        if(scheduleService == null){
            scheduleService = SpringContextHolder.getBean(ScheduleService.class);
        }

        WeChatPublic weChatPublic = scheduleService.getWeChatPublic(request.getServerName());
        if(weChatPublic != null){
            templateModel.put("_title", weChatPublic.getName());
        }
        templateModel.put("_project_debug", ProjectConstants.PRO_DEBUG);
        templateModel.put("only_weixin_login", ProjectConstants.ONLY_WEIXIN_LOGIN);

        return templateModel;
    }

}
