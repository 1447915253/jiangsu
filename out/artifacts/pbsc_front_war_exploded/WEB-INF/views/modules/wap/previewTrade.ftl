<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<#include "/wap/header.ftl" >
<#assign footer = "trade"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jaioyi.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xaidan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jiaoyiyueno.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/jiaoyixiadan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/asserts/iSlider/css/iSlider.css" rel="stylesheet" type="text/css"/>
    <#--<link href="${ctxStatic}/all/css/animate.css" rel="stylesheet" type="text/css"/>-->
<#--<link type="text/css" rel="Stylesheet" href="${ctxStatic}/all/css/imageflow.css" />-->
    <script type="text/javascript" src="${ctxStatic}/all/js/jquery.js"></script>

    <script type="text/javascript" src="${ctxStatic}/all/js/jquery.min.js"></script>
<#--<script type="text/javascript" src="${ctxStatic}/all/js/imageflow.js"></script>-->
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <style type="text/css">
        .iSlider-mask {
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            overflow: hidden;
        }
        .iSlider-mask-left {
            background:-webkit-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:-o-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:-moz-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.6));
        }
        .iSlider-mask-right {
            background:-webkit-linear-gradient(right, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:-o-linear-gradient(right, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:-moz-linear-gradient(right, rgba(0,0,0,0), rgba(0,0,0,0.6));
            background:linear-gradient(to left, rgba(0,0,0,0), rgba(0,0,0,0.6));
        }
    </style>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">

<#--动画效果开始-->
    <input id="IsSendReport" type="hidden" value="1" /><!--是否显示新手提示-->
<#--红包-->
    <div class="roll_msg open" id="redPackAnBIg" style="display: none;">
        <div class="" id="redPackAn">
            <img  src="${ctxStatic}/all/image/roll_msg.png">
        </div>
    </div>
<#--第1步-->
    <div class=" guide_box step1" id="guide" style="display: none">
        <div class="girl">
            <img src="${ctxStatic}/all/image/girl.png"></div>
    <#--<img src="Themes/quanmin/wz1.png" class="guide_wz1">-->
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png">
        </div>
        <div class="prompt">
            <h2>第一步</h2>根据必盈券使用规则，选择白银。
            <div class="btns_2">
                <a class="btn btn-primary next" id="guideNext">下一步</a>
            </div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <div class="mask2"></div>
    </div>
<#--第2步-->
    <div class="guide_box step2 " id="guide2">
        <div class="girl">
            <img src="${ctxStatic}/all/image/girl2.png"></div>
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png"></div>
        <div class="prompt">
            <h2>第二步</h2>选择时长60秒。
            <div class="btns_2">
                <a class="btn btn-primary prive" id="guide2Up">上一步</a>
                <a class="btn btn-primary next" id="guide2Next">下一步</a></div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <div class="mask2"></div>
    </div>
<#--第3步-->
    <div class="guide_box step3 " id="guide3">
        <div class="girl"></div>
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png"></div>
        <div class="prompt">
            <h2>第三步</h2>判断白银当前走势，选择买涨或买跌。
            <div class="btns_2">
                <a class="btn btn-primary prive" id="guide3Up">上一步</a>
                <a class="btn btn-primary next" id="guide3Next">下一步</a></div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <div class="mask2"></div>
    </div>
<#--第4步-->
    <div class="guide_box step4" id="guide4">
        <div class="girl"></div>
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png"></div>
        <div class="prompt">
            <h2>第四步</h2>选择必盈券并确定下单。
            <div class="btns_2">
                <a class="btn btn-primary prive" id="guide4Up">上一步</a>
                <a class="btn btn-primary next" id="guide4Next">下一步</a></div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <img src="${ctxStatic}/all/image/tk1.gif" class="tk">
        <div class="mask2"></div>
    </div>
<#--第5步-->
    <div class="guide_box step5" id="guide5">
        <div class="girl">
            <img src="${ctxStatic}/all/image/girl.png"></div>
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png"></div>
        <div class="prompt">
            <h2>第五步</h2>下单成功，请静待结果。
            <div class="btns_2">
                <a class="btn btn-primary prive" id="guide5Up">上一步</a>
                <a class="btn btn-primary next" id="guide5Next">下一步</a></div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <img src="${ctxStatic}/all/image/tk2.gif" class="tk">
        <div class="mask2"></div>
    </div>
<#--6-->
    <div class="guide_box step6" id="guide6">
        <div class="girl">
            <img src="${ctxStatic}/all/image/girl2.png"></div>
        <div class="sign">
            <img src="${ctxStatic}/all/image/arrow.png"></div>
        <div class="prompt">
            <h2>第六步</h2>60秒倒计时结束，恭喜您用10元必盈券成功盈利8元。
            <div class="btns_2">
                <a class="btn btn-primary prive" id="guide6Up">上一步</a>
                <a class="btn btn-primary next" id="guide6Next">下一步</a></div>
        </div>
        <div class="bg1"></div>
        <div class="bg2"></div>
        <img src="${ctxStatic}/all/image/tk3.gif" class="tk">
        <div class="mask2"></div>
    </div>
<#--7-->
    <div class="guide_end" id="guide_end" style="display: none">
        <div class="contents">
            <img src="${ctxStatic}/all/image/ani.png">
            <img src="${ctxStatic}/all/image/wz2.png" class="guide_wz2">
        </div>
        <a class="btn define" id="endSure">确定</a>
    </div>
<#--动画效果JS实现-->
    <script>
        if($("#IsSendReport").val() == 0 ){
            $("#redPackAnBIg").show();
        }
        $("#redPackAn").click(function () {
            $(this).parent().hide();
            $("#guide").addClass("guide")
        })
        $("#guideNext").click(function(){
            $("#guide").removeClass("guide");
            $("#guide2").addClass("guide");
        })
        $("#guide2Up").click(function () {
            $("#guide2").removeClass("guide");
            $("#guide").addClass("guide")
        })
        $("#guide2Next").click(function () {
            $("#guide2").removeClass("guide");
            $("#guide3").addClass("guide");
        })
        $("#guide3Up").click(function(){
            $("#guide3").removeClass("guide");
            $("#guide2").addClass("guide")
        })
        $("#guide3Next").click(function(){
            $("#guide3").removeClass("guide");
            $("#guide4").addClass("guide");
        })
        $("#guide4Up").click(function(){
            $("#guide4").removeClass("guide");
            $("#guide3").addClass("guide")
        })
        $("#guide4Next").click(function(){
            $("#guide4").removeClass("guide");
            $("#guide5").addClass("guide");
        })
        $("#guide5Up").click(function(){
            $("#guide5").removeClass("guide");
            $("#guide4").addClass("guide")
        })
        $("#guide5Next").click(function(){
            $("#guide5").removeClass("guide");
            $("#guide6").addClass("guide");
        })
        $("#guide6Up").click(function(){
            $("#guide6").removeClass("guide");
            $("#guide5").addClass("guide")
        })
        $("#guide6Next").click(function(){
            $("#guide6").removeClass("guide");
            $("#guide_end").show();
        })
        $("#endSure").click(function () {
            $("#guide_end").hide();
        })
    </script>



    <!--内容部分-->
    <div class="in_top clearfix">
        <div class="in_user left text-center">
            <a onclick="window.location.href = ctxWap + '/user?r='+Math.random()">
                <div class="img">
                    <#--<img src="<#if user.userHeader??>${user.userHeader}<#else>${ctxStatic}/img/images2/touxiang.png</#if>" alt="">-->
                </div>
                <p>个人中心</p>
            </a>
        </div>
        <div class="in_acount left">
            <p class="ac_a">余额(元)</p>
            <#--<p class="a_money">${user.money}</p>-->
        </div>
        <div class="in_money right">
        <#--<a onclick="window.location.href = '${ctxWap}/money/out'" class="btn in_money_ti left"><i></i>提</a>-->
        <#--<div class="shoufan right">-->
        <#--<a onclick="window.location.href = '${ctxWap}/pay/recharge'" class="btn in_money_chong left"><i></i>充</a>-->
        <#--&lt;#&ndash;<div class="shoufan_num">首充返100%</div>&ndash;&gt;-->
        <#--</div>-->
            <a  onclick="window.location.href = '${ctxWap}/pay/recharge'" class="btn btn-primary s mall">
                <span><img src="${ctxStatic}/all/image/jttsss.png" />首充返100%</span>
                <b class="span-img-logo span-img-logo3"></b> 充值
            </a>
            <b class="bian-befoter"></b>
            <a onclick="window.location.href = '${ctxWap}/money/out'" class="btn btn-primary s mall">
                <b class="span-img-logo span-img-logo2"></b> 提现
            </a>
            <b class="bian-befoter"></b>
            <a onclick="window.location.href = '${ctxWap}/money/out'" class="btn btn-primary s mall">
                <span style="width: 41px"><img src="${ctxStatic}/all/image/jttsss.png" />&nbsp;&nbsp;<b style="font-weight: normal;">0</b>张&nbsp;&nbsp;</span>
                <b class="span-img-logo span-img-logo1" style="height:15px;"></b> 券
            </a>
        </div>
    </div>
    <s class="xianTiao"></s>
    <div class="iTaba f36" id="iTaba">
        <ul class="ita_t my-bar" style="border-bottom: 0.03rem solid #000;">
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
                <li class="my-bar-item" id="contract_${it_index}" onclick="show1(${it_index})">
                    <#if !code??><#assign code=contracts[0].code/></#if>
                    <#if it.code == code><#assign _cc=it_index/></#if>
                    <#if !isMarketOpen || !it.marketOpen || !markets[it_index]??>
                    ${it.name}
                        <p id="currentMoney_${it.code}" class="my-bar_red">&nbsp;&nbsp;休市 <i id="show1_${it.code}" class="icon "></i></p>
                    <#else>
                    ${it.name}
                        <#assign it_m=markets[it_index]/>
                        <!--当前价-->
                        <p id="currentMoney_${it.code}" class="<#if it_m.price?number - it_m.open?number gt 0>my-bar_red<#else>my-bar_green</#if>">${markets[it_index].price?number?string(b)} <i id="show1_${it.code}" class="icon <#if it_m.price?number - it_m.open?number gt 0>i_red<#else>i_green</#if>"></i></p>
                    </#if>
                </li>
            </#list>
        </#if>
        </ul>
        <s class="xianTiao"></s>
        <div class="bd">
            <div class="tempWrap" style="overflow:hidden; position:relative;">
                <ul style="width: 1242px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(0px, 0px) translateZ(0px);">
                <#if contracts??>
                    <#list contracts as it>
                        <#if !isMarketOpen || !it.marketOpen || !markets[it_index]??>
                            <li class="my-bar" style="display: table-cell; vertical-align: top; width: 414px;background: #1E1E1E">
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

                            </li>
                        <#else>
                            <#assign it_m=markets[it_index]/>
                            <li class="my-bar" style="display: table-cell; vertical-align: top; width: 414px;">
                                <span class="my-bar-item" id="high_${it.code}">
                                    最高：${it_m.high?number?string(b)}
                                </span>
                                <span class="my-bar-item" id="low_${it.code}">
                                    最低：${it_m.low?number?string(b)}
                                </span>
                                <span class="my-bar-item" id="currentTime_${it.code}">
                                    时间：${current_time?time}
                                </span>
                            </li>
                        </#if>
                    </#list>
                </#if>
                </ul>
            </div>
        </div>
    </div>
    <!--轮播-->
    <div id="iSlider-wrapper" style="display:none;height: 6.5rem; width: 100%; margin-top: -1.5rem; margin-bottom: -1.0rem;">
    </div>
    <div class="clear"></div>

<#--表盘-->
    <ul class="biaopan">
        <li >
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>300</span>秒</h5>
            <h4>收益:<span>85%</span></h4>
        </li>
        <li class="bianpan-li">
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>60</span>秒</h5>
            <h4>收益:<span>80%</span></h4>
        </li>
        <li >
            <div>
            </div>
            <p>结算时间</p>
            <h5><span>180</span>秒</h5>
            <h4>收益:<span>82%</span></h4>
        </li>
    </ul>

    <div class="b_btn clearfix">
        <a class="left buy_up" onclick="sureBox(0);">
            <h6></h6>
            <b class="ka-si-la">买涨</b>
            <i class="icon i_z"></i>
        </a>
        <a class="right buy_down" onclick="sureBox(1);">
            <h6></h6>
            <b class="ka-si-la">买跌</b>
            <i class="icon i_die"></i>
        </a>
    </div>

<#--测试用DIV-->
<#--<div id="container" style="height: 285px">
    <img src="${ctxStatic}/all/image/2017-03-03_195621.png" style="width: 100%;height: 100%">
</div>-->

<#---->

    <!--商品走势图-->
    <div style="display: block;">
        <div class="" id="container"style="position: relative"></div>
        <!--时间段按钮-->
        <div id="addDate" style="display: none; width: 2rem; height: 1.2rem;position: absolute; top: 10.9rem; left: 8.7rem;">
            <button class="btn btn-primary" style="width: 0.8rem;height:0.8rem;line-height:0.7rem;background-color:#323232;border-radius:50%;font-size:0.6rem;color:#fff;border: 0;" onclick="addDate(1)">+</button>
            <button class="btn btn-primary" style="width: 0.8rem;height:0.8rem;line-height:0.7rem;background-color:#323232;border-radius:50%;font-size:0.6rem;color:#fff;border: 0;" onclick="addDate(-1)">-</button>
        </div>
    </div>
    <ul class="content_fenshixian" style="display: none">
        <li class="kdata_c" onclick="showKClick(0);">分时图</li>
        <li id="1" class="kdata_c" onclick="showKClick(1);">1分钟K线</li>
        <li id="5" class="kdata_c" onclick="showKClick(2);">5分钟K线</li>
        <li id="30" class="kdata_c" onclick="showKClick(3);">15分钟K线</li>
        <li id="1h" class="kdata_c" onclick="showKClick(4);">30分钟K线</li>
    </ul>

<#--时间选择-->
    <nav class="k-img">
        <article id="article">
            <span class="trade-chart-type stock active" onclick="showKClick(0,0);">k线</span>
            <span class="trade-chart-type line" onclick="showKClick(1,1)">走势</span>
        </article>
        <section class="trade-chart-period m1" onclick="showKClick(1);">1M <b></b></section>
        <section class="trade-chart-period m5 active" onclick="showKClick(2);">5M<b></b></section>
        <section class="trade-chart-period m15" onclick="showKClick(3);">15M<b></b></section>
        <section class="trade-chart-period m30" onclick="showKClick(4);">30M<b></b></section>
        <!--<section class="trade-chart-period h1" >1H</section>-->
        <!--<section class="trade-chart-period d1" >1D</section>-->
    </nav>


    <div class="radio" >
    <#--<img src="${ctxStatic}/all/image/guangbo.png" class="img left">-->
        <#--<div class="radio_text" onclick="showBroadCast()" id="bc_show"></div>-->
    </div>
    <div class="chicang">
        <ul class="new_jilu">
            <li class="new_jilu_list on" id="newTrade" onclick="change('newTrade')">最新成交</li>
            <li class="new_jilu_list" id="holdTrade" onclick="change('holdTrade')">持仓订单</li>
            <li class="new_jilu_list" id="tradeRecord" onclick="change('tradeRecord')">交易记录</li>
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
                <ul class="newbd_new_list newHold" >
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
                    <li>${it.money}<b></b></li>
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
                <ul class="chicangbd_new_list holdOrder" id="chicangbd_new_list_${it.id}" name="${it_index}" style="cursor: pointer;">
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
                <ul class="chicangbd_new_list tradingRecord" >
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
        <div class="pop_bd pop_bd_text pop_bd_text_kuang  f36" style="width:10rem; height: 10rem;margin-bottom: 0.3rem;">
            <div class="tishi">
                <div class="tishi_text">购买</div>
                <div class="close pas_close" onclick="hideBox('passErrorBox')"><img src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <div class="xianTiao3"></div>
            <div class="pa_yc clearfix">
                <ul class="pa_y clearfix" id="chooseMoney">
                    <li id="m1" onclick="chooseMoney('m1')">5000元</li>
                    <li id="m2" onclick="chooseMoney('m2')">3000元</li>
                    <li id="m3" onclick="chooseMoney('m3')">1000元</li>
                    <li id="m4" onclick="chooseMoney('m4')">500元</li>
                    <li id="m5" onclick="chooseMoney('m5')">200元</li>
                    <li id="m6" class="on" onclick="chooseMoney('m6')">100元</li>
                    <li id="m7" onclick="chooseMoney('m7')">50元</li>
                    <input class="money" placeholder="其他金额" id="inputBuyMoney" onkeyup="inputBuyMoney()">
                <#--<li id="m8" onclick="chooseMoney('m8')">其他金额</li>-->
                </ul>
            </div>
            <div class="xianTiao3"></div>
            <div class="pa_money">
                <div class="goumai">
                    购买：<span id="showMoney">100.00元</span>
                    <p style="display: inline-block">预期收入:<span id="willMoney">900.00元</span></p>
                </div>
                <ul class="heyue">
                    <li class="left">合约:<span id="contractName">白银</span></li>
                    <li class="left">结算周期:<span id="buyTimeSecond">60秒</span></li>
                </ul>
                <ul class="heyue">
                    <li class="left">订单方向:<span class="heyue_red" id="showUpAndDown"></span></li>
                    <li class="left price">当前价格:<span class="dangqian_green" id="showPrice"></span></li>
                </ul>
            </div>
            <div class="xianTiao3"></div>
            <button class="login" type="submit" onclick="_make()">确定</button>
        </div>
    </div>
</div>
<!-- 不在交易时段-->
<div class="popBox" id="stopBox" style="display: none;">
    <div class="popCont" >
        <div class="pop_bd buzaijiaoyi f36" style="height:5.5rem;">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.45rem;line-height: 1rem;">提示</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.1rem;" ><img onclick="hideBox('stopBox')" src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <div class="xianTiao3"></div>
            <div style="width: 8rem; text-align: center">
                <div style=" text-align: center;line-height:0.8rem;padding-top:0.8rem;color:#FF2A40; font-size:0.7rem;">休市</div>
                <p class="no center" style="color:#989ca3;padding-top:0.3rem;font-size:0.4rem;">工作日正常交易</p>
            </div>
            <button class="sure make_surea" style="margin-top: 0.5rem;" onclick="hideBox('stopBox')">确认</button>
        </div>
    </div>
</div>
<div class="popBox" id="WinBox" style="display: none;">
    <div class="popCont" >
        <div class="pop_bd buzaijiaoyi f36" style="overflow: auto">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.5rem;line-height: 1rem;">必胜开始</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.1rem;" ><img onclick="hideBox('WinBox')" src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <div class="p_money">
                <h2 id="overTime"></h2>
                <p>执行价格:<span id="hold_Price">暂无</span></p>
                <p>当前价格:<span id="now_Price" name="">暂无</span></p>
            </div>
            <ul class="p_point">
                <li>订单方向:<span class="p_point_red" id="sub">暂无</span></li>
                <li>预测结果:<span class="point_red" id="trend">暂无</span></li>
            </ul>
            <button class="make_sureb" style="margin-top: 0.5rem; " onclick="hideBox('WinBox')">继续下单</button>
        </div>
    </div>
</div>
<!--平仓-->
<div class="popBox" id="WinBoxs" style="display: none;">
    <div class="popCont" >
        <div class="pop_bd buzaijiaoyi f36" style="overflow: auto">
            <div class="tishi">
                <div class="tishi_text center" style="font-size: 0.5rem;line-height: 1rem;">必胜开始</div>
                <div class="close" style="width:0.6rem; right:0.1rem; top:0.1rem;" ><img onclick="hideBox('WinBoxs')" src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <div class="p_money">
                <h2 id="difMoney"></h2>
                <p>执行价格:<span id="buyPoints">暂无</span></p>
                <p>到期价格:<span id="sellPoints" name="">暂无</span></p>
            </div>
            <ul class="p_point">
                <li>订单方向:<span class="p_point_red" id="subs">暂无</span></li>
                <li>预测结果:<span class="point_red" id="trends">暂无</span></li>
            </ul>
            <button class="make_sureb" style="margin-top: 0.5rem; " onclick="hideBox('WinBoxs')">继续下单</button>
        </div>
    </div>
</div>
<div class="xianTiao2"></div>
<#--<#include "/wap/footer.ftl"/>-->
<nav class="bar bar-tab index" id="bar-tab">
    <a href="javascript:<#if footer == "trade">void(0);</#if>" class="tab-item jy">
        <span class="icon icontu icon-jy<#if footer == "trade">-active</#if>"></span>
        <span class="tab-label jy<#if footer == "trade">-active</#if>">交易</span>
    </a>
    <a href="javascript:<#if footer == "hold">void(0);</#if>" class="tab-item cc">
        <span class="icon icontu icon-cc<#if footer == "hold">-active</#if>"></span>
        <span class="tab-label cc<#if footer == "hold">-active</#if>">邀请</span>
    </a>
    <a href="javascript:<#if footer == "corps">void(0);</#if>" class="tab-item jt">
        <span class="icon icontu icon-jt<#if footer == "corps">-active</#if>"></span>
        <span class="tab-label jt<#if footer == "corps">-active</#if>">军团</span>
    </a>
    <a href="javascript:<#if footer == "user">void(0);</#if>" id="me label" class="tab-item">
        <span class="icon icontu icon-me<#if footer == "user">-active</#if>"></span>
        <span class="tab-label me<#if footer == "user">-active</#if>">个人中心</span>
    </a>
</nav>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<!--->
<script type="text/javascript" src="${ctxStatic}/all/js/slide.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/iSlider/js/iSlider.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/iSlider/js/iSlider.animate.flow1.js"></script>
<script type="text/javascript">
    var imagedata = [{
        content : '<img src="${ctxStatic}/all/image/img/1.png" width="100%"><div class="iSlider-mask" style="display:none;"></div>'
    },{
        content : '<img src="${ctxStatic}/all/image/img/2.png" width="100%"><div class="iSlider-mask iSlider-mask-right"></div>'
    },{
        content : '<img src="${ctxStatic}/all/image/img/3.png" width="100%"><div class="iSlider-mask iSlider-mask-left"></div>'
    }];
    var islider = new iSlider(document.getElementById('iSlider-wrapper'), imagedata, {
        isLooping: true,
        isOverspread: false,
        initIndex: 0,
        isAutoplay: false,
        animateTime: 400,
        animateType: 'flow1',
        fixPage:false,
        isTouchable: true,
        height: 400,
        onSlideChanged: function (index) {
            imageId = index;
            $('#iSlider-wrapper ul li').each(function (index) {
                var the = $(this).children('.iSlider-mask');
                the.removeClass('iSlider-mask-left').removeClass('iSlider-mask-right');
                if($(this).hasClass('islider-prev') || $(this).hasClass('islider-next')){
                    if($(this).hasClass('islider-prev')){
                        the.addClass('iSlider-mask-left');
                    }else{
                        the.addClass('iSlider-mask-right');
                    }
                    the.show();
                }else{
                    the.hide();
                }
            })
        },
        onInitialized: function () {
            $('#iSlider-wrapper ul li').each(function (index) {
                $(this).click(function () {
                    if($(this).hasClass('islider-prev')){
                        islider.slidePrev();
                    }else if($(this).hasClass('islider-next')){
                        islider.slideNext();
                    }
                });
            })
        }
    });

    TouchSlide({
        slideCell:"#focus",
        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell:".bd ul",
        effect:"left",
        autoPlay:true,//自动播放
        autoPage:true
    });
    TouchSlide({
        slideCell:"#iTaba",
        titCell:".ita_t li",
        mainCell:".bd ul",
        effect:"left",
        autoPlay:false,
        autoPage:false
    });

    function showDiv(sId){
        var getId=$(sId);
        getId.show().stop(true,true).animate({opacity:'1'},200);
        getId.addClass('active');
        var firstDiv=getId.find('div:first');
        firstDiv.click(function(e){
            e.stopPropagation();
        });
        getId.click(function(){
            getId.removeClass('active');
            $(this).hide().stop(true,true).animate({opacity:'0'},200);
        });
        $('.closed').click(function(){
            getId.removeClass('active');
            getId.hide().stop(true,true).animate({opacity:'0'},200);

        });
    }
    $(function(){
        $('.popCont').each(function(){
            var $pt=$(this).find('.pop_tit');
            var $pbd=$(this).find('.pop_bd');
            $pbd.find('.pho_bd_item').eq(0).show();
            $pt.find('a').eq(0).addClass('on');
            $pt.find('a').click(function(){
                var index=$(this).index();
                $(this).addClass('on').siblings().removeClass('on');
                $pbd.find('.pho_bd_item').hide();
                $pbd.find('.pho_bd_item').eq(index).fadeIn();
            })
        })
        $('.pa_y li,.ykItem span').click(function() {
            $(this).addClass('on').siblings().removeClass('on');
        });

        //加的效果
        $(".add").click(function(){
            var n=$(this).prev().val();
            var num=parseInt(n)+1;
            if(num==0){ return;}
            $(this).prev().val(num);
        });
        //减的效果
        $(".jian").click(function(){
            var n=$(this).next().val();
            var num=parseInt(n)-1;
            if(num==0){ return}
            $(this).next().val(num);
        });
    })
</script>
<script type="text/javascript" src="${ctxStatic}/js/formatString.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/picker.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/drager.js"></script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/highstock.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/js/themes/dark-unica.js' charset='utf-8'></script>
<script type="text/javascript">
    $('#container').height(document.body.clientHeight
            - $('#bar-tab').outerHeight() - $('.ita_t').outerHeight() - $('.bd').outerHeight()
            - $('.b_btn').outerHeight() + $('#container').outerHeight() - $('#iSlider-wrapper').outerHeight() / 2);
    var current_code_p = ${_cc};
    var _contracts = ${_contracts};
    var _broadcastsJson = ${broadcastsJson};
    var _codes = ${_codes};
    var _isMarketOpen = ${_isMarketOpen};
    /*loginStatus*/
    <#--var _user_money = ${user.money?c};-->
    <#--var _user_coupon_money = ${user.couponMoney?c};-->
    var _now = ${now?c};
    var _hold = <#if _list??>${_list}<#else>[]</#if>
    /*时时持仓订单*/
    var overTime = [];
    var it = null;
    for(var i = 0; i < _hold.length; i++){
        it = _hold[i];
        overTime.push(Math.round((Number(it.sellTime) - _now)/1000));
    }
    function show_time(){
        var totalInteval = null;
        for(var i = 0; i < overTime.length; i++){

            it = _hold[i];
            totalInteval = Number(it.offTime.replace('M', ''))*60;
            if(overTime[i] > 0 ){
                overTime[i]--;
                $("#overTime").text(overTime[num]);
            }else{

                console.log("id:" + _hold[num].id);
                /*判断倒计时是否显示*/
                if($("#WinBox").css('display') != 'none'){
                    //$("#trends").text($("#trend").text());
                    setTimeout(getHold(_hold[num].id),2000);
                }

                _hold.splice(i, 1);
                overTime.splice(i, 1);
                i--;
                $("#chicangbd_new_list_"+it.id).remove();
                $("#WinBox").css('display','none');
                obj = null;
                if(num > 0){
                    num--;
                }
                setTimeout('moneyNow(_hold.length)',1000);
            }
        }
        // 设置定时器
        setTimeout(function () {
            show_time();
        },1000);
    }

    function getHold(id){
        $.ajax({
            url: ctx + "/trade/holdId?id="+id,
            type: 'get',
            success: function (data) {
                var trade = data.trade;
                if(trade != ""){
                    if(0 > Number(trade.difMoney)){
                        $("#difMoney").text("亏 "+trade.difMoney);
                    }else if(0 < Number(trade.difMoney)){
                        $("#difMoney").text("盈 "+trade.difMoney);
                    }else{
                        $("#difMoney").text("平 "+trade.difMoney);
                    }
                    $("#buyPoints").text(trade.buyPoint);
                    $("#sellPoints").text(trade.sellPoint);
                    if(0 == trade.buyUpDown){
                        $("#subs").removeClass('p_point_green').addClass('p_point_red').text('买涨');
                    }else {
                        $("#subs").removeClass('p_point_red').addClass('p_point_green').text('买跌');
                    }
                    if(((trade.sellPoint < trade.buyPoint) && (trade.buyUpDown == "0")) || ((trade.sellPoint > trade.buyPoint) && (trade.buyUpDown == "1"))){
                        $("#trends").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                    }else if(((trade.sellPoint > trade.buyPoint) && (trade.buyUpDown == "0")) || ((trade.sellPoint <trade.buyPoint) && (trade.buyUpDown == "1"))){
                        $("#trends").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                    }else{
                        $("#trends").removeClass('point_red').removeClass('point_green').addClass('point_grey').text('平');
                    }
                    $("#WinBoxs").css("display",'block');
                }else{
                    getHold(id);
                }
            }
        })
    }

    // 根据是否能获取最新数据来二次判断 开盘情况
    <#list markets as it>
    if(_contracts[${it_index}].marketOpen){
        _contracts[${it_index}].marketOpen = <#if it??>true<#else>false</#if>;
    }
    </#list>

    // 二维码
    function weixinCode() {
        $("<div id='bg' onclick='hide(this)' style='position:relative;width:100%;height:100%;text-align:center;position:fixed;top:0;left:0;background-color:rgba(0,0,0,0.7);z-index:999;overflow: auto;'><div style='padding-top:0.8rem;margin:0 auto;position: relative'><img src='${ctxWap}/user/shareImg' style='position:relative; top: 50%; margin-top:4.5rem; width:9rem;'></div>"
                +'<div style="width: 100%; height: 3rem; text-align: center; padding-top: .3rem; font-size: .35rem; line-height: .7rem; color: #cccccc;"></div>'
                +"</div>").appendTo($("body"));
    }
    function hide(ts){
        $(ts).hide();
    }
    function hideBox() {
        obj = null;
        $('.popBox:visible').hide();
        $("#inputBuyMoney").val('');
    }
    function showBox(id) {
        $("#inputBuyMoney").val('');
        hideBox();
        $(id).show();
    }
    var _selectOffTime = 0;
    /*买涨买跌下单弹出框*/
    var imageId;
    function sureBox(d) {
        reg();

        /*if(!_contracts[current_code_p].marketOpen){
            showBox('#stopBox');
            return;
        }
        _direction = d;
        if(imageId == '2'){
            _selectOffTime = 2;
            $('#buyTimeSecond').text('300秒');
        }else if(imageId == '1'){
            _selectOffTime = 1;
            $('#buyTimeSecond').text('180秒');
        }else if(imageId == '0'){
            _selectOffTime = 0;
            $('#buyTimeSecond').text('60秒');
        }
        //显示金额和预期收益
        $('#chooseMoney li').removeClass('on');
        changeMoney('m6');
        showBox('#sureBox');
        $('#contractName').text(_contracts[current_code_p].name);
        $('#showPrice').text($('#currentMoney_'+_contracts[current_code_p].code).text());
        $('#showUpAndDown').text(d == '0' ? '买涨' : '买跌');*/
    }

    function chooseMoney(id){
        $("#"+id).on("click",changeMoney(id));
    }
    function changeMoney(id) {
        $("#showMoney").text($("#"+id).text());
        $("#"+id).addClass("on");
        _selectMoney = parseInt($("#"+id).text());
        //预期收益
        var percentProfit = _contracts[current_code_p].percentProfits.split(',')[_selectOffTime];
        var num = Number(percentProfit) / 100;
        $('#willMoney').text( (_selectMoney + _selectMoney*num)+'元');
    }
    function inputBuyMoney() {
        $('#chooseMoney li').removeClass('on');
        var money = $("#inputBuyMoney").val();
        var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/
        if(!exp.test(money)) {
            return;
        }
        $("#showMoney").text(money);
        _selectMoney = parseInt(money);
        //预期收益
        var percentProfit = _contracts[current_code_p].percentProfits.split(',')[_selectOffTime];
        var num = Number(percentProfit) / 100;
        $('#willMoney').text( (_selectMoney + _selectMoney*num)+'元');
    }

    // 下单
    var _selectMoneyStr = $('#showMoney').text();
    var _selectMoney = parseInt(_selectMoneyStr);
    function _make() {
        var _offTime_arr_temp = _contracts[current_code_p].offTimes.split(",");
        var _con = _contracts[current_code_p];
        var obj = {
            code: _con.code,
            type: 0,
            money: _selectMoney,
            buyUpDown: _direction,
            offTime: _offTime_arr_temp[_selectOffTime],
            //offPoint:_offPoint_arr_temp[_selectOffPoint],
            //couponId: _selectCoupon == 0 ? null : _coupons[_selectCoupon].id
        };
        /*        if(_selectCoupon != 0){
                    if(Number(_coupons[_selectCoupon].minMoney) > Number(obj.money)){
                        layer.msg('此优惠券不满足使用规则，请重新选择！');
                        return;
                    }
                }*/

        $.ajax({
            url: ctx + '/trade/make',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(obj),
            success: function(data){
                //去掉使用的代金券
//                    if(_selectCoupon != 0){
//                        _coupons.splice(_selectCoupon, 1);
//                        _coupons_arr.splice(_selectCoupon, 1);
//                    }
                showBox('#successBox');
                moneyNow();
                hold();
            },
            useDefaultError: false,
            error: function(p1, p2, p3, result){
                if(result.code == '99'){
                    showBox('#stopBox');
                }else{
                    layer.msg(result.error);
                }
            }
        });
    }


    function moneyNow(tradeId){
        if("undefined" == typeof(tradeId)){
            tradeId = 0;
        }
//        $.ajax({
//            url: ctx + '/users/getUserMoney?tradeId='+tradeId,
//            type: 'get',
//            success: function(data) {
//                if(data.money == "-1"){
//                    setTimeout(moneyNow(tradeId),1000);
//                }else{
//                    $(".a_money").text(fmoney(data.money,2));
//                }
//            }
//        });
    }

    function hold(){
        $.ajax({
            url: ctxWap + '/trade/holds?flag=2',
            type: 'get',
            success: function(data) {
                $(".holdOrder").remove();
                /*计算下单时间*/
                _hold = [];
                _hold = data.list;
                overTime.splice(0,overTime.length);
                /*获取平仓时间*/
                var timeList = data.timeList;
                for(var i=0;i<timeList.length;i++){
                    overTime.push(timeList[i]);
                }
                /*显示必胜开始*/
                $("#overTime").text(' ');
                $("#WinBox").css('display','block');
                if (0 == _direction){
                    $("#sub").removeClass('p_point_green').addClass('p_point_red');
                }else{
                    $("#sub").removeClass('p_point_red').addClass('p_point_green');
                }
                $("#sub").text(_direction == 0 ? '买涨':'买跌');

                console.log(overTime[0]);

                _hold = data.list;
                var arr = [];
                var win = [];
                var list = data.list;
                for(var i = 0; i < list.length; i++){
                    $("#hold_Price").text(list[0].buyPoint);
                    arr.push('<ul class="chicangbd_new_list holdOrder" id="chicangbd_new_list_'+ list[i].id +'" name="'+ i +'" style="cursor: pointer;">');
                    arr.push('<li name='+ list[i].code+'>'+ list[i].contractName +'</li>');
                    if(0 == list[i].buyUpDown) {
                        arr.push('<li class="heyu_de_red" name="0">买涨</li>');
                    }else{
                        arr.push('<li class="heyu_de_green" name="1">买跌</li>')
                    }
                    arr.push('<li>'+ list[i].buyTime.split(' ')[1] +'</li>');
                    arr.push('<li>'+ list[i].buyPoint +'</li>');
                    arr.push('<li class="hold_current_price_' + list[i].code +'">4568</li>');
                    arr.push('<li>'+ list[i].money +'</li>');
                    arr.push('</ul>');
                }
                $("#holdOrder").after(arr.join(''));
            }
        });
    }
    var num = 0;
    var obj = null;
    $(document).ready(function(){

        $(document).on('click','.holdOrder',function(){
            obj = $(this);
            num = Number($(this).attr('name'));

            //获取持仓列表中的-方向--开仓价格
            var ioc = obj.children().eq(1).text();
            $("#sub").text(ioc);
            if('买跌' == ioc){
                $("#sub").removeClass("p_point_red").addClass("p_point_green");
            }else{
                $("#sub").removeClass("p_point_green").addClass("p_point_red");
            }
            var buyMoney = obj.children().eq(3).text();
            var openPrice = parseFloat(buyMoney).toFixed(2);
            $("#hold_Price").text(buyMoney);
            $("#overTime").text(overTime[num]);
            $("#WinBox").css('display','block');
        });

    });

    function reg(){
        //未登录状态操作
        layer.open({
            content: '注册后才能进行交易',
            btn: ['注册', '我知道了'],
            yes: function(){
                window.location.replace("${ctxWap}/reg");
                layer.close(index);
            }
        });
        return;
    }

    function change(param) {
        /*最新成交*/
        if(param == 'newTrade'){
            $('#newTrade').addClass('on');
            $('#holdTrade').removeClass('on');
            $('#tradeRecord').removeClass('on');
            $('#newTradeDetail').show();
            $('#holdTradeDetail').hide();
            $('#tradeRecordDetail').hide();
            $.ajax({
                url: ctxWap + '/trade/holds',
                type:'get',
                success: function(data){
                    console.log(data.list);
                    $(".newHold").remove();
                    var arr = [];
                    var list = data.list;

                    for(var i = 0; i < list.length; i++){
                        arr.push('<ul class="newbd_new_list newHold">');
                        arr.push('<li>'+ list[i].buyTime.split(' ')[1] +'</li>');
                        arr.push('<li><ul class="heyu_de">')
                        arr.push('<li>'+ list[i].contractName +'</li>');
                        if(0 == list[i].buyUpDown) {
                            arr.push('<li class="heyu_de_red">买涨</li>');
                        }else{
                            arr.push('<li class="heyu_de_green">买跌</li>')
                        }
                        arr.push('</ul></li>')
                        arr.push('<li>'+ list[i].money +'</li>')
                        arr.push('</ul>');
                    }
                    $(".newbd_new").after(arr.join(''));
                }
            });
        }else if(param == 'holdTrade'){/*持仓订单*/
            $('#holdTrade').addClass('on');
            $('#newTrade').removeClass('on');
            $('#tradeRecord').removeClass('on');
            $('#holdTradeDetail').show();
            $('#newTradeDetail').hide();
            $('#tradeRecordDetail').hide();
        }else{/*交易记录*/
            $('#tradeRecord').addClass('on');
            $('#newTrade').removeClass('on');
            $('#holdTrade').removeClass('on');
            $('#tradeRecordDetail').show();
            $('#newTradeDetail').hide();
            $('#holdTradeDetail').hide();
            $.ajax({
                url: ctxWap + '/trade/holds?flag=1',
                type: 'get',
                success: function(data){
                    $(".tradingRecord").remove();
                    var arr = [];
                    var list = data.list;
                    for(var i = 0; i < list.length; i++){
                        arr.push('<ul class="chicangbd_new_list tradingRecord">');
                        arr.push('<li>'+ list[i].contractName +'</li>');
                        if(0 == list[i].buyUpDown){
                            arr.push('<li class="heyu_de_red">买涨</li>');
                        }else{
                            arr.push('<li class="heyu_de_green">买跌</li>');
                        }
                        arr.push('<li>'+ list[i].sellTime.split(' ')[1] +'</li>');
                        arr.push('<li>'+ list[i].money +'</li>');
                        arr.push('<li>'+ list[i].difMoney +'</li>');
                        if(((list[i].sellPoint - list[i].buyPoint) > 0 && (list[i].buyUpDown == 0)) || ((list[i].sellPoint - list[i].buyPoint) < 0 && list[i].buyUpDown == 1)){
                            arr.push('<li class="chicangbd_red">盈</li>');
                        }else if(((list[i].sellPoint - list[i].buyPoint) < 0 && (list[i].buyUpDown == 0)) || ((list[i].sellPoint - list[i].buyPoint) > 0 && list[i].buyUpDown == 1)){
                            arr.push('<li class="chicangbd_green">亏</li>');
                        }else{
                            arr.push('<li class="chicangbd_grey">平</li>');
                        }
                        arr.push('</ul>');
                    }
                    $("#tradingRecord").after(arr.join(''));
                }
            });
        }
    }
    function isNull(arg1)
    {
        return !arg1 && arg1!==0 && typeof arg1!=="boolean"?true:false;
    }

    $(function(){
        <#--$(".a_money").text(fmoney('${user.money}',2));-->
        show_time();
        // 定时刷新
        showKClick(0);
        chooseMoney('m6');
        if(_isMarketOpen){
            setTimeout(function () {
                an()
                marketNew();
                getCurrentTime();
            }, 1000);
        }

        $("#inputBuyMoney").on("click", function(){
            $('#chooseMoney li').removeClass('on');
        });
    });

    var serverCurrentTime = '';
    //定时获取当前时间
    function getCurrentTime() {
        if(serverCurrentTime == ''){
            var timeStr = '${current_date_time}'.replace(/-/g,"/");
            console.log(timeStr);
            serverCurrentTime = new Date(timeStr);
        }
        serverCurrentTime.setTime(serverCurrentTime.getTime()+1000);
        setTimeout(function () {
            getCurrentTime();
        }, 1000);
    }

    var _direction = 0;
    function show1(index) {
        current_code_p = index;
        showKClick(0);
    }

    // K线图类型
    var _marketKData_interval_position = 0;
    var _marketKData_interval_val = ["1","1","5","15","30","60","1440"];
    var _marketKData_interval_setTimeout_fun = null;
    function marketKData(){
        var now = new Date();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();

        var isR = true;
        // 每10秒刷新一次
        if(_marketKData_interval_position == 0 && second == 8){

        }else if(_marketKData_interval_position == 1 && second == 8){

        }else if(_marketKData_interval_position == 2 && minute%5 == 0 && second == 10){

        }else if(_marketKData_interval_position == 3 && minute%15 == 0 && second == 12){

        }else if(_marketKData_interval_position == 4 && minute%30 == 0 && second == 14){

        }else if(_marketKData_interval_position == 5 && minute == 0 && second == 16){

        }else{
            isR = false;
        }
        if(isR){
            showK(_marketKData_interval_position);
        }
        _marketKData_interval_setTimeout_fun = setTimeout(function () {
            marketKData();
        }, 1000);
    }
    //判断k线或者趋势
    var flag = true;
    var ta;

    // 是否重新绘制的标记
    var _p_pre = null;
    // 是否重新绘制标记线
    var _plotLine_refresh = false;
    function showKClick(p, tag) {
        if(_p_pre != (p == 0 ? p : 1)){
            // 重新绘制K线图
            if(_chart1 != null){
                _chart1.destroy();
                _chart1 = null;
                _plotLine_refresh = true;
            }
            _p_pre = p == 0 ? p : 1;
        }

        $('.kdata_c').removeClass('on');
        $('.kdata_c').eq(p).addClass('on');

        /*判断k线或者趋势图*/
        if(p === 0 || tag === 0){
            ta = tag;
            flag = true;
        }else if(tag == 1){
            ta = tag;
            flag = false;
        }

        clearTimeout(_marketKData_interval_setTimeout_fun);
        showK(p);
        /*显示拓展时间*/
        if(p == 4){
            $("#addDate").css('display','block');
        }else{
            $("#addDate").css('display','none');
        }
        if(_isMarketOpen && _contracts[current_code_p].marketOpen){
            _marketKData_interval_setTimeout_fun = setTimeout(function () {
                marketKData();
            }, 1000);
        }
    }
    var _chart1 = null;
    var _data = null;
    var _is_loading = false;
    function showK(p) {
        _marketKData_interval_position = p;
        var interval = _marketKData_interval_val[p];
        _is_loading = true;
        _data = [];
        $.ajax({
            url: ctx + '/market/kdata',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "code": _codes[current_code_p],
                "interval":interval
            }),
            success: function(data){
                var d = null;
                for(var i = 0; i < data.length ; i++){
                    d = data[i];
                    _data.push([d.timestamp, Number(d.open), Number(d.high), Number(d.low), Number(d.close)]);
                }
                if(!!window.console && _data.length > 1){
                    console.log(new Date(_data[_data.length-1][0]).toLocaleString() + '  ' + new Date(_data[_data.length-2][0]).toLocaleString());
                }
                _is_loading = false;
                if(flag == true){
                    if(_chart1 == null){
                        _chart1 = new Highcharts.StockChart({
                            chart: {
                                panning: true,
                                pinchType: 'x',
                                zoomType: 'x',
                                resetZoomButton: {
                                    position: {
                                        x: 0,
                                        y: -60
                                    }
                                },
                                backgroundColor: '#101419',
                                renderTo: $('#container')[0]
                            },
                            credits:{
                                enabled: false
                            },
                            rangeSelector:{
                                enabled: false
                            },
                            exporting:{
                                enabled: false
                            },
                            navigator: {
                                enabled: false
                            },
                            scrollbar: {
                                enabled: false
                            },
                            rangeSelector : {
                                enabled: false
                            },
                            xAxis: {
                                gridLineWidth: 0,
                                gridLineDashStyle: 'ShortDash',
                                tickInterval: _marketKData_interval_val[p]*60*1000
                            },
                            yAxis: {
                                opposite: false,
                                gridLineWidth: 1,
                                gridLineColor: "#444444",
                                labels: {
                                    align: "right",
                                    x: 15
                                }
                            },
                            tooltip: {
                                borderColor: '#666666',
                                backgroundColor: 'rgba(247,247,247,0.6)'
                            },
                            series : [{
                                id: '1',
                                name : '行情',
                                type: "area",
                                data : _data,
                                color: '#666666',
                                lineColor: '#00bcbc',
                                lineWidth: 1,
                                threshold: null,
                                animation: false,
                                tooltip: {
                                    valueDecimals: _contracts[current_code_p].precision
                                }
                            }]
                        });
                    }else{
                        _chart1.get("1").setData(_data, true, false);
                    }
                }else {
                    // 分时图转1分钟蜡烛图，数据点太多，截取40个点
                    if(p == 1){
                        _data.splice(0, _data.length - 30);
                    }
                    if(_chart1 == null){
                        _chart1 = new Highcharts.StockChart({
                            chart: {
                                panning: true,
                                pinchType: 'x',
                                zoomType: 'x',
                                resetZoomButton: {
                                    position: {
                                        x: 0,
                                        y: -60
                                    }
                                },
                                backgroundColor: '#101419',
                                renderTo: $('#container')[0]
                            },
                            credits:{
                                enabled: false
                            },
                            rangeSelector:{
                                enabled: false
                            },
                            exporting:{
                                enabled: false
                            },
                            navigator: {
                                enabled: false
                            },
                            scrollbar: {
                                enabled: false
                            },
                            rangeSelector : {
                                enabled: false
                            },
                            xAxis: {
                                gridLineWidth: 1,
                                gridLineDashStyle: 'ShortDash',
                                tickInterval: _marketKData_interval_val[p]*60*1000
                            },
                            yAxis: {
                                opposite: false,
                                gridLineWidth: 1,
                                gridLineColor: "#444444",
                                labels: {
                                    align: "right",
                                    x: 15
                                },
                            },
                            tooltip: {
                                borderColor: '#666666',
                                backgroundColor: 'rgba(247,247,247,0.6)'
                            },
                            series: [{
                                id: "1",
                                name : '行情',
                                type: 'candlestick',
                                upColor: '#d52e32',
                                upLineColor: '#d52e32',
                                color: '#1aa93a',
                                lineColor: '#1aa93a',
                                data: _data,
                                animation: false,
                                tooltip: {
                                    valueDecimals: _contracts[current_code_p].precision
                                }
                            }]
                        });
                    }else{
                        _chart1.get("1").setData(_data, true, false);
                    }
                }
                if(_time == null){
                    _time = _data[_data.length-1][0];
                }
                var price = $('#show1_'+_codes[current_code_p]).text();
                if(/^\d+(\.\d+)?$/.test(price)){
                    showNewLine(Number(price), _time);
                }
            }
        });
    }
    /*["1","1","5","15","30","60","1440","120","180","240"];*/
    /*点击加号*/
    var i = 4;
    function addDate(adds){
        /*if(i >= 4 && i < 6){
            i = i + Number(num);
        }else{
            i = 4;
        }*/
        i = i+Number(adds);
        if(i > 6 || i < 4){
            if(Number(adds) == 1){
                i = 6;
            }else{
                i = 4;
            }
            return;
        }
        showK(i);
    }

    /*保留上一秒的价格*/
    var oldPrice;
    var _time = null;
    // 固定频率动态刷新行情
    function marketNew(){
        if(!!window.console){

        }
        var __codes = [];
        for (var i = 0; i < _contracts.length; i++) {
            if (!_contracts[i].marketOpen) {
                continue;
            }
            __codes.push(_contracts[i].code);
        }
        $.ajax({
            url: ctx + '/market/new',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "codes": __codes.join(',')
            }),
            success: function (data) {
                if (!data || data.length == 0) {
                    return;
                }
                if (!!window.console) {
                    if (!data[0].timestamp) {
                        console.log(JSON.stringify(data));currentMoney
                    }
                }
                var isZhang = false;
                var it = null, code = null, price = null, open = null, heigh = null, low = null, close = null, off1 = null, off2 = null;
                var _inde = null;
                for (var i = 0; i < data.length; i++) {
                    it = data[i];
                    if (it == '') {
                        continue;
                    }
                    code = it.code;

                    price = Number(it.price);
                    if (price <= 0) {
                        continue;
                    }
                    open = Number(it.open);
                    heigh = Number(it.high);
                    low = Number(it.low);
                    close = Number(it.close);

                    /*isZhang = price > open;*/
                    isZhang = price > oldPrice;
                    oldPrice = Number(it.price);
                    console.log(isZhang+ "---");
                    off1 = (isZhang ? '+' : '') + (price - open);
                    off2 = (isZhang ? '+' : '') + decimalAfter2((price - open) * 100 / open) + '%';

                    for (var j = 0; j < _contracts.length; j++) {
                        if (_contracts[j].code == code) {
                            _inde = j;
                        }
                    }

                    //如果价格变动才显示
                    var currentMoney = $('#currentMoney_' + it.code).text();
                    if(price!= currentMoney){
                        $('#currentTime_'+it.code).text('时间：' + serverCurrentTime.toTimeString().substr(0,8));
                    }
                    // 改变页面显示
                    if (isZhang) {
                        $('#currentMoney_' + it.code).removeClass('my-bar_green').addClass('my-bar_red');
                        $('#currentMoney_' + it.code).html(formatNumber(price, _contracts[_inde].precision) + ' <i class="icon i_red"></i>');
                    } else {
                        $('#currentMoney_' + it.code).removeClass('my-bar_red').addClass('my-bar_green');
                        $('#currentMoney_' + it.code).html(formatNumber(price, _contracts[_inde].precision) + ' <i class="icon i_green"></i>');
                    }
                    //弹出框的当前价
                    if (it.code == _contracts[current_code_p].code && null == obj) {
                        $('#showPrice').text($('#currentMoney_' + _contracts[current_code_p].code).text());
                        $('#now_Price').text($('#currentMoney_' + _contracts[current_code_p].code).text());
                        if(0 != _hold.length && [] != _hold.length){
                            if(((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))){
                                $("#trend").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                            }else if(((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))){
                                $("#trend").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                            }else{
                                $("#trend").removeClass('point_red').removeClass('point_green').addClass('point_grey').text('平');
                            }
                        }
                    }

                    //持仓的当前价
                    //如果选择了持仓订单走这里
                    if(null != obj){
                        if(it.code == obj.children().eq(0).attr('name')){
                            $('#now_Price').text(price.toFixed(2));
                            if(((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))){
                                console.log('xiaoyu');
                                $("#trend").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                            }else if(((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))){
                                console.log('dayu');
                                $("#trend").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                            }else{
                                $("#trend").removeClass('point_red').removeClass('point_green').addClass('point_grey').text('平');
                            }
                        }
                    }
                    $('.hold_current_price_' + it.code).text(price);
                    $('#high_' + it.code).html('最高：' + formatNumber(heigh, _contracts[_inde].precision) + '<i></i>');
                    $('#low_' + it.code).html('最低：' + formatNumber(low, _contracts[_inde].precision) + '<i></i>');
                    if (it.code == _contracts[current_code_p].code) {
                        if (!_is_loading) {
                            // 矫正时间
                            _time = it.timestamp;
                            showNewLine(price, _time);
                        }
                    }
                }
            }
        });
        setTimeout(function () {
            marketNew();
        }, 350);
    }

    // 添加标记线
    function addPlotLine(price){
        if(price == null){
            return;
        }
        _chart1.get('1').yAxis.removePlotLine('2');
        _chart1.get('1').yAxis.addPlotLine({
            color: '#d52e32',
            dashStyle: 'LongDash',
            id: '2',
            width: 1,
            value: price,
            label: {
                text: '<span style="color: red;font-family:\'微软雅黑\';">'+ formatNumber(price, _contracts[current_code_p].precision) + '('+_new_line_ms/1000.0+'s)'+'</span>',
                align: 'left',
                useHTML: true,
                y: -5,
                x: 0
            },
            zIndex: 1000
        });
    }
    // 计算价格变动
    var _new_line_time_pre = null;
    var _new_line_price_pre = null;
    var _new_line_ms = null;
    function showNewLine(price, time){
        if(_chart1 != null){
            if(_new_line_price_pre != price){

                _new_line_ms = _new_line_time_pre == null ? 0 : new Date().getTime() - _new_line_time_pre.getTime();
                _new_line_price_pre = price;
                _new_line_time_pre = new Date();

                addPlotLine(price);
            }
            if(_plotLine_refresh){
                addPlotLine(price);
                _plotLine_refresh = false;
            }
            refreshId1(price, time);
        }
    }

    function refreshId1(price, time){
        time = formatTime(time);
        if(_chart1 != null) {
            var _it = _chart1.get("1");
            if (_it != null) {
                var _p = _data.length == 0 ? null : _data[_data.length-1];
                var isRemove = false;
                if(_p == null || _p[0] < time){
                    _p = [time, price, price, price, price];
                }else{
                    isRemove = true;
                }
                if(_marketKData_interval_position == 0){
                    if(isRemove && _p[1] == price){
                        return;
                    }
                    _p[1] = price;
                }else{
                    if(isRemove && _p[4] == price){
                        return;
                    }
                    _p[2] = Math.max(price, _p[2]);
                    _p[3] = Math.min(price, _p[3]);
                    _p[4] = price;
                }

                if(isRemove){
                    _it.removePoint(_data.length-1, false);
                }
                _it.addPoint(_p);
            }
        }
    }
    /**
     * 格式化时间戳，以适应K线图数据展示（主要是蜡烛图）
     * @param time
     * @returns {number}
     */
    function formatTime(time){
        var t = new Date(time);
//            if(_marketKData_interval_position == 0){
//                return t.getTime();
//            }
        var hour = t.getHours();
        var minute = t.getMinutes();
        var second = t.getSeconds();
        var date = t.getDate();
        var step = Number(_marketKData_interval_val[_marketKData_interval_position]);
        t.setMilliseconds(0);
        t.setSeconds(0);

        var off = 0;

        if(step < 60){
            if(step != 1){
                off = (minute - minute%step) + (minute%step != 0 || second != 0 ? step : 0);
                t.setMinutes(off);
            }
        }else if(step >= 60 && step < 1440){
            step = step/60;
            t.setMinutes(0);
            off = (hour - hour%step) + (hour%step != 0 || second != 0 ? step : 0);
            if(step != 1){
                t.setHours(off);
            }
        }else if(step == 1440){
            step = step/1440;
            t.setMinutes(0);
            t.setHours(0);
            off = (date - date/step) + (date%step != 0 || second != 0 ? step : 0);
            if(step != 1){
                t.setDate(off);
            }
        }
        if(!!window.console){
//                console.log(new Date(time).toLocaleString() + ' *********** ' + step + ' ************ ' + off + ' *********** ' + t.toLocaleString());
        }
        return t.getTime();
    }
    // Highcharts全局配置
    Highcharts.setOptions({
        global: {
            useUTC: true,
            timezoneOffset: -8*60
        },
        lang: {
            contextButtonTitle: "Chart context menu",
            decimalPoint: ".",
            downloadJPEG: "",
            downloadPDF: "",
            downloadPNG: "",
            downloadSVG: "",
            invalidDate: "",
            loading: "Loading...",
            months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            numericSymbols: [ "k" , "M" , "G" , "T" , "P" , "E"],
            printChart: "Print chart",
            rangeSelectorFrom: "From",
            rangeSelectorTo: "To",
            rangeSelectorZoom: "Zoom",
            resetZoom: "恢复",
            resetZoomTitle: "Reset zoom level 1:1",
            shortMonths:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            shortWeekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
            thousandsSep: "",
            weekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"]
        }
    });

    // 广播内容
    var _broadcast_index = 0;
    var _broadcast_index_cur = 0;
    function an() {
        $('#bc_show').fadeOut('slow', function () {
            $('#bc_show').text(_broadcastsJson[_broadcast_index].title);
            $('#bc_show').fadeIn('slow');
            _broadcast_index_cur = _broadcast_index;
            _broadcast_index++;
            if(_broadcast_index >= _broadcastsJson.length){
                _broadcast_index = 0;
                $.ajax({
                    url: ctx + '/index/broadcastList',
                    type: 'GET',
                    data:{},
                    success: function(data){
                        _broadcastsJson = data;
                    }
                });
            }
        });
        setTimeout(function () {
            an();
        }, 7000);
    }
    function showBroadCast() {
        if(_broadcast_index_cur >=0 && _broadcast_index_cur < _broadcastsJson.length){
            window.location.href = ctxWap+'/index/broadcastDetail/'+_broadcastsJson[_broadcast_index_cur].id;
        }
    }
    //表的样式切换
    var $biaopanLi = $(".biaopan>li");
    $biaopanLi.click(function(){
        $(this).addClass("bianpan-li").siblings().removeClass("bianpan-li");
    });
    //切换图时间
    var $tradeChart = $(".trade-chart-period");
    $tradeChart.click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
    //切换 柱状线图
    var $articleSpan = $("#article>span");
    $articleSpan.click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
</script>
</body>
</html>