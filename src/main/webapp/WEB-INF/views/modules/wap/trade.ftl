<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<#include "/wap/header.ftl" >
<#assign footer = "trade"/>
    <link href="${ctxStatic}/all/css/base.css?r="+Math.random() rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jaioyi.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xaidan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jiaoyiyueno.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jiaoyixiadan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/asserts/iSlider/css/iSlider.css" rel="stylesheet" type="text/css"/>
<#-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/all/css/default.css">
 <link rel="stylesheet" href="${ctxStatic}/all/css/demo.css">
 <link rel="stylesheet" href="${ctxStatic}/all/css/jquery.flipster.css">-->
    <script type="text/javascript" src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
    <style type="text/css">
        .swiper-slide p,.swiper-slide h5,.swiper-slide h4{
            text-align: center;
            margin: 0;
            padding: 0;
            font-weight: normal;
        }
        .swiper-slide p{
            padding-top: 0.23rem;
            color: #000;
            font-size: 0.4rem;
        }
        .swiper-slide h5{
            padding-top: 0.23rem;
            color: #000;
            font-size: 0.7rem;
            line-height: 0.6rem;
            font-weight: bold;
        }
        .swiper-slide h4{
            padding-top: 0.23rem;
            color: #000;
            font-size: 0.4rem;
            line-height: 0.6rem;
        }
        .translucent{
            height: 2.6rem;
            width: 5.6rem;
            background: rgba(255,255,255,1);
        }
        .container {
            -webkit-touch-callout:none;
            -webkit-user-select:none;
            -khtml-user-select:none;
            -moz-user-select:none;
            -ms-user-select:none;
            user-select:none;
        }
        .container_1_shadow {
            background-color: #000000;
            filter: alpha(Opacity=20);
            -moz-opacity: 0.2;
            opacity: 0.2;
        }
        .p_money p_money_form{
            -webkit-user-select:none;
        }
        #nologin{
            background: #1F1F1F;
        }
        #nologin:after{
            content: '';
            display: block;
            height: 1.7rem;
        }
        .popBox, .prompt-box{
            z-index: 9999999999999;
        }
    </style>
</head>
<body onunload="if(ws != null) ws.close();">
<section class="wrap-page">
    <!--内容部分-->
    <div class="in_top clearfix" style="background-color: #1f1f1f">
        <div class="in_user left text-center">
            <a onclick="<#if user?? >window.location.href = ctxWap + '/user?r='+Math.random()<#else > reg()</#if>">
                <div class="img">
                <#if user??>
                    <img src="<#if user.userHeader??>${user.userHeader}
                        <#else>${ctxStatic}/img/images2/log.png</#if>" alt="">
                </#if>
                </div>
                <p>个人中心</p>
            </a>
        </div>
        <div class="in_acount left">
            <p class="ac_a">余额(元)</p>
            <p class="a_money" style="color: #B4B4B4;"><#if user??>${user.money}<#else >0</#if></p>
        </div>
        <div class="in_money right">

            <a onclick="<#if user?? >window.location.href = '${ctxWap}/coupon/list?r='+Math.random()<#else > reg()</#if>" class="btn btn-primary s mall left">
                <span class="money_zhang_num" style=""><#--<img src="${ctxStatic}/all/image/jttsss.png"/>-->&nbsp;&nbsp;<b
                        id="coupon_total_count" style="font-weight: normal;">0</b>张&nbsp;&nbsp;</span>
                <b class="span-img-logo span-img-logo1" ></b> 券
            </a>
            <a onclick="withdraw()" class="btn btn-primary s mall left">
                <b class="span-img-logo span-img-logo2"></b> 提
            </a>
        <#--<b class="bian-befoter"></b>-->
            <a onclick="<#if user?? >window.location.href = '${ctxWap}/pay/recharge?r='+Math.random()<#else > reg()</#if>" class="btn btn-primary s mall left">
                <span class="shoufan_num"><#--<img src="${ctxStatic}/all/image/jttsss.png"/>-->首充返100%</span>
                <b class="span-img-logo span-img-logo3"></b> 充
            </a>
        <#--<b class="bian-befoter"></b>-->

        </div>
    </div>
<#--  <s class="xianTiao"></s>-->
    <div class="iTaba f36 j_cailiao" id="iTaba">
        <ul class="ita_t my-bar my_c" style="border-bottom: 0.03rem solid #2a282c;background:#002b54">
        <#if contracts??>
            <#list contracts as it>
                <#if it.precision == 0>
                    <#assign b='0' />
                </#if>
                <#if it.precision == 1>
                    <#assign b='0.0' />
                </#if>
                <#if it.precision == 2>
                    <#assign b='0.00' />
                </#if>
                <#if it.precision == 3>
                    <#assign b='0.000' />
                </#if>
                <#if it.precision == 4>
                    <#assign b='0.0000' />
                </#if>
                <li class="my-bar-item" id="contract_${it_index}" onclick="show1(${it_index})">
                    <#if !code??><#assign code=contracts[0].code/></#if>
                    <#if it.code == code><#assign _cc=it_index/></#if>
                    <p class="jingyin" >${it.name}</p>
                <#--${it.name}-->
                    <#assign it_m=markets[it_index]/>
                    <!--当前价-->
                    <p id="currentMoney_${it.code}"
                       class="<#if it_m.price?number - it_m.open?number gt 0>my-bar_red<#else>my-bar_green</#if>">${markets[it_index].price?number?string(b)}
                        <i id="show1_${it.code}"
                           class="icon <#if it_m.price?number - it_m.open?number gt 0>i_red<#else>i_green</#if>"></i>
                    </p>

                </li>
            </#list>
        </#if>
        </ul>
    <#--<s class="xianTiao"></s>-->
        <div class="bd">
            <div class="tempWrap" style="overflow:hidden; position:relative;">
                <ul style="width: 1242px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(0px, 0px) translateZ(0px);">
                <#if contracts??>
                    <#list contracts as it>
                    <#if !isMarketOpen || !it.marketOpen || !markets[it_index]??>
                    <#-- <li class="my-bar"
                         style="display: table-cell; vertical-align: top; width: 414px;background: #1E1E1E">
                         <span class="my-bar-item" id="high_${it.code}">
                                 开盘：
                             </span>
                         <span class="my-bar-item" id="high_${it.code}">
                                 最高：
                             </span>
                         <span class="my-bar-item" id="low_${it.code}">
                                 最低：
                             </span>
                         <span class="my-bar-item" id="currentTime_${it.code}">
                                 时间：
                         </span>
                     </li>-->
                    <#else>
                        <#assign it_m=markets[it_index]/>
                    <#-- <li class="my-bar" style="display: table-cell; vertical-align: top; width: 414px;">
                         <span class="my-bar-item" id="high_${it.code}">
                             最高：${it_m.high?number?string(b)}
                         </span>
                         <span class="my-bar-item" id="low_${it.code}">
                             最低：${it_m.low?number?string(b)}
                         </span>
                         <span class="my-bar-item" id="currentTime_${it.code}">
                             时间：${current_time?time}
                         </span>
                     </li>-->
                    </#if>
                </#list>
                </#if>
                </ul>
            </div>
        </div>
    </div>
    <!--轮播-->
<#--<div id="iSlider-wrapper" style="height: 5.6rem;/* width: 100%; */margin-top: -0.8rem;margin-bottom: -1rem;z-index: -1;">-->
<#--</div>-->
    <#--<div id="iSlider-wrapper" class="swiper-container" style="margin-bottom: 0.5rem;">
        <div class="swiper-wrapper">
            <div class="swiper-slide" style="height: 2.66rem; width: 5.66rem;  background-color:rgba(255, 255, 255, 0.3);border-radius: .3rem;">
                <div style="height: 2.5rem; width: 5.5rem;margin: 0.08rem auto; background-color: #c1a33e;border-radius: .3rem;" >
                    <p>盈战时间</p>
                    <h5><span>15</span>秒</h5>
                    <h4>收益率:<span>80%</span></h4>
                </div>
            </div>
            <div class="swiper-slide" style="height: 2.66rem; width: 5.66rem; background-color:rgba(255, 255, 255, 0.3)">
                <div style="height: 2.5rem; width: 5.5rem;margin: 0.08rem auto; background-color: #c1a33e;" >
                    <p>盈战时间</p>
                    <h5><span>30</span>秒</h5>
                    <h4>收益率:<span>82%</span></h4>
                </div>
            </div>
            <div class="swiper-slide" style="height: 2.66rem; width: 5.66rem;  background-color:rgba(255, 255, 255, 0.3)">
                <div style="height: 2.5rem; width: 5.5rem;margin: 0.08rem auto; background-color: #c1a33e;" >
                    <p>盈战时间</p>
                    <h5><span>60</span>秒</h5>
                    <h4>收益率:<span>85%</span></h4>
                </div>
            </div>
        </div>
    </div>-->
    <ul class="biaopan">
        <li onclick="changeBlock(this, 0);" class="bianpan-li">
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>30</span>秒</h5>
            <h4>收益:<span>80%</span></h4>
        </li>
        <li onclick="changeBlock(this, 1);">
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>60</span>秒</h5>
            <h4>收益:<span>82%</span></h4>
        </li>
        <li onclick="changeBlock(this, 2);">
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>180</span>秒</h5>
            <h4>收益:<span>85%</span></h4>
        </li>
    </ul>
    <div class="b_btn clearfix" style="z-index: 999">
        <a class="left buy_up" onclick="sureBox(0);">
            <h6></h6>
            <i class="icon i_z"></i>
            <b class="ka-si-la">买涨</b>
        </a>
        <a class="right buy_down" onclick="sureBox(1);">
            <h6></h6>
            <i class="icon i_die"></i>
            <b class="ka-si-la">买跌</b>
        </a>
    </div>
    <div class="clear"></div>

    <div id="nologin">
        <p style="float: left; width: 60%; padding-left: 10px; padding-top: 4px; color: darkgrey; line-height: 24px; font-size: 14px;">商品走势图</p>
        <div style="display: block;position: relative;/*margin-top: -1.3rem*/">
            <div class="container" id="container" style="position: relative;white-space: nowrap" ontouchstart="return _on_touch_start();" ontouchend="_on_touch_end();" ontouchcancel="_on_touch_end();"></div>
            <div class="container" style="position: relative; top: 0; left: 0; width: 100%;"></div>
        </div>
        <ul class="content_fenshixian" style="display: block">
            <li class="kdata_c" onclick="showKClick1(0);">分时图</li>
            <li id="1" class="kdata_c" onclick="showKClick1(1);">1分钟K线</li>
            <li id="5" class="kdata_c" onclick="showKClick1(2);">5分钟K线</li>
            <li id="30" class="kdata_c" onclick="showKClick1(3);">15分钟K线</li>
            <li id="1h" class="kdata_c" onclick="showKClick1(4);">30分钟K线</li>
            <li id="1.5h" class="kdata_c" onclick="showKClick1(5);">60分钟K线</li>
        </ul>
    <#-- <s class="xianTiao"></s>-->
    <#--时间选择-->
    <#--<nav class="k-img">
        <article id="article">
            <span class="trade-chart-type line active" onclick="showKClick(0, true);">走势</span>
            <span class="trade-chart-type stock " onclick="showKClick(1, true);">K线</span>
        </article>
        <section class="trade-chart-period m1 active" onclick="showKClick(1);">1M <b></b></section>
        <section class="trade-chart-period m5" onclick="showKClick(2);">5M<b></b></section>
        <section class="trade-chart-period m15" onclick="showKClick(3);">15M<b></b></section>
        <section class="trade-chart-period m30" onclick="showKClick(4);">30M<b></b></section>
        <!--<section class="trade-chart-period h1" >1H</section>&ndash;&gt;
        <!--<section class="trade-chart-period d1" >1D</section>&ndash;&gt;
    </nav>-->


        <div class="radio">
        <#--<img src="${ctxStatic}/all/image/guangbo.png" class="img left">-->
        <#--<div class="radio_text" onclick="showBroadCast()" id="bc_show"></div>-->
        </div>
        <div class="chicang" style="min-height: 3rem !important;">
            <ul class="new_jilu">
                <li class="new_jilu_list on" id="newTrade" onclick="change('newTrade')">
                    <i></i>最新成交</li>
                <li class="new_jilu_list" id="holdTrade" onclick="change('holdTrade')"><i></i>持仓订单</li>
                <li class="new_jilu_list" id="tradeRecord" onclick="change('tradeRecord')"><i></i>交易记录</li>
            </ul>
            <!--最新成交-->
            <div id="newTradeDetail" class="newbd">
                <ul class="newbd_new">
                    <li>开仓时间</li>
                    <li>
                        <ul class="heyu_de">
                            <li>合约</li>
                            <li>方向</li>
                        </ul>
                    </li>
                    <li>定金</li>
                </ul>
            <#if newTradeRecord??>
                <#list newTradeRecord as it>
                    <ul class="newbd_new_list newHold">
                        <li>${it.buyTime?time}</li>
                        <li>
                            <ul class="heyu_de">
                                <li>${it.contractName}</li>
                                <#if it.buyUpDown?number  == 0>
                                    <li class="heyu_de_red">买涨</li>
                                <#else>
                                    <li class="heyu_de_green">买跌</li>
                                </#if>
                            </ul>
                        </li>

                    <#--<img src="<#if it.buyUpDown?number == 0>${ctxStatic}/all/image/zhang.png<#else>${ctxStatic}/all/image/xiajiang.png</#if>" class="left text-center">-->
                        <li><span style="margin-right: 1.2rem;">
                            <#if it.money == 0>
                                券
                            <#else >
                            ${it.money?c}
                            </#if>
                        </span><b style="width:<#if it.money lt 200 && it.money gt 0>3<#else> ${it.money * 0.015}</#if>px; background-color:<#if it.buyUpDown?number == 0>#D83F4E;<#elseif it.buyUpDown?number == 1>#1FC65B;<#else >#8f8a8a;</#if>"> </b></li>
                    </ul>
                </#list>
            </#if>
            </div>
            <!--持仓订单-->
            <div id="holdTradeDetail" class="chicangbd" style="display: none">
                <ul class="chicangbd_new" id="holdOrder">
                    <li>合约</li>
                    <li>方向</li>
                    <li>开仓时间</li>
                    <li>开仓价格</li>
                    <li>当前价格</li>
                    <li>定金</li>
                </ul>
            <#if list??>
                <#list list as it>
                    <ul class="chicangbd_new_list holdOrder" id="chicangbd_new_list_${it.id}" name="${it_index}"
                        style="cursor: pointer;">
                        <li name="${it.code}">${it.contractName}</li>
                        <#if it.buyUpDown?number == 0>
                            <li class="heyu_de_red" name="${it.buyUpDown}">买涨</li>
                        <#else>
                            <li class="heyu_de_green" name="${it.buyUpDown}">买跌</li>
                        </#if>
                        <li>${it.buyTime?time}</li>
                        <li>${it.buyPoint}</li>
                        <!--当前价格-->
                        <li class="hold_current_price_${it.code}"></li>
                        <li>${it.money}</li>
                    </ul>
                </#list>
            </#if>
            </div>
            <!--交易记录-->
            <div id="tradeRecordDetail" class="chicangbd" style="display: none">
                <ul class="chicangbd_new" id="tradingRecord">
                    <li>合约</li>
                    <li>方向</li>
                    <li>平仓时间</li>
                    <li>定金</li>
                    <li>盈利状况</li>
                    <li>状态</li>
                </ul>
            <#if toDayTradeList??>
                <#list toDayTradeList as it>
                    <ul class="chicangbd_new_list tradingRecord">
                        <li>${it.contractName}</li>
                        <#if it.buyUpDown?number == 0>
                            <li class="heyu_de_red">买涨</li>
                        <#else>
                            <li class="heyu_de_green">买跌</li>
                        </#if>
                        <li>${it.sellTime?time}</li>
                        <li>${it.money}</li>
                        <li>${it.difMoney}</li>
                        <#if ((it.sellPoint - it.buyPoint) gt 0 && (it.buyUpDown?number == 0)) ||  ( (it.sellPoint - it.buyPoint) lt 0 && (it.buyUpDown?number == 1) )>
                            <li class="chicangbd_red">盈</li>
                        <#elseif ( (it.sellPoint - it.buyPoint) lt 0 && (it.buyUpDown?number == 0) ) ||  ( (it.sellPoint - it.buyPoint) gt 0 && (it.buyUpDown?number == 1) )>
                            <li class="chicangbd_green">亏</li>
                        <#else>
                            <li class="chicangbd_grey">平</li>
                        </#if>
                    </ul>
                </#list>
            </#if>
            </div>
        </div>
</section>
<!--下单确认弹出框-->
<div class="popBox" id="sureBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd pop_bd_text pop_bd_text_kuang  f36" style="width:9rem; height: auto;margin-bottom: 0.3rem;">
            <div class="tishi">
                <div class="tishi_text">购买</div>
                <div class="close pas_close" onclick="hideBox('passErrorBox')"><img
                        src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
        <#--       <div class="xianTiao3"></div>-->
            <div class="pa_yc clearfix">
                <ul class="pa_y clearfix" id="chooseMoney">
                    <li id="m2" onclick="chooseMoney('m2')">5000元</li>
                    <li id="m3" onclick="chooseMoney('m3')">3000元</li>
                    <li id="m4" onclick="chooseMoney('m4')">1000元</li>
                    <li id="m5" onclick="chooseMoney('m5')">500元</li>
                    <li id="m6" onclick="chooseMoney('m6')">200元</li>
                    <li id="m7" class="on" onclick="chooseMoney('m7')">100元</li>
                    <input class="money" type="number" placeholder="其他金额" id="inputBuyMoney" onkeyup="inputBuyMoney()" onblur="inputBuyMoney()">
                <#--<li id="m8" onclick="chooseMoney('m8')">其他金额</li>-->
                </ul>
            </div>
        <#--<div class="xianTiao3"></div>-->
            <div id="coupon_div" style="display: block;">
            <#-- <div id="coupon_type1_div" class="coupon_type_div" style="" onclick="coupon_type_click(1);">
                 <font style="font-size: 0.4rem;">必盈券</font><br/>10元<span id="coupon_type1_num_span">1</span>张
             </div>-->
                <div id="coupon_type2_div" class="coupon_type_div" style="width: 3rem" onclick="coupon_type_click(2);">
                    <font style="font-size: 0.4rem;">直盈券</font><br/>
                    <select id="directProfitCoupon">
                        <option value="10">10元</option>
                        <option value="20">20元</option>
                        <option value="50">50元</option>
                        <option value="100">100元</option>
                    </select>
                    <span id="coupon_type2_num_span">1</span>张
                </div>
                <div id="coupon_type3_div" class="coupon_type_div coupon_type_div_selected" style="width: 3rem"
                     onclick="coupon_type_click(3);">
                    <font style="font-size: 0.4rem;">增益券</font><br/>
                    <select id="gainProfitCoupon">
                        <option value="10">10元</option>
                        <option value="20">20元</option>
                        <option value="50">50元</option>
                        <option value="100">100元</option>
                    </select>
                    <span id="coupon_type3_num_span">1</span>张
                </div>
                <div id="coupon_type3_div_select" style="width: 2.2rem;height: auto;float: left;margin-left: 0rem;font-size: 0.5rem;line-height: 0.6rem;text-align: center;">
                    <div onclick="coupon_type_click(31);" class="" style="width: 0.6rem;/* float: left; */height: 0.6rem;margin-left: 0.1rem;/* margin-top: 0.05rem; */background-color: #000c1a;color: #bababa;display: block;">+</div>
                    <div onclick="coupon_type_click(32);" class="" style="width: 0.6rem;/* float: left; */height: 0.6rem;margin-left: 0.1rem;margin-top: 0.28rem;background-color: #000c1a;color: #bababa;/* display: inline-block; */">-</div>
                </div>

            </div>
            <div class="pa_money">
                <div class="goumai">
                    购买：<span id="showMoney">100.00元</span>
                    <p style="display: inline-block;">预期收入:<span id="willMoney">900.00元</span></p>
                </div>
                <ul class="heyue">
                    <li class="left">合约:<span id="contractName">白银</span></li>
                    <li class="left">结算周期:<span id="buyTimeSecond">15秒</span></li>
                </ul>
                <ul class="heyue">
                    <li class="left">订单方向:<span class="heyue_red" id="showUpAndDown"></span></li>
                    <li class="left price">当前价格:<span class="dangqian_green" id="showPrice"></span></li>
                </ul>
            </div>
        <#--   <div class="xianTiao3"></div>-->
            <button class="login" type="submit" onclick="_make()">确定</button>
        </div>
    </div>
</div>
<!-- 不在交易时段-->
<div class="popBox" id="stopBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd buzaijiaoyi f36" style="height:5.5rem;">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.45rem;line-height: 1rem;bored-radius:2px 2px 0 0 ">
                    提示
                </div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.25rem;"><img onclick="hideBox('stopBox')"
                                                                                         src="${ctxStatic}/all/image/q_03.png">
                </div>
            </div>
        <#--<div class="xianTiao3"></div>-->
            <div style="width: 8rem; text-align: center">
                <div style=" text-align: center;line-height:0.8rem;padding-top:0.5rem;color:#FF2A40; font-size:0.6rem;">
                    该合约未开盘
                </div>
                <p class="no center" style="color:#989ca3;padding-top:0.3rem;font-size:0.4rem;">工作日正常交易</p>
            </div>
            <button class="sure make_surea" style="margin-top: 0.5rem; font-size:0.5rem;" onclick="hideBox('stopBox')">确认</button>
        </div>
    </div>
</div>
<div class="popBox" id="WinBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd buzaijiaoyi f36" style="overflow: auto">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.5rem;line-height: 1rem;">盈战开始</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.27rem;"><img onclick="hideBox('WinBox')"
                                                                                         src="${ctxStatic}/all/image/q_03.png">
                </div>
            </div>
        <#--<div class="xianTiao3"></div>-->
            <div class="deposit">
                <span>合约: <b id="overContractName">占位</b></span>
                <span>定金: <b id="overContractMoney">占位</b></span>
            </div>
            <div class="p_money">
                <h2 id="overTime"></h2>
                <p>执行价格:<span id="hold_Price">暂无</span></p>
                <p>当前价格:<span id="now_Price" name="">暂无</span></p>
            </div>
        <#--   <div class="xianTiao3"></div>-->
            <ul class="p_point">
                <li>订单方向:<span class="p_point_red" id="sub">暂无</span></li>
                <li>预测结果:<span class="point_red" id="trend">暂无</span></li>
            </ul>
        <#--<div class="xianTiao3"></div>-->
            <button class="make_sureb" style="margin-top: 0.5rem; " onclick="hideBox('WinBox')">继续下单</button>
        </div>
    </div>
</div>
<!--登陆框-->
<div class="popBox" id="loginBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd buzaijiaoyi f36 pop_form" style="overflow: auto">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.5rem;line-height: 1rem;">注册</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.28rem;"><img onclick="hideBox('WinBoxs')"
                                                                                         src="${ctxStatic}/all/image/q_03.png">
                </div>
            </div>
        <#-- <div class="xianTiao3"></div>-->
            <form id="regist_form" action="${ctxWap}/reg" METHOD="post" enctype="application/x-www-form-urlencoded">
                <div class="p_money p_money_form">

                    <p><input type="text" name="mobile" placeholder="请输入11位手机号码" maxlength="11" data-validtype="require"></p>
                    <p><input type="password" name="tradePassword" placeholder="请输入密码" data-validtype="require"></p>
                    <p class="p_money_form_pp">
                        <input type="text" name="validCode" class="p_money_form_hourse"
                               placeholder="请输入短信验证码" maxlength="4" data-validtype="require"><input type="button" id="btn" style="margin-right: 0rem; width: 2.5rem;text-align:center" value="获取验证码" onclick="sendSms(this)"></input>
                    </p>
                    <input type="hidden" placeholder="请输入邀请码" name="agentInviteCode" class="text"
                           data-validtype="require" value="000000">
                </div>
            <#--<div class="xianTiao3"></div>-->
                <button type="button" class="make_sureb" style="margin-top: 0.5rem; " onclick="formSubmit()">新用户注册</button>
                <p><input type="hidden" name="chnName" value="18666666666"></p>
            </form>
            <button class="make_sureb"  style="margin-top: 0.5rem;background-color: #ba4d36;color: #dcdcdc " href="javascript:void(0)" onclick="goLogin()">已有账号登陆</button>
        </div>
    </div>
</div>
<div class="popBox" id="WinBoxs" style="display: none;">
    <div class="popCont">
        <div class="pop_bd buzaijiaoyi f36" style="overflow: auto">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.5rem;line-height: 1rem;">盈战开始</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.28rem;"><img onclick="hideBox('WinBoxs')" src="${ctxStatic}/all/image/q_03.png">
                </div>
            </div>
        <#--  <div class="xianTiao3"></div>-->
            <div class="deposit">
                <span>合约: <b id="buyContractName">占位</b></span>
                <span>定金: <b id="buyContractMoney">占位</b></span>
            </div>
            <div class="p_money">
                <h2 id="difMoney"></h2>
                <p>执行价格:<span id="buyPoints">暂无</span></p>
                <p>到期价格:<span id="sellPoints" name="">暂无</span></p>
            </div>
        <#--  <div class="xianTiao3"></div>-->
            <ul class="p_point">
                <li>订单方向:<span class="p_point_red" id="subs">暂无</span></li>
                <li>预测结果:<span class="point_red" id="trends">暂无</span></li>
            </ul>
        <#-- <div class="xianTiao3"></div>-->
            <button class="make_sureb" style="margin-top: 0.5rem; " onclick="hideBox('WinBoxs')">继续下单</button>
        </div>
    </div>
</div>
<div class="xianTiao2"></div>
</div>
<#if loginStatus?? && loginStatus == '0'>
<!--动画-->
<div id="guide">
    <img class="img01"src="${ctxStatic}/all/image/roll_msg.png" alt="">
    <div class="img02">
        <h4>提示</h4>
        <p>恭喜您!您已获得1张10元直盈券+9张100元增益券,请点击我的优惠券查看</p>
        <h4>开始新手教程</h4>
    </div>
    <div class="img03" style="display: none;">
        <!--第一步-->
        <img class="img03a" src="${ctxStatic}/all/image/01a.png" alt="">
        <img class="img03b" src="${ctxStatic}/all/image/01b.png" alt="">
        <img class="img03c" src="${ctxStatic}/all/image/01c.png" alt="">
        <!--第二步-->
        <img class="img04a" src="${ctxStatic}/all/image/02a.png" alt="">
        <img class="img04b" src="${ctxStatic}/all/image/02b.png" alt="">
        <img class="img04c" src="${ctxStatic}/all/image/02c.png" alt="">
        <img class="img04d" src="${ctxStatic}/all/image/01c.png" alt="">
        <!--第三步-->
        <img class="img05a" src="${ctxStatic}/all/image/02d.png" alt="">
        <img class="img05b" src="${ctxStatic}/all/image/01c.png" alt="">
        <!--第四步-->
        <img class="img06a" src="${ctxStatic}/all/image/02e.png" alt="">
        <img class="img06b" src="${ctxStatic}/all/image/01c.png" alt="">
        <!--第五步-->
        <img class="img07a" src="${ctxStatic}/all/image/01111111.gif" alt="">
        <img class="img07b" src="${ctxStatic}/all/image/06b.jpg" alt="">
    </div>
</div>
<!--风险揭示书-->
<div id="risk" style="margin-top: 4rem;">
    <div id="frame">
        <h4>风险揭示书</h4>
        <div style="width: 100%;height:80%;overflow: auto;padding: 0 .5rem;">
            <div>
                <p style="color:#FF0000;font-size: .5rem">尊敬的投资者：</p>
                <p>本交易平台开展的现货电子交易业务，是一种潜在收益和潜在风险并存的投资业务。</p>
                <p>请您认真阅读本风险提醒，确认充分认识并完全理解可能遇到的各类风险，点击“我已阅读并
                    同意《风险揭示书》”后，该风险提醒即对投资者产生法律约束力。</p>
                <section>
                    <h5>一、重要提示</h5>
                    <p>本平台交易具有高投机性和高风险性，不适合利用养老资金、借贷资金（如金融机构贷款或私
                        人贷款）等资金开展或参与的交易业务的投资者，只合适满足以下条件的投资者：</p>
                    <p>1、年满18周岁、未满60周岁，具有完全民事行为能力的自然人，或依法注册成立且合法存续
                        的企业法人或其他经济组织；</p>
                    <p>2、能够充分理解有关于此交易的一切风险，并且具有风险承受能力的投资者；</p>
                    <p>3、因投资失误而造成账户资金部分或全部损失、仍不会改变其正常生活方式的投资者；</p>
                    <p>4、有一定的金融产品投资经验，对投资市场有充分认识，懂得一些投资技巧；</p>
                    <p>5、已阅读本平台相关规则制度，理解并接受本平台规则制度的相关规定。</p>
                </section>
                <section>
                    <h5>二、投资者账户使用及管理</h5>
                    <p>1、本交易平台为投资开设的交易账户实行一户一码制（即一个投资者只对应一个交易账户）
                        。投资者可使用该交易账户交易其选择的本平台交易业务范围内提供的全部交易品种；</p>
                    <p>2、本平台仅通过投资者交易账户的账号和密码核实投资者的身份。凡通过投资者的交易账户
                        的账号和密码进行的交易操作，无论是否确属本人进行的交易，均视为是投资者本人操作；</p>
                    <p>3、投资者必须对交易账户和密码进行妥善保管。投资者密码丢失的，可进行密码重置。所有
                        因投资者密码遗失及被盗（含手机丢失）导致的损失，或因保管不善及其他不可归责于本平台
                        而造成的一切后果，均与本平台无关，由投资者自行承担；</p>
                    <p>4、投资者的交易账户只限本人使用，不得转借他人（或其他机构），如交易账户转借所引起
                        的一切纠纷和损失均由投资者自行解决和承担；</p>
                    <p>5、投资者不得以任何方式委托本平台及其工作人员以直接或间接操作交易账户、代替投资者
                        下达交易指令等方式管理其交易账户，否则投资者应自行承担后果。</p>
                </section>
                <section>
                    <h5>三、相关的风险揭示</h5>
                    <p>投资者进行交易之前应充分认识并完全理解可能遇到的各类风险。以下仅为本平台交易产品特
                        点列举的主要风险种类和对风险因素的客观分析，并不保证贯通本交易平台的全部风险种类，
                        同时也不代表对市场情况的预测。</p>
                </section>
                <section>
                    <h5>（一）交易风险</h5>
                    <p>1、在进行交易以前，投资者应该了解平台交易产品的价格（规则）可能导致快速的盈利或亏
                        损；</p>
                    <p>2、本平台的交易品种的价格受多种因素（价格发生不利波动）的影响，并且影响机制非常复
                        杂，您在实际操作中存在出现投资失误的可能性，如果不能有效控制风险，则可能遭受较大的
                        损失，您必须独自承担由此导致的一切损失；</p>
                    <p>3、本交易平台的价格可能会与其他途径的报价存在微弱的差距，并不能保证本平台交易价格
                        与其他市场保持完全的一致性；</p>
                    <p>4、您在本交易平台上所提交的市价单一经成交，即不可撤销，您必须接受这种方式可能带来
                        的风险；</p>
                    <p>5、您的成交单据必须建立在自主决定的基础之上。交易中心、会员（及其分支机构）、会员
                        代理人及前述主体的工作人员提供的任何关于市场的分析和信息，仅供投资者参考，不构成任
                        何要约或承诺。由此而造成的交易风险由您自行承担。</p>
                </section>
                <section>
                    <h5>（二）其他相关的风险</h5>
                    <p>1、政策风险：国家法律、法规、政策以及规章的变化，紧急措施的出台，相关监管部门监管
                        措施的实施，交易中心交易规则的修改等原因，均可能会对您的投资产生影响，或由上述原因
                        导致交易系统临时或永久性关闭及其他风险，交易中心和本平台不承担责任，您必须承担由此
                        导致的损失；</p>
                    <p>2、技术风险：1）此业务通过电子通讯技术和互联网技术来实现。由通讯或网络故障导致的某
                        些服务中断或延时可能会对您的投资产生影响；2）您的手机、操作系统等软硬件环境有可能
                        由于自身原因和/或被病毒、网络黑客攻击等原因，从而导致您的交易系统连接受到影响，使
                        您的投资决策无法正确和/或及时执行。对于上述不确定因素带来的风险，有可能会对您的投
                        资产生影响，您应该充分了解并承担由此造成的全部损失；</p>
                    <p>3、不可抗力及突发事件风险：受自然灾害、战争等不能预见、不能避免、不能克服的不可抗
                        力事件影响，或受国际上各种政治、经济、突发事件等因素的影响，或受通讯故障、系统故障
                        、电力中断、市场停止交易等意外事件或金融危机、国家政策变化等因素的影响，可能对投资
                        者正常买卖本平台产品造成影响，进而可能使投资者受到损失。</p>
                </section>
                <section>
                    <h5>四、免责声明</h5>
                    <p>1、本平台显示的实时行情数据、产品价格、指数等信息，有可能因为互联网通信繁忙或其他
                        技术原因，导致数据通信出现延迟、中断、数据错误等情况，仅供参考，不作为投资或进行任
                        何交易买卖的建议；</p>
                    <p>2、本平台发布的任何评论意见、文章、观点，仅作为投资资讯以供参考，不作为投资或交易
                        买卖的建议。如果投资者依据以上信息进行投资或进行交易买卖而遭受损失，本平台对此不承
                        担责任；</p>
                    <p>3、本平台如因系统维护或升级而需暂停服务时，将事先公告。若因线路及非控制范围外的技
                        术故障或其它不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，本平台对此
                        不负任何责任。</p>
                    <p>最后，我们诚挚地希望和建议您，从风险承受能力等自身实际情况出发，审慎地决定是否参与
                        本平台的投资，合理的配置自己的金融资产。
                        本人已认真阅读以上风险说明并完全理解和同意，自愿承担由此造成的风险，以及由此带来的
                        一切可能的损失。</p>
                </section>
            </div>
        </div>
        <h4>我已阅读并同意</h4>
    </div>
</div>
</#if>

</body>

<link href="${ctxStatic}/asserts/swiper/swiper-3.4.2.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/asserts/swiper/animate.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctxStatic}/asserts/swiper/swiper-3.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/swiper/swiper.animate1.0.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript">
    function  withdraw() {
        var d=new Date();
        var xq=d.getDay();
        var h=d.getHours();
        var m=d.getMinutes();
        var sj=( h < 10 ? '0':'')+h+':'+(m < 10 ? '0':'')+m;
        if (xq <1 || xq >5){
           // alert("请在周一至周五的9:00到16:00提现，谢谢配合");
            <#if user?? >window.location.href = '${ctxWap}/money/out?r='+Math.random()<#else > reg()</#if>;
        }else if(xq>=1 && xq<=5 && sj < '09:00' || sj > '16:00'){
            //alert("请在周一至周五的9:00到16:00提现,谢谢配合");
            <#if user?? >window.location.href = '${ctxWap}/money/out?r='+Math.random()<#else > reg()</#if>;
        }else{
            <#if user?? >window.location.href = '${ctxWap}/money/out?r='+Math.random()<#else > reg()</#if>;
        }
    }
</script>
<script type="text/javascript">
    function show1(index) {
        current_code_p = index;
        showKClick(1);
    }
    new Swiper('#iSlider-wrapper', {
        effect: 'coverflow',
        slidesPerView: "auto",
        loop: true,
        centeredSlides: true,
        initialSlide: 0,
        slideToClickedSlide: true,
        coverflow: {
            rotate: 0,
            stretch: 20,
            depth: 150,
            modifier: 2,
            slideShadows: true
        },
        onTransitionStart: function (e) {
            imageId = e.realIndex;
        }
    });
</script>
<#include "/wap/footer.ftl"/>
<script type="text/javascript" src="${ctxStatic}/all/js/slide.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/formatString.js"></script>
<script type="text/javascript">
    TouchSlide({
        slideCell: "#focus",
        titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell: ".bd ul",
        effect: "left",
        autoPlay: true,//自动播放
        autoPage: true
    });
    TouchSlide({
        slideCell: "#iTaba",
        titCell: ".ita_t li",
        mainCell: ".bd ul",
        effect: "left",
        autoPlay: false,
        autoPage: false
    });

    <#if errors??>
    layer.msg('${errors.error}');
    </#if>

    $('.container').height(document.body.clientHeight
            - $('#bar-tab').outerHeight() - $('.ita_t').outerHeight() - $('.bd').outerHeight()
            - $('.b_btn').outerHeight() + $('#container').outerHeight() - $('#iSlider-wrapper').outerHeight() / 2 - 100);

    $('.container').eq(1).css('margin-top', -$('.container').eq(1).height());

    $('.b_btn,.iTaba,.content_fenshixian,.chicang').bind('touchmove', function () {
        $('.container').eq(1).removeClass('container_1_shadow');
        $('.container').eq(1).show();
    });

    var _move_container = true;
    var _container_1_shadow = null;
    $('.container').eq(1).bind('touchstart', function () {
        console.log('touchstart');
        _container_1_shadow = setTimeout(function () {
            $('.container').eq(1).addClass('container_1_shadow');
        }, 500);
    });
    $('.container').eq(1).bind('touchmove', function () {
        console.log('touchmove');
        _move_container = false;
        clearTimeout(_container_1_shadow);
        $('.container').eq(1).removeClass('container_1_shadow');
    });
    $('.container').eq(1).bind('touchend', function () {
        console.log('touchend');
        if(_move_container){
            $('.container').eq(1).hide();
        }
        _move_container = true;
        clearTimeout(_container_1_shadow);
        $('.container').eq(1).removeClass('container_1_shadow');
        $('.container').eq(0).trigger('click');
    });

    var contractsList = ${_contracts};
    var current_code_p = ${_cc};
    var _contracts = ${_contracts};
    var _broadcastsJson = ${broadcastsJson};
    var _codes = ${_codes};
    var _isMarketOpen = ${_isMarketOpen};
    <#if user??>
    var _user_money = ${user.money?c};
    var _user_coupon_money = ${user.couponMoney?c};
    </#if>
    var _now = ${now?c};
    var _hold = <#if _list??>${_list}<#else>[]</#if>
    // 根据是否能获取最新数据来二次判断 开盘情况
    <#list markets as it>
    if (_contracts[${it_index}].marketOpen) {
        _contracts[${it_index}].marketOpen = <#if it??>true<#else>false</#if>;
    }
    </#list>
    var serverCurrentTime = '';
    //定时获取当前时间
    function getCurrentTime() {
        if (serverCurrentTime == '') {
            var timeStr = '${current_date_time}'.replace(/-/g, "/");
            console.log(timeStr);
            serverCurrentTime = new Date(timeStr);
        }
        serverCurrentTime.setTime(serverCurrentTime.getTime() + 1000);
        setTimeout(function () {
            getCurrentTime();
        }, 1000);
    };
    // 是否登录
    var _isLogin = <#if user??>true<#else >false</#if>;

    if(_isLogin){
        $(".a_money").text(fmoney('<#if user??>${user.money}</#if>', 2));
    }
</script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/js/highstock.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/js/themes/dark-unica.js' charset='utf-8'></script>
<script type="text/javascript" src="${ctxStatic}/js/modules/trade/trade.js?v=6"></script>
<#if loginStatus?? && loginStatus == '0'>
<!----------动画---------->
<style>
    #guide{
        position: fixed;
        top:0;
        left: 0;
        width:100%;
        height:100%;
        text-align: center;
        z-index: 999;
        background: rgba(0,0,0,.5);
    }
    #guide .img01{
        position: absolute;
        top:50%;
        left: 50%;
        margin-left: -186px;
        margin-top: -211px;
        transform: ;
    }
    #guide .img02 {
        display: none;
        position: absolute;
        top: 50%;
        width:80%;
        margin: 0 auto;
        left: 50%;
        transition: all .3s;
        margin-top: -110px;
        margin-left: -40%;
        font-size: 0.45rem;
        color: #C0C0C0;
        background: #363636;
    }
    #guide .img02 h4:first-child{
        background: #464646;
        height: 1.5rem;
        text-align: center;
        font-weight: normal;
        line-height: 1.5rem;
    }
    #guide .img02 p{
        padding: 0.9rem 0.43rem;
        line-height: 26px;
    }
    #guide .img02 h4:last-child{
        background: #D3B030;
        height:1.3rem;
        line-height: 1.3rem;
        font-weight: normal;
        text-align: center;
        color: #333;
    }
    #guide .img03 img{
        width:100%;
    }
    #guide .img03 .img03a{
        margin-top: -50px;
        width: 50%;
        opacity: 0;
        transition: all .5s;
    }
    #guide .img03 .img03b{
        width: 50%;
        left: -200px;
        position: absolute;
        top:55%;
        margin-top: -74px;
        opacity: 0;
        transition: all .5s .5s;
    }
    #guide .img03 .img03c{
        width: 50%;
        position: absolute;
        bottom:-10%;
        left: 50%;
        margin-left: -25%;
        opacity: 0;
        transition: all .5s .6s;
        /*animation:myfirst .5s .6s;*/
        /*-moz-animation:myfirst .5s .6s; !* Firefox *!*/
        /*-webkit-animation:myfirst .5s .6s; !* Safari and Chrome *!*/
        /*-o-animation:myfirst .5s .6s; !* Opera *!*/
    }
    @keyframes myfirst{
        form{
            transform: rotate(80deg);
        }
        20%{
            transform: rotate(-60deg);
        }
        40%{
            transform: rotate(40deg);
        }
        60%{
            transform: rotate(-20deg);
        }
        80%{
            transform: rotate(10deg);
        }
        to{
            transform: rotate(0deg);
            opacity: 0;
            bottom:-10%;
        }
    }
    #guide .img03 .img04a{
        position: absolute;
        top:0;
        display: none;
        transition: all .5s .5s;
        left:0;
    }
    #guide .img03 .img04b {
        position: absolute;
        top:-20%;
        left: 50%;
        opacity: 0;
        width:50%;
        margin-left: -45%;
        transition: all .5s .5s;
    }
    #guide .img03 .img04c {
        position: absolute;
        top: 45%;
        left:-20%;
        width: 50%;
        opacity: 0;
        transition: all .5s .5s;
        margin-left: -45%;
    }
    #guide .img03 .img04d{
        width: 50%;
        position: absolute;
        bottom:-10%;
        opacity:0;
        left: 50%;
        margin-left: -25%;
        transition: all .5s .5s;
    }
    #guide .img03 .img05a {
        position: absolute;
        top: -20%;
        left: 50%;
        width: 50%;
        margin-left: -45%;
        opacity: 0;
        transition: all .5s .5s;
    }
    #guide .img03 .img05b {
        width:50%;
        position: absolute;
        bottom: -10%;
        left: 50%;
        opacity: 0;
        margin-left: -25%;
        transition: all .5s .5s;
    }
    #guide .img03 .img06a{
        position: absolute;
        top: -20%;
        left: 50%;
        width: 50%;
        margin-left: -45%;
        opacity: 0;
        transition: all .5s .5s;
    }
    #guide .img03 .img06b{
        width:50%;
        position: absolute;
        bottom: -10%;
        left: 50%;
        opacity: 0;
        margin-left: -25%;
        transition: all .5s .5s;
    }
    #guide .img03 .img07a {
        width:50%;
        position: absolute;
        top: 0;
        left: 50%;
        margin-left: -40%;
        transition: all .5s .5s;
        opacity: 0;
    }
    #guide .img03 .img07b {
        position: absolute;
        width: 40%;
        margin-left: -20%;
        left: 50%;
        transition: all .5s .5s;
        bottom:0;
        opacity: 0;
    }
    #risk{
        width:100%;
        height:100%;
        background: rgba(0,0,0,.5);
        position: fixed;
        top:0;
        left: 0;
        display: none;
        z-index: 999;
    }
    #frame{
        background:#373537;
        width:80%;
        height:80%;
        position: absolute;
        top:10%;
        left: 50%;
        margin-left: -40%;
        margin-top: -40%;
        color:#bbb;
        font-family:'微软雅黑';
        font-size:0.42rem;
    }
    #frame h4:first-child{
        text-align: center;
        height:1.4rem;
        line-height: 1.4rem;
        background: #474547;
    }
    #frame h4:last-child{
        text-align: center;
        height:1.2rem;
        line-height: 1.2rem;
        background: #D3AF30;
        color: #373537;
        position: absolute;
        bottom: 0;
        width:100%;
    }
    #frame p{
        margin-top: .4rem;
    }
    #frame section{
        margin-top: .4rem;
    }
    #frame h5{
        font-size: .55rem;
        font-weight: normal;
    }
</style>

<script>
    $(function () {
        $('.img01').click(function () {
            console.log(456)
            $(this).hide(500);
            $('.img02').show(500);
        })
        $('.img02').click(function () {
            //第一步 显示
            $(this).hide(100);
            $('.img03').show();
            $('.img03a').css('opacity','1').css('width','100%').css('margin-top','30px')
            $('.img03b').css('opacity','1').css('width','100%').css('left','0')
            $('.img03c').css('opacity','1').css('bottom','10%')
        })
        $('.img03c').click(function () {
            //第一步 隐藏
            $('.img03c').css('animation','myfirst .5s').css('animation-fill-mode','forwards');
            $('.img03a').css('margin-top','50').css('width','50%').css('opacity','0');
            $('.img03b').css('margin-top','50').css('left','200px').css('opacity','0');
            //第二步 显示
            $('.img04b').css('width','90%').css('top','20px').css('opacity','1');
            $('.img04c').css('width','90%').css('left','50%').css('opacity','1');
            $('.img04d').css('opacity','1').css('bottom','10%')
        })
        $('.img04d').click(function () {
            //第二步 隐藏
            $('.img04b').css('width','50%').css('top','-20%').css('opacity','0');
            $('.img04c').css('width','50%').css('left','-20%').css('opacity','0');
            $('.img04d').css('animation','myfirst .7s').css('animation-fill-mode','forwards');
            //第三步 显示
            $('.img05a').css('opacity','1').css('width','90%').css('top','0');
            $('.img05b').css('opacity','1').css('bottom','10%');
        })
        $('.img05b').click(function () {
            //第三步 隐藏
            $('.img05a').css('opacity','0').css('width','50%').css('top','-20%');
            $('.img05b').css('animation','myfirst .7s').css('animation-fill-mode','forwards');
            //第四步 显示
            $('.img06a').css('opacity','1').css('width','90%').css('top','0');
            $('.img06b').css('opacity','1').css('bottom','10%');
        })
        $('.img06b').click(function () {
            //第四步 隐藏
            $('.img06a').css('opacity','0').css('width','50%').css('top','-20%');
            $('.img06b').css('animation','myfirst .7s ').css('animation-fill-mode','forwards');
            //第五步 显示
            $('.img07a').css('opacity','1').css('width','80%');
            $('.img07b').css('opacity','1').css('bottom','20%');
        })
        $('.img07b').click(function () {
            $('#guide').hide();
            $('#risk').show();
        })
        $('#frame h4:last-child').click(function () {
            $('#risk').hide();
        })
    })
</script>
</#if>

<#if !user??>
<script type="text/javascript" src="${ctxStatic}/js/jquery.cookie.js"></script>
</#if>
<script>
    $(function () {
        //首页底部高度
        $('.chicangbd').css('height',$('.newbd').css('height'))
    })
</script>
</html>