
//
var _type = '1';
function toRecharge() {
    var type = _type;
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    //document.getElementById('yinLianForm').reset();
    $.ajax({
        url: ctx + '/money/tiangong/prepay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            type: type
        }),
        success: function (data) {
            layer.closeAll();
            // 微信支付
            // function onBridgeReady(){
            //     layer.closeAll();
            //     var _prepay = data.pk.replace('prepay_id=', '');
            //     WeixinJSBridge.invoke(
            //         'getBrandWCPayRequest',
            //         {
            //             "appId": data.appId,     //公众号名称，由商户传入
            //             "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
            //             "nonceStr": data.nonceStr, //随机串
            //             "package": data.pk,
            //             "signType": data.signType,         //微信签名方式：MD5
            //             "paySign": data.paySign //微信签名
            //         },
            //         function(res){
            //             // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            //             if(res.err_msg == "get_brand_wcpay_request:ok" ) {
            //                 layer.msg("充值成功", function () {
            //                     window.history.back();
            //                 })
            //             }else {
            //                 $.ajax({
            //                     url: ctx + '/money/prepay/del/'+_prepay,
            //                     type: 'delete',
            //                     contentType: 'application/json;charset=UTF-8'
            //                 });
            //                 if(res.err_msg == 'get_brand_wcpay_request:cancel'){
            //                     // 用户取消
            //                 }else{
            //                     layer.msg("充值失败", function () {
            //
            //                     });
            //                 }
            //             }
            //         }
            //     );
            // }
            // if (typeof WeixinJSBridge == "undefined"){
            //     if(document.addEventListener ){
            //         document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            //     }else if(document.attachEvent){
            //         document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            //         document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            //     }
            // }else{
            //     onBridgeReady();
            // }

            // 中信支付
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');

            // 天宫移动端支付
            // if('1' == type){
            //     // 微信
            //     if("error" == data){
            //         layer.msg("充值失败");
            //     }else{
            //         eval(data);
            //     }
            // }else{
            //     // 银联
            //     for(var attr in data){
            //         $('#yinLianForm input[name="'+attr+'"]').val(data[attr]);
            //     }
            //     document.getElementById('yinLianForm').submit();
            //
            // }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            layer.closeAll();
            layer.msg("充值失败");
            try{
                console.log(XMLHttpRequest.message);
            }catch(e){


            }

        }
    });
}

//支付充值
function toPay(payType) {
    var _m = $("#zhenmoeny").val();
    var obj = JSON.parse($.cookie("_auth"));
    var json =  JSON.stringify({
        money: _m,
        cookie:obj
    })
/*    if(Number(_m) < 10){
        layer.msg("单笔入金最低10元");
        return;
    }*/
    if(payType == 0){
        //unionPay();
        /*通联微信扫码*/
        //tongLianToRecharge();
        //98 微信扫码支付
        nineEightSannPay(0);
    }else if(payType == 1){
        //scanCodePay();
        /*支付宝*/
        //aLiScanCodePay();
        //98 支付宝扫码支付
        nineEightSannPay(1);
    }else if(payType == 2){
        //Android访问
        //showAndroidToast(json)
        //98支付 快捷支付
        nineeightQuickPay();
    }else if(payType == 3){
        //IOS访问
        //window.location.href = 'tel:'+json
        //98支付 qq钱包
        nineEightSannPay(3);
    }else if(payType == 4){
        //58支付 微信openPay
        // fiveEightOpenPay();
        //58微信网关openPay
        fiveEightGateWayOpenPay();
    }else if(payType == 5){
        weiPengH5Pay();
    }else if(payType == 6){
        //杉德支付
        shanDePay();
    }else if(payType == 7){
        //杉德微信扫码支付
        shanDePagePay(payType);
    }else if(payType == 8){
        //杉德支付宝扫码支付
        shanDePagePay(payType);
    }else if(payType == 9){
        //百时微信扫码支付
        baiShiScanPay(payType);
    }else if(payType == 10){
        //百时支付宝扫码支付
        baiShiScanPay(payType);
    }else if(payType == 11){
        //百时支付宝扫码支付
        baiShiWeChatPubPay(payType);
    }else if(payType == 12){
        //摩宝银联支付
        moBaoUnionPay(payType);
    }else if(payType == 13){
        //新Web微信支付
        newWebPay(payType);
    }else if(payType == 14){
        //杉德支付2
        shanDePay2();
    }else if(payType == 15){
        //杉德微信扫码支付2
        shanDePagePay2(payType);
    }else if(payType == 16){
        //杉德支付宝扫码支付2
        shanDePagePay2(payType);
    }else if(payType == 17){
        //杉德京东扫码支付
        shanDePagePay2(payType);
    }else if(payType == 18){
        //千红扫码支付
        qianHongPagePay(payType);
    }else if(payType == 19){
        //熊云支付宝支付
        xionyunPagePay(payType);
    }else if(payType == 20){
        //汇智通
        huizhitongPagePay(payType);
    }else if(payType == 21){
        //熊云微信支付
        xionyunPagePay(payType);
    }
}

//熊云支付
function xionyunPagePay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var payType;
    if(type == 19){
        payType = '103';
    }else if(type == 21){
        payType = '104';
    }else {
        payType = '103';
    }
    $.ajax({
        url: ctx + '/money/xionyunPagePay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money : _m,
            tradeType : payType,
        }),
        success: function (data) {
            window.location.href = (data.url);
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });

}


//千红支付
function qianHongPagePay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    } else if(type == 18){
        payType = 'wechat';
        type = 902;
    }else if(type == 19){
        payType = 'alipay';
        type = 903;
    }
   /* if(_m >= 2000){
        layer.msg("充值金额不能超过2000！", function () {

        });
        return;
    }
*/
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    window.location.href=ctxWap+'/pay/qianhongPay?money='+encodeURI(_m);
}

//杉德支付
function shanDePay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var obj = {
        money: getInputValue('zhenmoeny')
    };
    $.ajax({
        url: ctx + '/shande/shanDeH5Pay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(obj),
        success: function (data) {
            /*window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);*/
            console.log(data);
            window.location.href = data;
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

/*威鹏H5支付*/
function weiPengH5Pay(){

    /*layer.msg("正在维护...");
     return false;*/
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var obj = {
        money: getInputValue('zhenmoeny')
    };
    $.ajax({
        url: ctx + '/money/weiPengH5Pay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(obj),
        success: function (data) {
            /*window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);*/
            console.log(data);
            window.location.href = data;
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//58微信网关openPay
function fiveEightGateWayOpenPay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });

/*    var obj = {
        tprice: getInputValue('zhenmoeny'),
    };*/
    var obj = {
        money: getInputValue('zhenmoeny'),
    };
    $.ajax({
        //加密数字签名 且添加订单
        url: ctx + '/money/fiveeight/gateWayOpenPay/createGateWayPayOrderByService',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(obj),
        success: function (data) {
            if(data != ''){
/*                setInputValue('userId',data.userId);
                setInputValue('tprice',data.tprice);
                setInputValue('tname',data.tname);
                setInputValue('attach',data.attach);
                setInputValue('attachOrderId',data.attach);
                setInputValue('jumpUrl',data.jumpUrl);
                document.getElementById('fiveeightWeixinForm').submit();*/
                //var json  = JSON.parse(data.credential);
                //window.location.href = json.submitUrl+'?payMode='+json.payMode+'&params='+json.params;
                console.log(data);
                window.location.href = data;
            }
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//58支付 微信openPay
function fiveEightOpenPay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    $.ajax({
        url: ctx + '/money/fiveEightOpenPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            totalAmount: _m
        }),
        success: function (data) {
            layer.closeAll();
            var _prepay = data.orderId;
            data = JSON.parse(data.payInfo);
            console.log(data);
            function onBridgeReady(){
                layer.closeAll();
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest',
                    {
                        "appId": data.appId,     //公众号名称，由商户传入
                        "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
                        "nonceStr": data.nonceStr, //随机串
                        "package": data.package,
                        "signType": data.signType,         //微信签名方式：MD5
                        "paySign": data.paySign //微信签名
                    },
                    function(res){
                        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                            layer.msg("充值成功", function () {
                                window.history.back();
                            })
                        }else {
                            $.ajax({
                                url: ctx + '/money/prepay/del/' + _prepay,
                                type: 'delete',
                                contentType: 'application/json;charset=UTF-8'
                            });
                            if(res.err_msg == 'get_brand_wcpay_request:cancel'){
                                // 用户取消
                            }else{
                                layer.msg("充值失败", function () {

                                });
                            }
                        }
                    }
                );
            }
            if (typeof WeixinJSBridge == "undefined"){
                if(document.addEventListener ){
                    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                }else if(document.attachEvent){
                    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                }
            }else{
                onBridgeReady();
            }
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//98支付 扫码支付
function nineEightSannPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var payType;
    if(type == 0){
        payType = 'wechat';
    }else if(type == 1){
        payType = 'alipay';
    }else if(type == 3){
        payType = 'qq';
    }
    $.ajax({
        url: ctx + '/money/nineEightScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data)+'&payType='+payType;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//杉德 扫码支付
function shanDePagePay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    if(type == 7){
        payType = 'wechat';
        type = 1;
    }else if(type == 8){
        payType = 'alipay';
        type = 2;
    }

    $.ajax({
        url: ctx + '/money/shanDeScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data)+'&payType='+payType;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}


//杉德 QQ扫码支付
function shanDeQQScanPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    if(type == 18){
        payType = 'qq';
    }

    $.ajax({
        url: ctx + '/money/shanDeQQScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data)+'&payType='+payType;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}


//百时扫码支付
function baiShiScanPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    if(type == 9){
        payType = 'wechat';
        type = "0203";
    }else if(type == 10){
        payType = 'alipay';
        type = "0204";
    }

    $.ajax({
        url: ctx + '/money/baiShiScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = data;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//百时微信公众号支付
function baiShiWeChatPubPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    if(type == 11){
        payType = 'wechatpub';
        type = "0207";
    }
    $.ajax({
        url: ctx + '/money/baiShiWeChatPubtPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = data;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//98支付 快捷支付
function nineeightQuickPay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });

    var obj = {
        money: getInputValue('zhenmoeny'),
    };
    $.ajax({
        //加密数字签名 且添加订单
        url: ctx + '/money/nineeight/quickPay/createPayOrder',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(obj),
        success: function (data) {
            if(data != ''){
                //document.getElementById('nineeightYinLianForm').submit();
                var json  = JSON.parse(data.credential);
                window.location.href = json.submitUrl+'?payMode='+json.payMode+'&params='+json.params;
            }
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

function scanCodePay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    $.ajax({
        url: ctx + '/money/createPayOrder',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            type:"254"
        }),
        success: function (data) {
            // var url = data.bankurl;
            // window.location.replace(url);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data.codeurl);
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

function unionPay() {
    var _m = $("#zhenmoeny").val();
    if(/^\d+(\.\d{0,2})?$/.test(_m)){
    }else{
        layer.msg("请输入正确的充值金额！")
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });

    var obj = {
        money: getInputValue('zhenmoeny'),
    };
    $.ajax({
        //加密数字签名 且添加订单
        url: ctx + '/money/yizhifu/unionPay/createPayOrder',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(obj),
        success: function (data) {
            if(data != ''){
                setInputValue('v_mid',data.v_mid);
                setInputValue('v_oid',data.v_oid);
                setInputValue('v_rcvname',data.v_rcvname);
                setInputValue('v_rcvaddr',data.v_rcvaddr);
                setInputValue('v_rcvtel',data.v_rcvtel);
                setInputValue('v_rcvpost',data.v_rcvpost);
                setInputValue('v_ymd',data.v_ymd);
                setInputValue('v_orderstatus',data.v_orderstatus);
                setInputValue('v_ordername',data.v_ordername);
                setInputValue('v_moneytype',data.v_moneytype);
                setInputValue('v_url',data.v_url);
                setInputValue('v_md5info',data.v_md5info);
                document.getElementById('form').submit();
            }
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}
function getInputValue(id) {
    return $('#'+id).val();
}
function setInputValue(id,value) {
    return $("#"+id).val(value);
}

function setType(t) {
    $(".showPic"+t).parent("div").siblings().find("a").removeClass("active")
    if(t=="1"){
        $(".showPic"+1).addClass("active")
    }else if(t == '0'){
        $(".showPic"+0).addClass("active")
    }else if(t == '2'){
        $(".showPic"+2).addClass("active")
    }else if(t == '3'){
        $(".showPic"+3).addClass("active")
    }else if(t == '4'){
        $(".showPic"+4).addClass("active")
    }else if(t == '5'){
        $(".showPic"+5).addClass("active")
    }else if(t == '6'){
        $(".showPic"+6).addClass("active")
    }else if(t == '7'){
        $(".showPic"+7).addClass("active")
    }else if(t == '8'){
        $(".showPic"+8).addClass("active")
    }else if(t == '9'){
        $(".showPic"+9).addClass("active")
    }else if(t == '10'){
        $(".showPic"+10).addClass("active")
    }else if(t == '11'){
        $(".showPic"+11).addClass("active")
    }else if(t == '12'){
        $(".showPic"+12).addClass("active")
    }else if(t == '13'){
        $(".showPic"+13).addClass("active")
    }else if(t == '14'){
        $(".showPic"+14).addClass("active")
    }else if(t == '15'){
        $(".showPic"+15).addClass("active")
    }else if(t == '16'){
        $(".showPic"+16).addClass("active")
    }else if(t == '17'){
        $(".showPic"+17).addClass("active")
    }else if(t == '18'){
        $(".showPic"+18).addClass("active")
    }else if(t == '19'){
        $(".showPic"+19).addClass("active")
    }else if(t == '20'){
        $(".showPic"+20).addClass("active")
    }else if(t == '21'){
        $(".showPic"+21).addClass("active")
    }
    $('#weixin').removeClass('weixin-active').addClass('weixin');
    $('#yinlian').removeClass('yinlian-active').addClass('yinlian');
    $('#quickPay').removeClass('weixin-active').addClass('weixin');
    $('#qqPay').removeClass('weixin-active').addClass('weixin');
    $('#weixinOpenPay').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDePay').removeClass('yinlian-active').addClass('yinlian');
    $('#weipengH5').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDePagePay').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDeAlipayPagePay').removeClass('yinlian-active').addClass('yinlian');
    $('#baiShiWeChatScanPay').removeClass('yinlian-active').addClass('yinlian');
    $('#baiShiALiScanPay').removeClass('yinlian-active').addClass('yinlian');
    $('#baiShiWeChatPubPay').removeClass('yinlian-active').addClass('yinlian');
    $('#moBaoUnionPay').removeClass('kuaijie-active').addClass('kuaijie');
    $('#xinWebScanPay').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDePay2').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDePagePay2').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDeAlipayPagePay2').removeClass('yinlian-active').addClass('yinlian');
    $('#shanDeJDPagePay2').removeClass('jingdong-active').addClass('jingdong');
    $('#shanDeQQScanPay').removeClass('yinlian-active').addClass('yinlian');
    $('#qianHongPagePay').removeClass('qqmoney-active').addClass('qqmoney');
    $('#xionyunPagePay').removeClass('jingdong-active').addClass('weixin');
    $('#huizhitongPagePay').removeClass('jingdong-active').addClass('weixin');
    $('#xionvxPagePay').removeClass('jingdong-active').addClass('weixin');

    if('0' == t){
        payType = 1;
        $('#weixin').removeClass('weixin').addClass('weixin-active');
    }else if('1' == t){
        payType = 0;
        $('#yinlian').removeClass('yinlian').addClass('yinlian-active');
    }else if('2' == t){
        payType = 2;
        $('#quickPay').removeClass('weixin').addClass('weixin-active');
    }else if( '3'== t){
        payType = 3;
        $('#qqPay').removeClass('weixin').addClass('weixin-active');
    }else if('4' == t){
        payType = 4;
        $('#weixinOpenPay').removeClass('yinlian').addClass('yinlian-active');
    }else if('5' == t){
        payType = 5;
        $('#weipengH5').removeClass('yinlian-active').addClass('yinlian');
    }else if('6' == t){
        payType = 6;
        $('#shanDePay').removeClass('yinlian').addClass('yinlian-active');
    }else if('7' == t){
        payType = 7;
        $('#shanDePagePay').removeClass('yinlian').addClass('yinlian-active');
    }else if('8' == t){
        payType = 8;
        $('#shanDeAlipayPagePay').removeClass('yinlian').addClass('yinlian-active');
    }else if('9' == t){
        payType = 9;
        $('#baiShiWeChatScanPay').removeClass('yinlian').addClass('yinlian-active');
    }else if('10' == t){
        payType = 10;
        $('#shanDeAlipayPagePay').removeClass('yinlian').addClass('yinlian-active');
    }else if('11' == t){
        payType = 11;
        $('#baiShiWeChatPubPay').removeClass('yinlian').addClass('yinlian-active');
    }else if('12' == t){
        payType = 12;
        $('#moBaoUnionPay').removeClass('kuaijie').addClass('kuaijie-active');
    }else if('13' == t){
        payType = 13;
        $('#xinWebScanPay').removeClass('yinlian').addClass('yinlian-active');
    }else if('14' == t){
        payType = 14;
        $('#shanDePay2').removeClass('yinlian').addClass('yinlian-active');
    }else if('15' == t){
        payType = 15;
        $('#shanDePagePay2').removeClass('yinlian').addClass('yinlian-active');
    }else if('16' == t){
        payType = 16;
        $('#shanDeAlipayPagePay2').removeClass('weixin').addClass('weixin-active');
    }else if('17' == t){
        payType = 17;
        $('#shanDeJDPagePay2').removeClass('jingdong').addClass('jingdong-active');
    }else if('18' == t){
        payType = 18;
        $('#qianHongPagePay').removeClass('qqmoney').addClass('qqmoney-active');
    }else if('19' == t){
        payType = 19;
        $('#xionyunPagePay').removeClass('jingdong').addClass('jingdong-active');
    }else if('20' == t){
        payType = 20;
        $('#huizhitongPagePay').removeClass('jingdong').addClass('jingdong-active');
    }else if('21' == t){
        payType = 21;
        $('#xionvxPagePay').removeClass('weixin').addClass('weixin-active');
    }
}

/*威鹏扫码支付*/
function weiPengScanQrcodePay() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    if(_m < 10){
        layer.msg("最低充值金额10元！");
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var obj = {
        money: getInputValue('zhenmoeny')
    };
    $.ajax({
        url: ctx + '/money/weiPengScanQrcode',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(obj),
        success: function (data) {
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

/*威富通扫码支付*/
function tongLianToRecharge() {
    var type = 'NATIVE';
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    //document.getElementById('yinLianForm').reset();
    $.ajax({
        url: ctx + '/money/tongLianScan',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType: type
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);
            // 中信支付
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

/*支付宝支付*/
function aLiScanCodePay(){
    var type = 'NATIVE';
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    //document.getElementById('yinLianForm').reset();
    $.ajax({
        url: ctx + '/money/aLiScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType: type
        }),
        success: function (data) {
            layer.closeAll();
            console.log("等待中");
            // window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);
            // 中信支付
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
            window.location.replace(data);
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//摩宝银联支付
function moBaoUnionPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    if(_m >= 2000){
        layer.msg("银联充值金额不能超过2000！", function () {

        });
        return;
    }

    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    window.location.href=ctxWap+'/pay/moBaoUnionPay?money='+encodeURI(_m);
}

//新Web扫码支付
function newWebPay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    $.ajax({
        url: ctx + '/money/xinWebScanPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            // console.log(data);
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);
            //window.location.href = data;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//杉德支付2
function shanDePay2() {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    var obj = {
        money: getInputValue('zhenmoeny')
    };
    $.ajax({
        url: ctx + '/shande2/shanDeH5Pay2',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(obj),
        success: function (data) {
            /*window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);*/
            console.log(data);
            window.location.href = data;
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//杉德 扫码支付2
function shanDePagePay2(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    if(type == 15){
        payType = 'wechat';
        type = 1;
    }else if(type == 16){
        payType = 'alipay';
        type = 2;
    }else if(type == 17){
        payType = 'jingdong';
        type = 3;
    }

    $.ajax({
        url: ctx + '/money/shanDeScanPay2',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            console.log(data)
            window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data)+'&payType='+payType;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}

//汇智通支付
function huizhitongPagePay(type) {
    var _m = $("#zhenmoeny").val();
    if(!/^\d+(\.\d{0,2})?$/.test(_m)){
        layer.msg("充值金额不正确！", function () {

        });
        return;
    }
    layer.open({
        type: 2,
        content: '加载中',
        shadeClose: false
    });
    alert($("#zhenmoeny").val());
    $.ajax({
        url: ctx + '/money/huizhitongPay',
        type: 'post',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            money: _m,
            tradeType:payType,
            type:type
        }),
        success: function (data) {
            layer.closeAll();
            // console.log(data);
            //window.location.href = ctxWap + '/money/qrcode?url='+encodeURIComponent(data);
            //window.location.href = data;
            //window.location.replace('https://pay.swiftpass.cn/pay/jspay?token_id='+data+'&showwxtitle=1');
        },
        error: function(){
            layer.closeAll();
            layer.msg("充值失败");
        }
    });
}