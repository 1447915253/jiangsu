package com.rmkj.microcap.common.modules.weixin.face;

import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import org.springframework.http.ResponseEntity;

/**
 * Created by 123 on 2016/10/28.
 * 微信支付回调，业务处理接口
 */
public interface NotifyForPayService {

    boolean payResult(NotifyReqBean notifyReqBean);

    ResponseEntity deletePrePay(String thirdSerialNo);
}
