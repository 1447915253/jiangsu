<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <title>遐琛微交易</title>
    <#include "/wap/header.ftl" >
    <#assign footer = "trade"/>
    <link rel="shortcut icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/cd.css" />
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/global.css">
    <link rel="stylesheet" href="${ctxStatic}/css/base.css">
    <style>
        .menus ul li:nth-child(2) a{
            color:#FF8500;
        }
        .wallet:before{
            background-position-y: -39px;
            background-position-x: -85px;
        }
        .min{
            background: #000C18;
        }
        .caozuo{
            margin-top: 0.8rem;
        }
    </style>
</head>
<body style="max-width: 768px;margin: 0 auto;">

<script>
    var currentCode = 'YAG';//当前显示的交易品种代码
    var currentInterval = 1; //设置当前的图类型
</script>
<!--顶部开始-->
<div class="top_div" style="max-width: 768px;">
    <h1>交易</h1>
</div>
<!--顶部结束-->
<!----主体开始-->
<div class="min">
    <div class="nav">
        <ul>
<#--            <li class="tong  gaoliang" id="10" data="YAG" keymoney="100">
                <span>DK银</span>
                <div class="zhangfu green">
                    <p id="price10">3657</p>
                    <span id="diff10">29</span><span>
                    </span><span id="diffrate10">0.80%</span>
                </div>
            </li>-->
        <#list contracts as it>
            <li class="tong <#if it_index==0>gaoliang</#if>" id="10" data="YAG" keymoney="100" onclick="_changeShow(${it_index});" data-index="${it_index}">
            ${it.name}
                <#if !isMarketOpen>
                    <p>休市</p>
                <#else>
                    <div id="${it.code}_price" class="zhangfu <#if (markets[it_index].open?number - markets[it_index].price?number > 0) >green<#else>red</#if>">
                        <p id="con_price_${it.code}" class="price10">${markets[it_index].price}</p>
                        <#assign ab=markets[it_index].price?number - markets[it_index].open?number/>
                        <span id="con_price_1_${it.code}"><#if ab gt 0>+${ab}<#else>${ab}</#if></span>
                        <#assign a=(markets[it_index].price?number - markets[it_index].open?number)*100/(markets[it_index].open?number)/>
                        <span id="con_price_2_${it.code}"><#if a gt 0>+${a}<#else>${a}</#if>%</span>
                    </div>
                </#if>
            </li>
        </#list>
        </ul>
    </div>
    <div class="content">
        <div class="tong">
            <!--<ul class="content_menu">
                <li >投资金额</li>
                <li>&yen100</li>
                <li>券</li>
                <li>到期时间</li>
                <li>100M</li>
            </ul>-->
            <#list contracts as it>
                <div class="show1 content_box <#if (markets[it_index].open?number - markets[it_index].price?number > 0) >green<#else>red</#if>" id="show1_${it.code}">
                    <input type="hidden" name="allprice" id="allprice" >
                    <div id="top_${it.code}">${markets[it_index].price}<p> ${markets[it_index].price?number - markets[it_index].open?number}（${(markets[it_index].price?number - markets[it_index].open?number)*100/(markets[it_index].open?number)}%）</p></div>
                    <img src="${ctxStatic}/img/images2/bottom.png" id="imga" style="display:none;"> <img src="${ctxStatic}/img/images2/top.png" id="imgb" style="display: none;">
                </div>
                <ul class="show2 content_details" id="show2_${it.code}">
                    <li>最高 <p class="red zhangfu" id="high">${markets[it_index].high}</p></li>
                    <li>最低 <p class="green zhangfu" id="low">${markets[it_index].low}</p></li>
                    <li>昨收 <p class="zhangfu" style="color: #BEBDBC;" id="close">${markets[it_index].close}</p></li>
                    <li>今开 <p class="zhangfu" style="color: #BEBDBC;" id="open">${markets[it_index].open}</p></li>
                </ul>
            </#list>
            <ul class="content_fenshixian">
                <li class="kdatali gaoliang" id="1" onclick="showKClick(0, this)">分时图</li>
                <li class="kdatali" id="5" onclick="showKClick(1, this)">5M</li>
                <li class="kdatali" id="15" onclick="showKClick(2, this)">15M</li>
                <li class="kdatali" id="30" onclick="showKClick(3, this)">30M</li>
                <li class="kdatali" id="1h" onclick="showKClick(4, this)">1H</li>
                <li class="kdatali" id="1d" onclick="showKClick(5, this)">1D</li>
            </ul>
            <!--iframe  显示折线图-->
            <div id="container" style="height: 200px;width:100%;" >
                <iframe  id="nowtu" style='width:100%;height:100%;border:none;' src="">
                </iframe>
            </div>
            <div class="caozuo" style="text-align: center;">
                <a class="maizhang" href="javascript:showBox(0);" style="color:#fff;">买涨</a><input type="hidden" name="zzzzzz" id="zzzzzz">
                <span>
                    <h2 id="ownerlow">76%</h2>
                    <h6>收益比率</h6>
                </span>
                <a class="maidie" href="javascript:showBox(1);" style="color: #fff;">买跌</a>
            </div>
        </div>
    </div>
</div>

<!----主体结束-->
<!--弹出框-->
<div class="pop_box" style="display: none;" id="box">
    <!--交易密码-->
    <div style="height: 29rem;margin-left: -136px" class="pop_box_jiaoyimima">
        <div><span id="mgoperation">下单操作</span><img src="${ctxStatic}/img/images2/close.png"></div>
        <form action="#" method="post" action="" >
            <input type="hidden" id="diezhang" name="diezhang">
            <input type="hidden" id="nexttime" name="nexttime" value="5">

            <div style="line-height: 2rem;color:#f00;">
                <div id="productName" style="border:none;display:inline-block;line-height:2rem;width: 45%;font-size: 1.3rem;font-weight:bold;"></div>
                <div style="border:none;display:inline-block;width: 45%;text-align:center;line-height: 2rem;font-size: 1.3rem;font-weight: bold;">收益<span id="ownerlow02" style="font-size: 1.3rem;margin-left: 0.5rem;color: #f00;">75</span></div>
            </div>
            <ul class="pa_y clearfix" style="margin:0;">
                <span style="font-size: 15px;">购买金额</span>
            </ul>
            <ul class="pa_y clearfix" id="totalmoney" style="margin:0;">
                <li onclick="addMoney(-1);" style="margin-right:0;background:none;width: 25%;"><img src="${ctxStatic}/img/images2/back.png" style="width: 50%;"></li>
                <li class="on" data="100" style="margin:0;background:none;width: 50%;"><input id="_make_money" type="text" readonly="readonly" value="100" style="width: 100%;outline: none;background-color: #000637;color:#fff;line-height: 2rem;text-align: center;border: none;font-size: 1.5rem;"></li>
                <li onclick="addMoney(1);" style="margin-right:0;background:none;width: 25%;"><img src="${ctxStatic}/img/images2/more.png" style="width: 50%;"></li>
            </ul>
            <div id="settime" style="margin:1rem 0;text-align: center;">
                <span class="red" style="display: inline-block;margin-right: 1.2rem">1M</span>
                <span data="5" style="display: inline-block;margin-right: 1.2rem">5M</span>
                <span data="10" style="display: inline-block;margin-right: 1.2rem">10M</span>
                <span data="20" style="display: inline-block;margin-right: 1.2rem">20M</span>
                <span data="60" style="display: inline-block;">60M</span>
            </div>
            <ul class="clearfix" style="margin:0;">
                <span style="font-size: 15px;">优惠券</span>
            </ul>
            <ul class="pa_y clearfix" style="margin:0;">
                <li onclick="changeCoupon(-1);" style="margin-right:0;background:none;width: 25%;"><img src="${ctxStatic}/img/images2/back.png" style="width: 50%;"></li>
                <li class="on" style="margin:0;background:none;width: 50%;"><input id="_make_coupon" type="text" readonly="readonly" value="无可用代金券" style="width: 100%;outline: none;background-color: #000637;color:#fff;line-height: 2.0rem;text-align: center;border: none;font-size: 1.3rem;"></li>
                <li onclick="changeCoupon(1);" style="margin-right:0;background:none;width: 25%;"><img src="${ctxStatic}/img/images2/more.png" style="width: 50%;"></li>
            </ul>
            <div style="margin-bottom: 1rem;"><a href="javascript:;" onclick="_make()" style="font-weight: bold;">下单</a></div>
        </form>
    </div>
    <!--使用代金券-->
    <div class="pop_box_daijinquan"  style="">
        <div><span>使用代金券：<i class="quan">1张</i></span><img src="${ctxStatic}/img/images2/close.png"></div>
        <div>
            <ul>
                <li>交易品种 <span>铜</span></li>
                <li>投资金额 <span>&yen200</span></li>
                <li>收益比率 <span>76%</span></li>
                <li>到期时间 <span>1M</span></li>
                <li>最新价格 <span class="red">3865</span></li>
                <li>委托方向 <span class="red">买涨 <img class="zhangdie" src="${ctxStatic}/img/images2/zhang.png"></span></li>
            </ul>
            <a href="#">确定</a>
        </div>
    </div>
    <!--提示：下单金额-->
    <div class="pop_box_xiadanjine" style="height: 17rem;display: none">
        <div style="text-align: left"><span style="margin-left: 1rem"><img src="${ctxStatic}/img/images2/logo2.png" style="margin-right: 0.5rem">提示</span><img src="${ctxStatic}/img/images2/close.png"></div>
        <div style="border-bottom: none;margin-top: 1.5rem">
            下单金额应为10的整倍数!
            <i>确认</i>
        </div>
    </div>
    <!--提示：余额不足-->
    <div class="pop_box_yuebuzu" style="display: none;margin-left: -112px;">
        <div style="text-align: left"><span style="margin-left: 1rem"><img src="${ctxStatic}/img/images2/logo2.png" style="margin-right: 0.5rem">提示</span><img src="${ctxStatic}/img/images2/close.png"></div>
        <div style="border-bottom: none;margin-top: 1.5rem">
            414,余额不足，交易失败！
            <u style="display:inline-block;position: absolute;left: 18%;bottom: 19%;">现在充值 <img src="${ctxStatic}/img/images2/arrows.png"></u>
            <a href="/user/Recharge.html">确认</a>
        </div>
    </div>
    <!--提示：休市-->
    <div class="pop_box_xiushi" style="display: none;height:13rem;">
        <div style="text-align: left"><span style="margin-left: 1rem"><img src="${ctxStatic}/img/images2/logo2.png" style="margin-right: 0.5rem">提示</span><img src="${ctxStatic}/img/images2/close.png"></div>
        <div style="border-bottom: none;margin-top: 1.5rem">
            <h2 style="line-height: 2rem;font-size: 2.3rem;color: #FC0402;">休市</h2>
            或不在交易时段
        </div>
    </div>
    <!--提示：支付密码错误-->
    <div class="pop_box_zhifumima" style="display: none;">
        <div style="text-align: left"><span style="margin-left: 1rem"><img src="${ctxStatic}/img/images2/logo2.png" style="margin-right: 0.5rem">提示</span><img src="${ctxStatic}/img/images2/close.png"></div>
        <div style="border-bottom: none;margin-top: 1.5rem">
            支付密码错误，请重试。
            <div style="border-bottom: none;margin-left: 25%">
                <s id="repeat" style="    text-decoration: none;">重试</s>
                <a href="#" style="color: #04AC1B; margin-left: 15%">忘记密码</a>
            </div>
        </div>
    </div>
</div>
<#include "/wap/footer.ftl">
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/highstock.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/asserts/highcharts/js/themes/dark-unica.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctxStatic}/js/modules/trade/trade.js' charset='utf-8'></script>
<script type="text/javascript">

    $(".pop_box>div>div>img").click(function(){
        $(".pop_box").hide();
        $(this).parent().parent().hide();
    })

    var _direction = null;
    function showBox(direction) {
        if(!_isMarketOpen){
            layer.msg('休市中');
            return;
        }
        $(".pop_box").show();
        $(".pop_box_jiaoyimima").show();
//        $(".pop_box_daijinquan").show();

        var _con = _contracts[current_code_p];

        var _arr = [];
        var _offTimes = _con.offTimes.split(',');
        var _percentProfit = _con.percentProfits.split(',');
        for (var i = 0; i < _offTimes.length; i++){
            if(i == 0){
                _arr.push('<span data-index="'+i+'" class="red" style="display: inline-block;margin-right: 1.2rem">'+_offTimes[i]+'</span>');
            }else if(i+1 == _offTimes.length){
                _arr.push('<span data-index="'+i+'" style="display: inline-block;">'+_offTimes[i]+'</span>');
            }else{
                _arr.push('<span data-index="'+i+'" style="display: inline-block;margin-right: 1.2rem">'+_offTimes[i]+'</span>');
            }
        }
        $('#settime').html(_arr.join(''));

        $('#ownerlow02').text(_percentProfit[0]+'%');

        $('#settime span').click(function(){
            $('#settime .red').removeClass('red');
            $(this).addClass('red');
            $('#ownerlow02').text(_percentProfit[Number($(this).data("index"))]+'%');
        });

        $('#mgoperation').text(direction == 0 ? '看涨建仓' : '看跌建仓');
        $('#productName').html(_con.name+': <span id="cur_point_'+_con.code+'">'+$('#con_price_'+_con.code).text()+'</span>');

        _direction = direction;
    }

    var _moneys = [20, 50, 100, 200, 500, 1000];
    var _moneys_index = 2;
    function addMoney(w){
        _moneys_index += w;
        if(_moneys_index <= -1){
            _moneys_index = _moneys.length - 1;
        }else if(_moneys_index >= _moneys.length){
            _moneys_index = 0;
        }
        $('#_make_money').val(_moneys[_moneys_index]);
    }

    var _coupons = ${_user_coupons};
    _coupons.splice(0, 0, {
        id: null,
        money: _coupons.length == 0 ? '无可用代金券' : '不使用'
    });
    var _coupons_index = 0;
    var _coupons_id = null;
    function changeCoupon(w){
        _coupons_index += w;
        if(_coupons_index <= -1){
            _coupons_index = _coupons.length - 1;
        }else if(_coupons_index >= _coupons.length){
            _coupons_index = 0;
        }
        $('#_make_coupon').val(_coupons[_coupons_index].money+(_coupons_index != 0 ? '(满'+_coupons[_coupons_index].minMoney+'元)' : ''));
        _coupons_id = _coupons[_coupons_index].id;
    }

    function _make() {
        var _con = _contracts[current_code_p];
        var obj = {
            code: _con.code,
            type: 0,
            money: $('#_make_money').val(),
            buyUpDown: _direction,
            offTime: $('#settime .red').text(),
            couponId: _coupons_id
        };
        if(_coupons_index != 0){
            if(Number(_coupons[_coupons_index].minMoney) > Number(obj.money)){
                layer.msg('此优惠券不满足使用规则，请重新选择！');
                return;
            }
        }

        $.ajax({
            url: ctx + '/trade/make',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(obj),
            success: function(data){
                //去掉使用的代金券
                if(_coupons_index != 0){
                    _coupons.splice(_coupons_index, 1);
                }
                layer.msg('建仓成功！', function () {
                    window.location.replace(ctxWap + '/trade/hold?r=' + Math.random());
                });
            }
        });
    }

    $(function () {
        _changeShow(<#if num??>${num}<#else>0</#if>);
    });
    var _contracts = ${_contracts};
    var _broadcastsJson = ${broadcastsJson};
    var _codes = ${_codes};
    var _isMarketOpen = ${_isMarketOpen};
    var _user_money = ${user.money?c};
    var _user_coupon_money = ${user.couponMoney?c};
</script>
</body>
</html>