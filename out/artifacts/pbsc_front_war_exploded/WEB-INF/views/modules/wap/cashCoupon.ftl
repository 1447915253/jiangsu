<!DOCTYPE html>
<html style="background-color: #fff;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
<#include "/wap/header.ftl" >
<#assign footer = "home"/>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/cd.css" />
    <link rel="stylesheet" href="${ctxStatic}/css/base.css">
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/index.css">
    <link rel="stylesheet" href="${ctxStatic}/css/global.css">
    <script type="text/javascript"  src="${ctxStatic}/js/rem.js"></script>
    <style>
        @font-face{
            font-family:abc;
            src:url("${ctxStatic}/css/FZCQJW.TTF");
        }
        /*@media (min-width:380px){*/
            /*.voucher ul li div .how_much{*/
                /*bottom: 59%;*/
                /*left: 66.8%;*/
            /*}*/
        /*}*/
    </style>
</head>
<body style="max-width: 768px;margin: 0 auto;background-color: #001222;">
<!--顶部开始-->
<#--<div class="top_div" style="max-width: 768px;">-->
    <#--<a onclick='history.go(-1)' class="cdan_div" style="line-height: 60px;">返回</a>-->
    <#--<div class="jypt_div">-->
        <#--<h1>体验券</h1>-->
    <#--</div>-->
    <#--<div id="commonbao" status="1"></div>-->
<#--</div>-->
<!--顶部结束-->
<div class="min dai_s" style="background-color: #001222;height: 100%;">
    <div class="nav width">
        <ul class="widthul">
            <li class="weishiyong yong_tu gaoliang">未使用</li>
            <li class="yishiyong yong_tu">已使用</li>
            <li class="yiguoqi yong_tu" style="width: 34%;">已过期</li>
        </ul>
    </div>

<#if cashCouponList??><#list cashCouponList as it>
    <#if it.status?number == 0>
        <div class="voucher">
            <ul>
                <li style="position: relative">

                    <img src="${ctxStatic}/img/images2/voucher_gq.png">
                    <div>
                        <span class="money">${it.money}</span>
                        <p class="condition"><#if it.minMoney = 0>直用券<#else>满<span>${it.minMoney}</span>可用</#if></p>
                        <p class="scope">使用范围：<span>全场使用</span></p>
                        <p class="time">有效时间：<span>${it.createTime?date}——${it.endDate?date}</span></p>
                        <p class="how_much"><span>${it.cashCouponCounts}</span>张</p>
                        <a href="${ctxWap}/trade"
                           style="display: block;position:absolute;width:5rem;height:9rem;top:8%;right:3%;"></a>
                        <!--点击使用-->
                    </div>
                </li>
            </ul>
        </div>
    </#if>
    <#if it.status?number == 1>
        <div class="voucher_ysy" style="display: none">
            <ul>
                <li style="position: relative;">
                    <div class="zhangtu"></div>
                    <img src="${ctxStatic}/img/images2/voucher_gq.png">
                    <div>
                        <span class="money">${it.money}</span>
                        <p class="condition"><#if it.minMoney = 0>直用券<#else>满<span>${it.minMoney}</span>可用</#if></p>
                        <p class="scope">使用范围：<span>全场使用</span></p>
                        <p class="time">有效时间：<span>${it.createTime?date}——${it.endDate?date}</span></p>
                        <p class="how_much"><span>${it.cashCouponCounts}</span>张</p>
                    </div>
                </li>
            </ul>
        </div>
    </#if>
    <#if it.status?number == 2>
        <div class="voucher_gq" style="display: none">
            <ul>
                <li style="position: relative">
                    <img src="${ctxStatic}/img/images2/voucher_gq.png">
                    <div>
                        <span class="money">${it.money}</span>
                        <p class="condition"><#if it.minMoney = 0>直用券<#else>满<span>${it.minMoney}</span>可用</#if></p>
                        <p class="scope">使用范围：<span>全场使用</span></p>
                        <p class="time">有效时间：<span>${it.createTime?date}——${it.endDate?date}</span></p>
                        <p class="how_much"><span>${it.cashCouponCounts}</span>张</p>
                    </div>
                </li>
            </ul>
        </div>
    </#if>
</#list></#if>
    <!--<div class="bottom">没有更多了～</div>-->
</div>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script>
    $(function(){
        $(".nav ul li").click(function(){
            $(this).addClass("gaoliang").siblings().removeClass("gaoliang")

            if($(".nav ul li:nth-child(1)").hasClass("gaoliang")){
                $(".voucher").show();
            }else {
                $(".voucher").hide();
            }
            if($(".nav ul li:nth-child(2)").hasClass("gaoliang")){
                $(".voucher_ysy").show();
            }else {
                $(".voucher_ysy").hide();
            }
            if($(".nav ul li:nth-child(3)").hasClass("gaoliang")){
                $(".voucher_gq").show();
            }else {
                $(".voucher_gq").hide();
            }
        })
    })
</script>
</body>
</html>