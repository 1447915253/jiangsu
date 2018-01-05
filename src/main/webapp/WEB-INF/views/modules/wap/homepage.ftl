<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <#assign footer = "user"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/gerenzhognxin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxStatic}/all/js/jquery.min.js"></script>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <!--内容部分-->
    <div class="in_top clearfix" >
        <div class="in_user left text-center">
            <a href="#">
                <div class="img"><img src="<#if user.userHeader??>${user.userHeader}<#else>${ctxStatic}/img/images2/log.png</#if>" alt=""></div>
            </a>
        </div>
        <div class="in_acount left">
            <p class="ac_a"><span>${user.chnName}</span> &nbsp;&nbsp;&nbsp;&nbsp;交易账号：<span>${user.mobile}</span></p>
            <p class="a_money">持仓金额：<span class="a_money_red"><#if holdMoney??>${holdMoney}<#else>0.00</#if>元</span></p>
            <p class="chi_num">持仓数量：<span><#if holdCounts??>${holdCounts}<#else>0</#if></span></p>
        </div>
        <#--<div class="in_shezhi right">-->
            <#--<a href="#">-->
                <#--<img class="img" src="${ctxStatic}/all/image/shezhi.png">-->
            <#--</a>-->
        <#--</div>-->
    </div>
    <div class="w-item clearfix">
        <div class="user_img_lf left">
            <span><span id="nowMoney">${user.money?c}( 元 )</span>
        </div>
        <div class="in_money right">
            <a onclick="window.location.href = '${ctxWap}/pay/recharge'" class="btn in_money_chong left"><i></i>充值</a>
            <a onclick="withdraw()" class="btn in_money_ti left"><i></i>提现</a>
        </div>
    </div>
    <div class="mui-content">
        <ul class="mui-table-view mui-grid-view mui-grid-9">
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/user/bankCard'">
                <span class="mui-icon mui-icon-home"></span>
                <div class="mui-media-body">银行卡</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/money/payRecord'">
                <span class="mui-icon mui-icon-chatbubble"></span>
                <div class="mui-media-body">充提记录</div></a></li>
            <#--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/account'">
                <span class="mui-icon mui-icon-email"></span>
                <div class="mui-media-body">账户信息</div></a></li>-->
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/trade/tradeHistory'">
                <span class="mui-icon mui-icon-chatbubble"></span>
                <div class="mui-media-body">历史订单</div></a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/money/getBilling?flag=1'">
                <span class="mui-icon mui-icon-location"></span>
                <div class="mui-media-body">账单明细</div></a></li>
            <#--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/corps'">
                <span class="mui-icon mui-icon-search"></span>
                <div class="mui-media-body">军团</div></a></li>-->
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/coupon/list'">
                <span class="mui-icon mui-icon-quan"></span>
                <div class="mui-media-body">优惠券</div>
            <#--<#if messageCount??>-->
                <#--<div style="border: 1px solid red; background-color: red; border-radius: 50%; position: relative; bottom: 2.1rem; left: 1.4rem; color: white; width: 0.9rem; height:0.5rem; font-size: 0.3rem; line-height: 0.5rem;"><#if (messageCount>99)>99+<#else>${messageCount?c}</#if></div>-->
            <#--</#if>-->
            </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/user/userMessageList'">
                <span class="mui-icon mui-icon-phone"></span>
                <div class="mui-media-body">消息</div>
                <#if messageCount??>
                    <div style="border: 1px solid red; background-color: red; border-radius: 50%; position: relative; bottom: 2.1rem; left: 1.4rem; color: white; width: 0.9rem; height:0.5rem; font-size: 0.3rem; line-height: 0.5rem;"><#if (messageCount>99)>99+<#else>${messageCount?c}</#if></div>
                </#if></a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a  onclick="window.location.href = '${ctxWap}/logout'">
            <span class="mui-icon mui-icon-more"></span>
            <div class="mui-media-body">安全退出</div></a>
            </li>
<#--            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">
                <span class="mui-icon mui-icon-gear"></span>
                <div class="mui-media-body">邀请</div></a></li>-->
            <#--<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/user/modifyTradePwd'">
                <span class="mui-icon mui-icon-info"></span>
                <div class="mui-media-body">修改密码</div></a></li>-->
           <#-- <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a onclick="window.location.href = '${ctxWap}/user/aBout'" >
                <span class="mui-icon mui-icon-more"></span>
                <div class="mui-media-body">关于我们</div></a></li>-->
<#--            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a  onclick="window.location.replace('http://tb.53kf.com/code/client/10154491/1][img]http://tb.53kf.com/kfimg.php?arg=10154491&style=1')">
                <span class="mui-icon mui-icon-out"></span>
                <div class="mui-media-body" style="color: #388efd;">客服</div></a></li>-->
        </ul>
    </div>
</section>
<#include "/wap/footer.ftl"/>
<script type="text/javascript" src="${ctxStatic}/js/formatString.js"></script>
<script type="application/javascript">
    $(function () {
       $("#nowMoney").text(fmoney('${user.money}',2) +　"( 元 )");
    });
    function hide(ts){
        $(ts).hide();
    }
</script>
<script type="text/javascript">
    function  withdraw() {
        var d=new Date();
        var xq=d.getDay();
        var h=d.getHours();
        var m=d.getMinutes();
        var sj=( h < 10 ? '0':'')+h+':'+(m < 10 ? '0':'')+m;
        if (xq <1 || xq >5){
            //alert("请在周一至周五的9:00到16:00提现，谢谢配合");
            window.location.href = '${ctxWap}/money/out';
        }else if(xq>=1 && xq<=5 && sj < '09:00' || sj > '16:00'){
            //alert("请在周一至周五的9:00到16:00提现,谢谢配合");
            window.location.href = '${ctxWap}/money/out';
        }else {
            window.location.href = '${ctxWap}/money/out';
        }
    }
</script>
</body>
</html>