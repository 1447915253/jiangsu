package com.rmkj.microcap.common.modules.weixin.controller;

import com.rmkj.microcap.common.base.BaseController;
import com.rmkj.microcap.common.modules.weixin.XStreamTool;
import com.rmkj.microcap.common.modules.weixin.bean.CheckWeiXinBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyRespBean;
import com.rmkj.microcap.common.modules.weixin.service.EventBaseMsgService;
import com.rmkj.microcap.common.modules.weixin.service.WeiXinPayService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.weixin.bean.MsgTypeBean;
import com.rmkj.microcap.common.modules.weixin.factory.WeiXinServiceFactory;
import com.rmkj.microcap.common.utils.JaxbUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by zhangbowen on 2016/6/8.
 */
@RequestMapping("${v1}/weixin")
@RestController
public class WeiXinController extends BaseController {

    private static final Logger Log = Logger.getLogger(WeiXinController.class);

    @Autowired
    private WeiXinPayService weiXinPayService;

    /**
     * 接收验证
     *
     * @return
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public ResponseEntity callBack(@Valid CheckWeiXinBean checkWeiXinBean, Errors errors) {
        if (errors.hasErrors()) {
            return parseErrors(errors);
        }
        String[] arrays = new String[]{ProjectConstants.WEI_XIN_TOKEN, checkWeiXinBean.getTimestamp(), checkWeiXinBean.getNonce()};
        Arrays.sort(arrays);
        StringBuffer shaStr = new StringBuffer();
        for (String s : arrays) {
            shaStr.append(s);
        }
        String sha1ResultStr = Utils.sha(shaStr.toString());
        if (sha1ResultStr.equals(checkWeiXinBean.getSignature())) {
            return ResponseEntity.ok(checkWeiXinBean.getEchostr());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * 接收消息与事件
     *
     * @param xml
     * @return
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public ResponseEntity callBack(@RequestBody String xml) {
        if (StringUtils.isBlank(xml)) {
            return ResponseEntity.ok().build();
        }
        Log.info(xml);
        MsgTypeBean baseMsgBean = JaxbUtil.convertToJavaBean(xml, MsgTypeBean.class);
        String msgType = StringUtils.isNotBlank(baseMsgBean.getMsgType()) ? baseMsgBean.getMsgType() : "";
        String event = StringUtils.isNotBlank(baseMsgBean.getEvent()) ? "_" + baseMsgBean.getEvent() : "";
        EventBaseMsgService baseMsgService = WeiXinServiceFactory.getService(msgType + event);
        if (baseMsgService == null) {
            System.out.println(msgType + event);
            return ResponseEntity.ok("success");
        }
        String result = baseMsgService.execute(xml);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/pay/callback")
    public ResponseEntity notifyUrl(@RequestBody String xml){
        Log.info(xml);
        NotifyReqBean notifyReqBean = XStreamTool.toBean(xml, NotifyReqBean.class);
        return new ResponseEntity(XStreamTool.toXml(weiXinPayService.notify(notifyReqBean), NotifyRespBean.class), HttpStatus.OK);
    }
}
