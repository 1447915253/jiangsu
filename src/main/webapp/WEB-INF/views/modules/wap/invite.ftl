<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<#include "/wap/header.ftl" >
<#assign footer = "hold"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>

    <link href="${ctxStatic}/all/css/daydingd.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/chicang.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <!-- new css-->
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/yaoqing.css" rel="stylesheet" type="text/css"/>
    <!--<link href="all/css/footer.css" rel="stylesheet" type="text/css"/>-->
    <script type="text/javascript" src="${ctxStatic}/js/rem.js"></script>
    <!-- 微信分享js-->
    <#--<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>-->
    <#--<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>-->

    <title>${title}</title>
    <style>
        .rule{
            background: #554d43;
        }
    </style>
</head>
<body style="background-color: #50473e;"
    <div  class="wrap-page" style="overflow-y: auto; position: absolute; top: 0px; left: 0px;">
        <div class="yaoqing" style="background: url('${ctxStatic}/all/image/ShareBg1.jpg?v=110');background-size: cover;">
            <div class="y_name">恒禾商贸千万福利HI翻天</div>
            <div class="invite_text">送Ta新手体验券若Ta注册投资，您还能获得如下奖励</div>
            <div class="button-ljfx">
                <p> 好友充值，即获 <b>20元</b> <span> 直盈券</span></p>
              <#--  <p> 好友交易，高达 <b>1.5%</b><span> 现金奖励</span></p>-->
                <a class="cik" href="javascript:void(0);" id="invite">马上邀请亲朋好友</a>
            </div>
            <div class="rule">
                <p style="font-size: 0.45rem;text-align: center;margin-bottom: .3rem">规则说明：</p>
                <p style="margin-bottom: .3rem">1、好友在您的邀请下注册成为新用户，Ta即可获得1张10元直盈券和9张100元增益券。直盈券1次只能使用1张，增益券在单笔交易金额满1000元时即可使用，可增加10%收益。</p >
                <p style="margin-bottom: .3rem">2、您邀请注册成功的好友进行首次充值以后您可以获得2张直盈券。直盈券每天最多使用3张；每张至少间隔1小时使用；每张不管盈、平、亏、一次交易后自动消失。</p >
                <p style="margin-bottom: .3rem">3、您邀请注册平台的好友A在平台交易，您可以从A的每笔交易中获得1.0%的现金奖励；A邀请注册的好友B在平台交易，您又能B的每笔交易额中获得0.5%的现金奖励。</p>
                <p style="margin-bottom: .3rem">4、邀请用户与所获得的券无上限。</p>
                <p style="text-align: center;margin-top:.8rem;color: #d5c630">活动最终解释权归平台所有</p>
            </div>
        </div>
    </div>
<#include "/wap/footer.ftl"/>
<script type="application/javascript">
    $(function () {
        if(isWeiXin() == false){
            $('#invite').hide();
        }
    });
    var _img = new Image();
    _img.src = "${ctxStatic}/user/getShareImgQRCode";
    var shareImg = '<img id="shareImage" src="${ctxWap}/user/getShareImgQRCode" style="position: relative; margin-left: -4rem; left: 50%; top: 6.7rem; z-index: 1; '
            +'width: 8rem; height: 12.21rem;">';

    function showInvite(){
        $(document.body).append(shareImg);
        setTimeout(function () {
            $('.yaoqing').one('click', hideInvite);
        }, 500);
    }
    function hideInvite() {
        $("#shareImage").remove();
        $("#invite").one('click', showInvite);
    }
    function isWeiXin(){
        var ua = window.navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i) == 'micromessenger'){
            return true;
        }else{
            return false;
        }
    }
    $(document).ready(function () {
        $("#invite").one('click', showInvite);
    });
</script>
</body>
</html>
