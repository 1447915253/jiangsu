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
    <!--<link href="all/css/footer.css" rel="stylesheet" type="text/css"/>-->
    <script type="text/javascript" src="${ctxStatic}/js/rem.js"></script>
    <title>${title}</title>
</head>
<body id="fixation">
<section class="wrap-page">
    <div class="user_top">
        <a class="left buy_chic <#if !flag??>buy_dd</#if>" id="chicang" onclick="change1()">
            <i class="icon i_z buy_chic_fangd"></i>持仓中
        </a>
        <a class="right buy_chic <#if flag??>buy_dd</#if>" id="todayOrder" onclick="chang2()">
            <i class="icon i_z  buy_chic_dingd"></i>当日订单
        </a>
    </div>
    <!--持仓页面-->
<#if list??><#list list as it>
    <div class="new_tong div1" id="holdDiv_${it.id}" <#if flag??>style="display: none;"</#if> >
        <div class="new_tong_lf left">
            <p>订单号:<span>${it.serialNo}</span></p>
            <p class="tong_text">${it.contractName}</p>
            <p>最新价格</p>
            <#list contracts as c><#if it.contractName?string == c.name?string ><p id="${it.id}_1" class="">${markets[c_index].price?number}</p></#if></#list>
        </div>
        <div class="new_tong_rt right">
            <div class="buyru_top">
                <div class="buyru_top_one buyru_t left"><img src="<#if it.buyUpDown?number == 0>${ctxStatic}/all/image/zhang.png<#else>${ctxStatic}/all/image/xiajiang.png</#if>"></div>
                <div class="bianhao_bot_tong_line left"></div>
                <div class="buyru_top_two buyru_t left">
                    <p>买入价</p>
                    <p>${it.buyPoint?c}</p>
                </div>
                <div class="bianhao_bot_tong_line left"></div>
                <div class="buyru_top_three buyru_t  left">
                    <p>投资金额</p>
                    <p>&yen;${it.money}</p>
                </div>
                <div class="bianhao_bot_tong_line left"></div>
                <div class="buyru_top_for buyru_t right">
                    <p id="${it.id}_2"></p>
                    <p id="${it.id}_3"></p>
                </div>
            </div>
            <div class="buyru_bot">
                <div class="buyru_bot_lf left">
                    <p>开始时间:<span>${it.buyTime?datetime}</span></p>
                    <input class="lefttime" orderid="${it.id}" lefttime = "" value="${it.sellTime?datetime}" hidden/>
                    <p>剩余时间:<span class="green"><span id="fen_${it.id}"></span>分<span id="miao_${it.id}"></span>秒</span></p>
                </div>
                <div class="buyru_bot_rt right">
                    <div class="buyru_bot_rt_w">
                        <div class="buyru_bot_rt_red" id="color_${it.id}">
                            <div class="num" id="speed_${it.id}"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#list></#if>
    <!--当日订单页面-->
    <div class="account div2" <#if !flag??>style="display: none;"</#if> >
        <div class="left account_dnum">
            当日单数:<span>${toDayTradeList?size}</span>
        </div>
        <div class="right account_ye">
            账户余额:<span>${user.money}</span>
        </div>
    </div>
<#if toDayTradeList??><#list toDayTradeList as it>
    <div class="bianhao div2" <#if !flag??>style="display: none;"</#if> >
        <div class="bianhao_top">
            <div class="left bianhao_num">交易编号：<span>${it.serialNo}</span></div>
            <div class="right bianhao_time">下单时间:<span>${it.buyTime?datetime}</span></div>
        </div>
        <div class="bianhao_line"></div>
        <div class="bianhao_bot">
            <div class="left bianhao_bot_tong left">
                <div class="bianhao_bot_tong_lf">
                    <img src="<#if it.contractName == 'DK银'>${ctxStatic}/all/image/yin_03.png<#elseif it.contractName == 'DK铜'>${ctxStatic}/all/image/tong.png<#else>${ctxStatic}/all/image/shiyou.png</#if>" class="clearfix text-center img">
                    <p class="text-center">${it.contractName}</p>
                </div>
            </div>
            <div class="bianhao_bot_tong_line left"></div>
            <div class="left bianhao_bot_price left">
                <div class="left biao_price_lf">
                    <div class="left bianhao_bot_price_t left">
                        <p>执行价格</p>
                        <p>${it.buyPoint}</p>
                    </div>
                    <div class="left bianhao_bot_price_c left"></div>
                    <div class="left bianhao_bot_price_b left">
                        <p>到期价格</p>
                        <p>${it.sellPoint}</p>
                    </div>
                </div>
                <img src="<#if it.buyUpDown?number == 0>${ctxStatic}/all/image/zhang.png<#else>${ctxStatic}/all/image/xiajiang.png</#if>" class="left text-center">
                <div class="left biao_price_lf left">
                    <div class="left bianhao_bot_price_t">
                        <p>投资金额</p>
                        <p>${it.money}</p>
                    </div>
                    <div class="left bianhao_bot_price_c"></div>
                    <div class="left bianhao_bot_price_b ">
                        <p>到期时间</p>
                        <p>${it.sellTime?datetime}</p>
                    </div>
                </div>
            </div>
            <div class="bianhao_bot_tong_line left"></div>
            <#if it.difMoney gt 0>
                <div class="right bianhao_bot_yk">
                    <p >盈</p>
                    <h3>¥${it.difMoney}</h3>
                </div>
            <#elseif it.difMoney lt 0>
                <div class="right bianhao_botg_yk">
                    <p>亏</p>
                    <h3>¥${it.difMoney}</h3>
                </div>
            <#else>
                <div class="right bianhao_both_yk">
                    <p>平</p>
                    <h3>¥${it.difMoney}</h3>
                </div>
            </#if>
        </div>
    </div>
</#list>
</#if>
</section>
<#include "/wap/footer.ftl"/>
<script>

    $(function(){
        show_time();
    });

    function show_time(){
        var flag;
    <#if list??>
        <#list list as it>
            var time_start = new Date().getTime(); //设定当前时间
            var time_end = new Date('${it.sellTime?datetime}'.replace("-","/").replace("-","/")).getTime(); //设定目标时间
            // 计算时间差
            var time_distance = time_end - time_start;
            flag = time_distance;
            // 天
            var int_day = Math.floor(time_distance / 86400000)
            time_distance -= int_day * 86400000;
            // 时
            var int_hour = Math.floor(time_distance / 3600000)
            time_distance -= int_hour * 3600000;
            // 分
            var int_minute = Math.floor(time_distance / 60000)
            time_distance -= int_minute * 60000;
            // 秒
            var int_second = Math.floor(time_distance / 1000)
            // 时分秒为单数时、前面加零
            if (int_day < 10) {
                int_day = "0" + int_day;
            }
            if (int_hour < 10) {
                int_hour = "0" + int_hour;
            }
            // 显示时间
            /*        $("#time_d").val(int_day);
                    $("#time_h").val(int_hour);*/
            var endDate = new Date('${it.sellTime?datetime}'.replace("-","/").replace("-","/"));
            var startDate = new Date('${it.buyTime?datetime}'.replace("-","/").replace("-","/"));
            var totalInteval = (endDate.getTime() - startDate.getTime())/1000;
            if(flag > 0 ){
                $("#fen_" + "${it.id}").text(int_minute);
                $("#miao_" + "${it.id}").text(int_second);
                $("#speed_"+"${it.id}").text( 100-parseInt((int_minute*60+int_second)*100/totalInteval) +'%');
                $("#color_"+"${it.id}").css('width',100-( (int_minute*60+int_second)*100/totalInteval )+'%');
            }else{
                $("#fen_" + "${it.id}").text("0");
                $("#miao_" + "${it.id}").text("0");
                $("#speed_"+"${it.id}").text('100%');
                $("#color_"+"${it.id}").css('width','100%');
            }

            /* 检测进度条 */
            if("100%" == $("#speed_"+"${it.id}").text()){
                $("#holdDiv_"+"${it.id}").remove();
                removeTradeFromList('${it.id}');
            }
        </#list>
    </#if>
        // 设置定时器
        setTimeout("show_time()",1000);
    }

    function change1() {
        $('#chicang').addClass('buy_dd');
        $('#todayOrder').removeClass('buy_dd');
        $(".div2").hide();
        $(".div1").show();
    }
    function chang2() {
        $('#todayOrder').addClass('buy_dd');
        $('#chicang').removeClass('buy_dd');
        $(".div1").hide();
        $(".div2").show();
        window.location.replace(ctxWap + '/trade/hold?flag=2&r='+Math.random());
    }

    function removeTradeFromList(id) {
        for(var i = 0; i < _list.length; i++){
            if(_list[i].id == id){
                _list.splice(i, 1);
                return;
            }
        }
    }

    var _codes = ${_codes};
    var _list = ${_list};
    // 刷新盈亏情况
    function refreshWinOrLoss() {
        $.ajax({
            url: ctx + '/market/new',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "codes": _codes.join(',')
            }),
            success: function (data) {
                if (!data || data.length == 0) {
                    return;
                }
                // 整理最新价格
                var newPrice = {};
                for(var i = 0; i < data.length; i++){
                    if(data[i] == null){
                        continue;
                    }
                    newPrice[data[i].code] = Number(data[i].price);
                }

                // 改变页面内容
                var t = null, buyPrice = null;
                for(var i = 0; i < _list.length; i++){
                    t = _list[i];
                    buyPrice = Number(t.buyPoint);
                    if(newPrice[t.code]){
                        $('#'+t.id+'_1').text(formatNumber(newPrice[t.code], 3));
                        if(t.buyUpDown == '0'){
                            if(buyPrice > newPrice[t.code]){
                                $('#'+t.id+'_2').html('<font style="color: green">亏</font>');
                                $('#'+t.id+'_3').html('-'+t.money);
                            }else if(buyPrice < newPrice[t.code]){
                                $('#'+t.id+'_2').html('<font style="color: red">盈</font>');
                                $('#'+t.id+'_3').html(Number(t.money)-Number(t.fee));
                            }else{
                                $('#'+t.id+'_2').html('<font style="color: gray">平</font>');
                                $('#'+t.id+'_3').html('<font style="color: gray">0</font>');
                            }
                        }else{
                            if(buyPrice > newPrice[t.code]){
                                $('#'+t.id+'_2').html('<font style="color: red">盈</font>');
                                $('#'+t.id+'_3').html(Number(t.money)-Number(t.fee));
                            }else if(buyPrice < newPrice[t.code]){
                                $('#'+t.id+'_2').html('<font style="color: green">亏</font>');
                                $('#'+t.id+'_3').html('-'+t.money);
                            }else{
                                $('#'+t.id+'_2').html('<font style="color: gray">平</font>');
                                $('#'+t.id+'_3').html('0');
                            }
                        }
                    }

                }
            }
        });
        _timeout_fun = setTimeout(function () {
            refreshWinOrLoss();
        } , 1500);
    }
    refreshWinOrLoss();
</script>
</body>
</html>