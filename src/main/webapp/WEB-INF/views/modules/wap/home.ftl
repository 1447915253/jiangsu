<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <#assign footer = "home"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/index.css" rel="stylesheet" type="text/css"/>
    <#--<link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>-->
    <script type="text/javascript"  src="${ctxStatic}/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <ul class="user_b my-bar">
        <li class="my-bar-item touxiang">
            <div class="vm infor text-center">
                <div class="photo ">
                    <img src="<#if user.userHeader??>${user.userHeader}<#else>${ctxStatic}/img/images2/touxiang.png</#if>" alt="">
                </div>
            </div>
            <div class="vm_text">
                <p class="vm_name"><#if user.chnName??>${user.chnName}</#if></p>
                <p class="vm_person" onclick="window.location.href = ctxWap + '/user?r='+Math.random()">个人中心</p>
            </div>
            <div class="vm_img"></div>
            <div class="line"></div>
        </li>
        <li class="my-bar-item my-bar-item_yw">
            <p class="my-bar-item_y">余额</p>
            <p class="f24">&yen;${user.money}</p>
            <div class="line"></div>
        </li>
        <li class="my-bar-item po">
            <a href="${ctxWap}/money/cashCoupon" class="quan"><img src="all/image/quana.png">券</a>
            <div class="po_number"><span>${_user_can_use_coupons?size}张</span></div>
            <div class="line"></div>
        </li>
        <li class="my-bar-item po">
            <a href="${ctxWap}/pay/recharge" class="quan"><img src="all/image/qs_05.png">充</a>
            <div class="po_number_fan"><span>首充返100%</span></div>
        </li>
    </ul>
    <div class="yts">
    <#list contracts as it>
        <ul class="dk_yin">
            <li class="dk_yin_item">
                <div class="yin_dh">
                    <div class="yin_dh_left left">
                        <img src="<#if it_index == 0 >${ctxStatic}/all/image/yin_03.png<#elseif it_index== 1>${ctxStatic}/all/image/tong.png<#else>${ctxStatic}/all/image/shiyou.png</#if>"
                             class="left">
                        <p class="yin_dh_text">${it.name}</p>
                    </div>
                    <div class="yin_dh_center left">
                        <p class="yin_dh_center_n left">${markets[it_index].price}
                            <span><#if (markets[it_index].price?number - markets[it_index].open?number) gt 0>+${markets[it_index].price?number - markets[it_index].open?number}<#else>${markets[it_index].price?number - markets[it_index].open?number}</#if>
                                <#if (markets[it_index].price?number - markets[it_index].open?number)*100/(markets[it_index].open?number) gt 0>(+${(markets[it_index].price?number - markets[it_index].open?number)*100/(markets[it_index].open?number)}%)<#else>(${(markets[it_index].price?number - markets[it_index].open?number)*100/(markets[it_index].open?number)}%)</#if></span>
                        </p>
                        <img src="<#if (markets[it_index].open?number - markets[it_index].price?number > 0) >${ctxStatic}/all/image/xiajiang.png<#else>${ctxStatic}/all/image/zhang.png</#if>"
                             class="right">
                    </div>
                    <div class="jiaoyi right">
                        <span onclick="changePage('${it.code}')">交易</span>
                        <img src="${ctxStatic}/all/image/rmb.png"></div>
                </div>
                <div id="${it.code}" class="yin_content"></div>
            </li>
        </ul>
    </#list>
        <p style="font-size:0.35rem; padding-left: 0.3rem;line-height:0.5rem; margin-top: -0.2rem;">广播：<span onclick="showBroadCast()" id="bc_show"></span></p>

    </div>
</section>
<div class="popBox" id="cashCouponBox" style="display: none">
    <div class="popCont_img">
        <div class="popCont_img_num"><span>100</span>元</div>
        <div class="erweim_img"><button  onclick="getCashCoupon()">点击领取</button></div>
    </div>
</div>
<#include "/wap/footer.ftl"/>
<script type="application/javascript">
    var _contracts = ${_contracts};
    var _broadcastsJson = ${broadcastsJson};

    $('.yin_content').height((document.body.clientHeight
            - $('.user_b').outerHeight() - $('.yin_dh').outerHeight()*_contracts.length - $('#bar-tab').outerHeight()*2)/3);
</script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/highstock.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/js/themes/dark-unica.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/js/modules/home/home.js' charset='utf-8'></script>
<script type="text/javascript">
    var data = ${data};
    var _data = null;
    var d = null;
    var _dataJSON = null;
    <#list contracts as it>
        _data = [];
        _dataJSON = data.${it.code};
        for (var i = _dataJSON.length - 1; i >= 0; i--) {
            d = _dataJSON[i];
            _data.push([d.timestamp, Number(d.open), Number(d.high), Number(d.low), Number(d.close)]);
        }
        if (!!window.console) {
            console.log(new Date(_data[_data.length - 1][0]).toLocaleString() + '  ' + new Date(_data[_data.length - 2][0]).toLocaleString());
        }
        $('#' + '${it.code}').highcharts('StockChart', {
            chart: {
                panning: false,
                pinchType: '',
                backgroundColor: '#002b54'
            },
            credits: {
                enabled: false
            },
            rangeSelector: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            navigator: {
                enabled: false
            },
            scrollbar: {
                enabled: false
            },
            rangeSelector: {
                enabled: false
            },
            xAxis: {
                gridLineWidth: 1,
                gridLineDashStyle: 'ShortDash',
                range: 6*60*60*1000,
                lineWidth: 0,
                tickInterval: 1 * 3600 * 1000,
                labels: {
                    enabled: false
                }
            },
            yAxis: {
                gridLineWidth: 1,
                gridLineDashStyle: 'ShortDash',
                lineWidth: 0,
                labels: {
                    enabled: false
                }
            },
            tooltip: {
                borderColor: '#d52e32',
                backgroundColor: 'rgba(47,47,47,0.6)'
            },
            series : [{
                name : '行情',
                data : _data,
                lineWidth: 1,
                lineColor: '#d52e32',
                tooltip: {
                    valueDecimals: (_data[0][1] + '').indexOf('.') != -1 ? 2 : 0
                }
            }]
        });
    </#list>

    $(function () {
        var flag = <#if isNeedGiveCashCoupon??>"${isNeedGiveCashCoupon.mobile}"+</#if>"valide";
        if(  flag != "valide"){
            $("#cashCouponBox").show();
        }
        an();
    });
    function changePage(num) {
        window.location.href = ctxWap + '/trade?id='+num;
    }

    function getCashCoupon() {
        $.ajax({
            url: ctx + '/users/getCashCoupon',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                $("#cashCouponBox").hide();
                window.location.replace(ctxWap +'/home?r='+Math.random());
            }
        });
    }
</script>
</body>
</html>