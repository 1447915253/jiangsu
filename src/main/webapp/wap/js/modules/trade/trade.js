$(function () {
    $('.popCont').each(function () {
        var $pt = $(this).find('.pop_tit');
        var $pbd = $(this).find('.pop_bd');
        $pbd.find('.pho_bd_item').eq(0).show();
        $pt.find('a').eq(0).addClass('on');
        $pt.find('a').click(function () {
            var index = $(this).index();
            $(this).addClass('on').siblings().removeClass('on');
            $pbd.find('.pho_bd_item').hide();
            $pbd.find('.pho_bd_item').eq(index).fadeIn();
        })
    })
    $('.pa_y li,.ykItem span').click(function () {
        $(this).addClass('on').siblings().removeClass('on');
    });

    //加的效果
    $(".add").click(function () {
        var n = $(this).prev().val();
        var num = parseInt(n) + 1;
        if (num == 0) {
            return;
        }
        $(this).prev().val(num);
    });
    //减的效果
    $(".jian").click(function () {
        var n = $(this).next().val();
        var num = parseInt(n) - 1;
        if (num == 0) {
            return
        }
        $(this).next().val(num);
    });

    $(document).on('click', '.holdOrder', function () {
        obj = $(this);
        num = Number($(this).attr('name'));

        //获取持仓列表中的-合约-定金
        var contractName = obj.children().eq(0).text();
        $("#overContractName").text(contractName);
        var overContractMoney = obj.children().eq(5).text();
        $("#overContractMoney").text(overContractMoney);
        //获取持仓列表中的-方向--开仓价格
        var ioc = obj.children().eq(1).text();
        $("#sub").text(ioc);
        if ('买跌' == ioc) {
            $("#sub").removeClass("p_point_red").addClass("p_point_green");
        } else {
            $("#sub").removeClass("p_point_green").addClass("p_point_red");
        }
        var buyMoney = obj.children().eq(3).text();
        var openPrice = parseFloat(buyMoney).toFixed(2);
        $("#hold_Price").text(buyMoney);
        $("#overTime").text(overTime[num]);
        $("#WinBox").show();
    });

    // 登录或者未登录
    if (_isLogin) {

    } else {
        $("nav a").removeAttr('href');
        $("nav a").on("click", function () {
            $("#loginBox").show();
        });

        $("#nologin").on("click", function () {
            //未登录状态操作
            $("#loginBox").show();
        });
    }

    show_time();
    // 定时刷新 k线图
    showKClick(1, true);
    chooseMoney('m7');
    getCurrentTime();
    if (_isMarketOpen) {
        setTimeout(function () {
            marketNew();
            // marketNewWebSocket();
        }, 1000);
    }

    $("#inputBuyMoney").on("click", function () {
        $('#chooseMoney li').removeClass('on');
    });

    // 异步获取用户余额（针对用户充钱之后，返回加载旧页面导致的余额未更新）
    if (_isLogin) {
        moneyNow('-1');
        /*查询充值订单*/
        findMoneyRecordStatus();
    }

    _refresh_trade_setTimeout_fun = setTimeout(function () {
        _refresh_trade_fun();
    }, 3000);
});
var _refresh_trade_setTimeout_fun = null;
var _refresh_trade_fun = function () {
    var hasOpen = false;
    for (var i = 0; i < _contracts.length; i++) {
        if (_contracts[i].marketOpen) {
            hasOpen = true;
            break;
        }
    }

    if (!hasOpen) {
        return;
    }

    $.ajax({
        url: ctx + '/trade/lasts',
        success: function (data) {
            if(data.length > 10){
                data.splice(10, data.length - 10);
            }
            if (data.length > 0) {
                var len = $('.newHold').length;
                if (len == 10) {
                    $('.newHold:gt(' + (len - data.length - 1) + ')').remove();
                }

                var arr = [];
                for (var i = 0; i < data.length; i++) {
                    holds_fun(arr, data[i]);
                }
                if ($('.newHold').length == 0) {
                    $('#newTradeDetail').append(arr.join(''));
                } else {
                    $('.newHold').eq(0).before(arr.join(''));
                }
            }
        }
    });
    _refresh_trade_setTimeout_fun = setTimeout(function () {
        _refresh_trade_fun();
    }, 1000 + Math.random()*1000);
}
function sureBox(d) {
    if (_isLogin) {
        if (!_contracts[current_code_p].marketOpen) {
            showBox('#stopBox');
            return;
        }
        _direction = d;
        if (imageId == '2') {
            _selectOffTime = 2;
            $('#buyTimeSecond').text('180秒');
        } else if (imageId == '1') {
            _selectOffTime = 1;
            $('#buyTimeSecond').text('60秒');
        } else if (imageId == '0') {
            _selectOffTime = 0;
            $('#buyTimeSecond').text('30秒');
        }
        //显示金额和预期收益
        $('#chooseMoney li').removeClass('on');
        changeMoney('m7');
        showBox('#sureBox');
        $('#contractName').text(_contracts[current_code_p].name);
        $('#showPrice').text($('#currentMoney_' + _contracts[current_code_p].code).text());
        $('#showUpAndDown').text(d == '0' ? '买涨' : '买跌');

        $('#coupon_div .coupon_type_div_selected').removeClass('coupon_type_div_selected');
        $('#coupon_type3_num_span').text(1);
        if (_coupons.length != 0) {
            $('#coupon_div').show();
        } else {
            $('#coupon_div').hide();
        }

        $('#coupon_type1_div,#coupon_type2_div,#coupon_type3_div').hide();
        for (var i = 0; i < _coupons.length; i++) {
            $('#coupon_type' + _coupons[i].type + '_div').show();
            if (_coupons[i].type == '3') {
                $('#coupon_type3_div_select').show();
            }
        }
        changeMoneyCall();
    } else {
        reg();
    }
}

/*时时持仓订单*/
var overTime = [];
var it = null;
for (var i = 0; i < _hold.length; i++) {
    it = _hold[i];
    overTime.push(Math.round((Number(it.sellTime) - _now) / 1000));
}
var _show_time_fun = null;
if(overTime.length > 0){
    _show_time_fun = setInterval(function () {
        show_time();
    }, 1000);
}
function show_time() {
    var totalInteval = null;
    for (var i = 0; i < overTime.length; i++) {
        it = _hold[i];
        totalInteval = Number(it.offTime.replace('M', '')) * 60;
        if (overTime[i] > 0) {
            overTime[i]--;
            $("#overTime").text(overTime[num]);
        } else {
            /*判断倒计时是否显示*/
            if (num == i && $("#WinBox").css('display') != 'none') {
                //$("#trends").text($("#trend").text());
                setTimeout(getHold(_hold[num].id), 1000);
            }
            _hold.splice(i, 1);
            overTime.splice(i, 1);
            i--;
            $("#chicangbd_new_list_" + it.id).remove();
            obj = null;
            if (num > 0) {
                num--;
            }
            setTimeout('moneyNow(_hold.length)', 1000);
        }
    }
}

function getHold(id) {
    $.ajax({
        url: ctx + "/trade/holdId?id=" + id,
        type: 'get',
        success: function (data) {
            var trade = data.trade;
            if (trade != "") {
                if (Number(trade.buyPoint) == Number(trade.sellPoint)) {
                    $("#difMoney").text("平 " + trade.difMoney).css('color', '#bbbbbb');
                } else if (Number(trade.difMoney) > 0) {
                    $("#difMoney").text("盈 " + trade.difMoney).css('color', '#FA2E42');
                } else {
                    $("#difMoney").text("亏 " + trade.difMoney).css('color', '#48AB72');
                }
                $("#buyPoints").text(trade.buyPoint);
                $("#sellPoints").text(trade.sellPoint);
                if (0 == trade.buyUpDown) {
                    $("#subs").removeClass('p_point_green').addClass('p_point_red').text('买涨');
                } else {
                    $("#subs").removeClass('p_point_red').addClass('p_point_green').text('买跌');
                }
                if (((trade.sellPoint < trade.buyPoint) && (trade.buyUpDown == "0")) || ((trade.sellPoint > trade.buyPoint) && (trade.buyUpDown == "1"))) {
                    $("#trends").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                } else if (((trade.sellPoint > trade.buyPoint) && (trade.buyUpDown == "0")) || ((trade.sellPoint < trade.buyPoint) && (trade.buyUpDown == "1"))) {
                    $("#trends").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                } else {
                    $("#trends").removeClass('point_red').removeClass('point_green').addClass('point_grey').text('平');
                }
                $("#WinBoxs").css("display", 'block');
            } else {
                settime(function () {
                    getHold(id);
                }, 200)
            }
        }
    })
}

function hide(ts) {
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

function reg() {
    //未登录状态操作
    $("#loginBox").show();
    return;
}
var _selectOffTime = 0;
/*买涨买跌下单弹出框*/
var imageId;
function coupon_type_click(flag) {
    /*$("#chooseMoney li").removeClass("on");*/
    var _coupon_selected_div = $('#coupon_div .coupon_type_div_selected');
    if (flag === 1 || flag === 2 || flag === 3) {
        var coupon_money=0;
        if (flag === 1 || flag === 2) {
            coupon_money=$("#directProfitCoupon").val();
            var _offTime_arr_temp = _contracts[current_code_p].offTimes.split(",");
            var _con = _contracts[current_code_p];
            if (_offTime_arr_temp[_selectOffTime] != '0.5M') {
                return;
            }
            //清空输入金额
            $("#inputBuyMoney").val('');
            /*如果是白银的情况下清楚金额*/
            $("#chooseMoney li").removeClass("on");
            _selectMoney = 0;
            $("#showMoney").text(coupon_money + "元");
        }
        _coupon_selected_div.removeClass('coupon_type_div_selected');
        if (_coupon_selected_div.attr('id') != 'coupon_type' + flag + '_div') {
            for (var i = 0; i < _coupons.length; i++) {
                if (_coupons[i].type == flag) {
                    //changeMoneyCall();
                    if (flag == '3' && _selectMoney < 100) {
                        return;
                    }
                    $('#coupon_type' + flag + '_div').addClass('coupon_type_div_selected');
                    if (flag == '3') {
                        coupon_money=$("#gainProfitCoupon").val();
                    }
                    break;
                }
            }
        }
        changeMoneyCall(coupon_money);
    } else if (flag === 31 || flag === 32) {
        if (_coupon_selected_div.length != 0 && _coupon_selected_div.attr('id') == 'coupon_type3_div' && _selectMoney >= 100) {
            var coupon_money=$("#gainProfitCoupon").val();
            var off = flag === 31 ? 1 : -1;
            var num = Number($('#coupon_type3_num_span').text());
            var temp = num + off;
            for (var i = 0; i < _coupons.length; i++) {
                if (_coupons[i].type == '3' && _coupons[i].num >= temp && temp >= 1 && (temp*coupon_money) <= Math.floor(_selectMoney/10 )) {
                    $('#coupon_type3_num_span').text(temp);
                    changeMoneyCall(coupon_money);
                    return;
                }
            }
        }
    }
}
function chooseMoney(id) {
    $("#" + id).on("click", changeMoney(id));
    if (!$("#coupon_type3_div").hasClass('coupon_type_div_selected')) {
        /*选择金额去除必盈，直盈卷*/
        $("div").removeClass("coupon_type_div_selected");
    };
}
function changeMoney(id) {
    $("#showMoney").text($("#" + id).text());
    $("#" + id).addClass("on");
    _selectMoney = parseInt($("#" + id).text());
    //预期收益
    changeMoneyCall();
    $('#coupon_type3_num_span').text(1);
}
function inputBuyMoney() {
    var money = $("#inputBuyMoney").val();
    var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/
    var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{0,2})?$/;
    if (!exp.test(money)) {
        _selectMoney = 100;
        $("#showMoney").text(_selectMoney);
        $("#inputBuyMoney").val(_selectMoney);
        $("#inputBuyMoney").val('');
        return;
    }
    $("#showMoney").text(money);
    _selectMoney = parseInt(money);
    //预期收益
    changeMoneyCall(_selectMoney);
    $('#coupon_type3_num_span').text(1);
    /*输入金额去除必盈卷，直盈卷*/
    if (!$("#coupon_type3_div").hasClass('coupon_type_div_selected')) {
        $("div").removeClass('coupon_type_div_selected');
    } else {

    }
}

// 计算预期收入
function changeMoneyCall(coupon_money) {
    if (_selectMoney < 100) {
        $('#coupon_type3_div').removeClass('coupon_type_div_selected');
    }
    if( coupon_money == null){
        coupon_money = 0;
    }
    var _coupon_selected_div = $('#coupon_div .coupon_type_div_selected');
    var couponMoney = 0;
    if (_coupon_selected_div.attr('id') == 'coupon_type1_div') {
        couponMoney = Number($('#coupon_type1_num_span').text()) * coupon_money;
    } else if (_coupon_selected_div.attr('id') == 'coupon_type2_div') {
        couponMoney = Number($('#coupon_type2_num_span').text()) * coupon_money;
    } else if (_coupon_selected_div.attr('id') == 'coupon_type3_div') {
        couponMoney = _selectMoney < 100 ? 0 : Number($('#coupon_type3_num_span').text()) * coupon_money;
    }
    var percentProfit = _contracts[current_code_p].percentProfits.split(',')[_selectOffTime];
    percentProfit = Number(percentProfit) / 100;
    $('#willMoney').text((_selectMoney + (_selectMoney + couponMoney) * percentProfit).toFixed(2) + '元');
}

// 下单
var _selectMoneyStr = $('#showMoney').text();
var _selectMoney = parseInt(_selectMoneyStr);
function _make() {
    var _offTime_arr_temp = _contracts[current_code_p].offTimes.split(",");
    var _con = _contracts[current_code_p];
    var _coupon_selected_div = $('#coupon_div .coupon_type_div_selected');
    var _couponType = null, _couponNum = 0, _couponMoney = 0;
    if (_coupon_selected_div.attr('id') == 'coupon_type1_div') {
        _couponType = 1;
        _couponNum = Number($('#coupon_type1_num_span').text());
    } else if (_coupon_selected_div.attr('id') == 'coupon_type2_div') {
        _couponType = 2;
        _couponNum = Number($('#coupon_type2_num_span').text());
        _couponMoney =$('#directProfitCoupon').val();
    } else if (_coupon_selected_div.attr('id') == 'coupon_type3_div') {
        _couponType = 3;
        _couponNum = Number($('#coupon_type3_num_span').text());
        _couponMoney =$('#gainProfitCoupon').val();
    }
    if (_selectMoney > 5000) {
        layer.msg('单笔金额不能超过5000！');
        $('.layui-m-layer').css('z-index','99999999999999');
        return;
    }

    var obj = {
        code: _con.code,
        type: 0,
        money: _selectMoney,
        buyUpDown: _direction,
        offTime: _offTime_arr_temp[_selectOffTime],
        couponType: _couponType,
        couponNum: _couponNum,
        couponMoney:_couponMoney
        //offPoint:_offPoint_arr_temp[_selectOffPoint],
        //couponId: _selectCoupon == 0 ? null : _coupons[_selectCoupon].id
    };

    $.ajax({
        url: ctx + '/trade/make',
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(obj),
        success: function (data) {
            //去掉使用的代金券
//                    if(_selectCoupon != 0){
//                        _coupons.splice(_selectCoupon, 1);
//                        _coupons_arr.splice(_selectCoupon, 1);
//                    }
            showBox('#successBox');
            moneyNow();
            hold();
            if (_couponNum != 0) {
                refreshCoupons();
            }
        },
        useDefaultError: false,
        error: function (p1, p2, p3, result) {
            if (result.code == '99') {
                showBox('#stopBox');
            } else {
                layer.msg(result.error);
                $('.layui-m-layer').css('z-index','99999999999999')
            }
        }
    });
}


function moneyNow(tradeId) {
    if ("undefined" == typeof(tradeId)) {
        tradeId = 0;
    }
    $.ajax({
        url: ctx + '/users/getUserMoney?tradeId=' + tradeId,
        type: 'get',
        success: function (data) {
            if (data.money == "-1") {
                setTimeout(moneyNow(tradeId), 1000);
            } else {
                if(data != null && data.money != null && /^\d+(\.\d+)?/.test(data.money)){
                    $(".a_money").text(fmoney(data.money, 2));
                }
            }
        }
    });
}

function hold() {
    $.ajax({
        url: ctxWap + '/trade/holds?flag=2',
        type: 'get',
        success: function (data) {
            clearInterval(_show_time_fun);
            $(".holdOrder").remove();
            /*计算下单时间*/
            _hold = [];
            _hold = data.list;
            overTime.splice(0, overTime.length);
            /*获取平仓时间*/
            var timeList = data.timeList;
            for (var i = 0; i < timeList.length; i++) {
                overTime.push(timeList[i]);
            }
            /*显示必胜开始*/
            $("#overTime").text(overTime[0]);
            $("#WinBox").show();
            if (0 == _direction) {
                $("#sub").removeClass('p_point_green').addClass('p_point_red');
            } else {
                $("#sub").removeClass('p_point_red').addClass('p_point_green');
            }
            $("#sub").text(_direction == 0 ? '买涨' : '买跌');

            // 设置定时器
            _show_time_fun = setInterval(function () {
                show_time();
            }, 1000);

            _hold = data.list;
            var arr = [];
            var win = [];
            var list = data.list;
            for (var i = 0; i < list.length; i++) {
                $("#hold_Price").text(list[0].buyPoint);
                $("#overContractName").text(list[0].contractName);
                $("#overContractMoney").text(list[0].money);
                $("#buyContractName").text(list[0].contractName);
                $("#buyContractMoney").text(list[0].money);
                arr.push('<ul class="chicangbd_new_list holdOrder" id="chicangbd_new_list_' + list[i].id + '" name="' + i + '" style="cursor: pointer;">');
                arr.push('<li name=' + list[i].code + '>' + list[i].contractName + '</li>');
                if (0 == list[i].buyUpDown) {
                    arr.push('<li class="heyu_de_red" name="0">买涨</li>');
                } else {
                    arr.push('<li class="heyu_de_green" name="1">买跌</li>')
                }
                arr.push('<li>' + list[i].buyTime.split(' ')[1] + '</li>');
                arr.push('<li>' + list[i].buyPoint + '</li>');
                arr.push('<li class="hold_current_price_' + list[i].code + '">4568</li>');
                arr.push('<li>' + list[i].money + '</li>');
                arr.push('</ul>');
            }
            $("#holdOrder").after(arr.join(''));
        }
    });
}
var num = 0;
var obj = null;
function change(param) {
    clearTimeout(_refresh_trade_setTimeout_fun);
    /*最新成交*/
    if (param == 'newTrade') {
        $('#newTrade').addClass('on');
        $('#holdTrade').removeClass('on');
        $('#tradeRecord').removeClass('on');
        $('#newTradeDetail').show();
        $('#holdTradeDetail').hide();
        $('#tradeRecordDetail').hide();
        // $.ajax({
        //     url: ctxWap + '/trade/holds',
        //     type: 'get',
        //     success: function (data) {
        //         _refresh_trade_fun();
        //         $(".newHold").remove();
        //         var arr = [];
        //         var list = data.list;
        //         for (var i = 0; i < list.length; i++) {
        //             holds_fun(arr, list[i]);
        //         }
        //
        //         $(".newbd_new").after(arr.join(''));
        //     }
        // });
    } else if (param == 'holdTrade') {/*持仓订单*/
        $('#holdTrade').addClass('on');
        $('#newTrade').removeClass('on');
        $('#tradeRecord').removeClass('on');
        $('#holdTradeDetail').show();
        $('#newTradeDetail').hide();
        $('#tradeRecordDetail').hide();
    } else {/*交易记录*/
        $('#tradeRecord').addClass('on');
        $('#newTrade').removeClass('on');
        $('#holdTrade').removeClass('on');
        $('#tradeRecordDetail').show();
        $('#newTradeDetail').hide();
        $('#holdTradeDetail').hide();
        $.ajax({
            url: ctxWap + '/trade/holds?flag=1',
            type: 'get',
            success: function (data) {
                $(".tradingRecord").remove();
                var arr = [];
                var list = data.list;
                for (var i = 0; i < list.length; i++) {
                    arr.push('<ul class="chicangbd_new_list tradingRecord">');
                    arr.push('<li>' + list[i].contractName + '</li>');
                    if (0 == list[i].buyUpDown) {
                        arr.push('<li class="heyu_de_red">买涨</li>');
                    } else {
                        arr.push('<li class="heyu_de_green">买跌</li>');
                    }
                    arr.push('<li>' + list[i].sellTime.split(' ')[1] + '</li>');
                    arr.push('<li>' + list[i].money + '</li>');
                    arr.push('<li>' + list[i].difMoney + '</li>');
                    if (((list[i].sellPoint - list[i].buyPoint) > 0 && (list[i].buyUpDown == 0)) || ((list[i].sellPoint - list[i].buyPoint) < 0 && list[i].buyUpDown == 1)) {
                        arr.push('<li class="chicangbd_red">盈</li>');
                    } else if (((list[i].sellPoint - list[i].buyPoint) < 0 && (list[i].buyUpDown == 0)) || ((list[i].sellPoint - list[i].buyPoint) > 0 && list[i].buyUpDown == 1)) {
                        arr.push('<li class="chicangbd_green">亏</li>');
                    } else {
                        arr.push('<li class="chicangbd_grey">平</li>');
                    }
                    arr.push('</ul>');
                }
                $("#tradingRecord").after(arr.join(''));
            }
        });
    }
}

function holds_fun(arr, it) {
    var myRound = Math.round(Math.random() * 9 + 3);

    var myMoney = [200, 1200, 450, 600, 300, 500, 700, 800, 1000, 2000, 3000, 1800, 3500, 4000];
    arr.push('<ul class="newbd_new_list newHold">');
    arr.push('<li>' + it.buyTime.split(' ')[1] + '</li>');
    arr.push('<li><ul class="heyu_de">')
    arr.push('<li>' + it.contractName + '</li>');
    if (0 == it.buyUpDown) {
        arr.push('<li class="heyu_de_red">买涨</li>');
    } else {
        arr.push('<li class="heyu_de_green">买跌</li>')
    }
    arr.push('</ul></li>')
    var color;
    if (it.buyUpDown == 0) {
        color = '#D83F4E';
    } else {
        color = '#1FC65B';
    }
    var resultMoney = 0;
    /*0元显示券*/
    var moneyOrTicket;
    if (0 == it.money) {
        moneyOrTicket = '券';
    } else {
        resultMoney = it.money <= 20 ? myMoney[myRound - 1] : it.money;
        moneyOrTicket = resultMoney;
    }
    arr.push('<li><span style="margin-right: 1.2rem;">' + moneyOrTicket + '</span><b style="width: '
        + (resultMoney < 200 && resultMoney>0 ? 3 : resultMoney * 0.015 ) + 'px; background-color: ' + color + ';"></b></li>')
    arr.push('</ul>');
}
function isNull(arg1) {
    return !arg1 && arg1 !== 0 && typeof arg1 !== "boolean" ? true : false;
}

var _direction = 0;
// K线图类型
var _marketKData_interval_position = 0;
var _marketKData_interval_val = ["1", "1", "5", "15", "30", "60", "1440"];
var _marketKData_interval_setTimeout_fun = null;
function marketKData() {
    var now = new Date();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();

    var isR = true;
    // 每10秒刷新一次
    if (_marketKData_interval_position == 0 && second == 8) {
        isR = false;
    } else if (_marketKData_interval_position == 1 && second == 8) {
        isR = true;
    } else if (_marketKData_interval_position == 2 && minute % 5 == 0 && second == 10) {
        isR = true;
    } else if (_marketKData_interval_position == 3 && minute % 15 == 0 && second == 12) {
        isR = true;
    } else if (_marketKData_interval_position == 4 && minute % 30 == 0 && second == 14) {
        isR = true;
    } else if (_marketKData_interval_position == 5 && minute == 0 && second == 16) {
        isR = true;
    } else {
        isR = false;
    }
    if (isR) {
        showK(_marketKData_interval_position, true);
    }
    _marketKData_interval_setTimeout_fun = setTimeout(function () {
        marketKData();
    }, 1000);
}

function showKClick1(p) {
    if(_marketKData_interval_position == p){
        return;
    }
    showKClick(p);
}
// 是否重新绘制的标记
var _p_pre = null;
// 是否重新绘制标记线
var _plotLine_refresh = false;
function showKClick(p, force) {
    var w = $('#article span');
    if (w.eq(1).hasClass('active')) {
        if (p == 0 && force !== true) {
            return;
        }
    } else if (w.eq(0).hasClass('active')) {
        if (p != 0 && force !== true) {
            $('.trade-chart-period').removeClass('active');
            return;
        }
    }

    _chart1 = null;
    _plotLine_refresh = true;
    // if (_p_pre != (p == 0 ? p : 1)) {
    //     // 重新绘制K线图
    //     if (_chart1 != null) {
    //         _chart1.destroy();
    //         _chart1 = null;
    //         _plotLine_refresh = true;
    //     }
    //     _p_pre = p == 0 ? p : 1;
    // }

    $('.kdata_c').removeClass('on');
    $('.kdata_c').eq(p).addClass('on');

    clearTimeout(_marketKData_interval_setTimeout_fun);
    layer.open({
        type: 2,
        shadeClose: false
    });

    if (force === true) {
        $('.trade-chart-period').removeClass('active');
        if (p == 0) {
            $('.trade-chart-period').eq(p).addClass('active');
        }
    }
    showK(p);
    /*显示拓展时间*/
//        if(p == 4){
//            $("#addDate").css('display','block');
//        }else{
//            $("#addDate").css('display','none');
//        }
    if (_isMarketOpen && _contracts[current_code_p].marketOpen) {
        _marketKData_interval_setTimeout_fun = setTimeout(function () {
            marketKData();
        }, 1000);
    }
}
var _chart1 = null;
var _data = [];
var _is_loading = false;
function showK(p, autoRefresh) {
    autoRefresh = autoRefresh || false;
    _marketKData_interval_position = p;
    var interval = _marketKData_interval_val[p];
    _is_loading = true;
    $.ajax({
        url: ctx + '/market/kdata',
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({

            "code": _codes[current_code_p],
            "interval": interval
        }),
        success: function (data) {
            layer.closeAll();
            _is_loading = false;

            var d = null;
            _data = [];
            for (var i = 0; i < data.length; i++) {
                d = data[i];
                _data.push([d.timestamp, Number(d.open), Number(d.high), Number(d.low), Number(d.close)]);
            }

            $("#currentMoney_" + _codes[current_code_p]).text(data[data.length-1].open);

            if (!!window.console && _data.length > 1) {
                console.log(new Date(_data[_data.length - 1][0]).toLocaleString() + '  ' + new Date(_data[_data.length - 2][0]).toLocaleString());
            }

            if(p != 0){
                if (p == 1) {
                    _data.splice(0, _data.length - 20);
                }else{
                    _data.splice(0, _data.length - 20);
                }
            }

            // 分时图转1分钟蜡烛图，数据点太多，截取40个点
            if (p == 0) {
                /* if (p == 0) {
                 _data.splice(0, _data.length - 60);
                 console.log("截取后"+_data.length);
                 }*/

                if (_chart1 == null) {
                    _chart1 = new Highcharts.StockChart({
                        chart: {
                            panning: false,
                            marginLeft: 0,
                            marginRight: 0,
                            pinchType: '',
                            zoomType: '',
                            backgroundColor: '#161618',
                            renderTo: $('#container')[0],
                            animation: false
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
                        xAxis: {
                            gridLineWidth: 1,
                            gridLineDashStyle: 'ShortDash',
                            gridLineColor: '#333333',
                            labels:{
                                formatter: function() {
                                    if(this.isLast){
                                        return '';
                                    }
                                    var t = new Date(this.value);
                                    return int2Str(t.getMonth()+1, 2) + '-' + int2Str(t.getDate(), 2) + '<br/>' + int2Str(t.getHours(), 2)+':' + int2Str(t.getMinutes(), 2);
                                },
                                useHTML : false,
                                style: {
                                    color: 'gray'
                                },
                                zIndex: -1
                            },
                            tickPositioner: function () {
                                var positions = [],
                                    increment = (_data.length - _data.length%5)/5;
                                for(var i = 0; i < _data.length; i +=increment){
                                    positions.push(_data[i][0]);
                                }
                                return positions.slice(0, positions.length);
                            },
                            lineWidth: 0,
                            tickLength: 0,
                            crosshair: {
                                color: '#d52f31',
                                width: 0.5,
                                label: {
                                    enabled: true,
                                    backgroundColor: '#d52f31',
                                    formatter: function (value) {
                                        var t = new Date(value);
                                        return int2Str(t.getMonth()+1, 2) + '-' + int2Str(t.getDate(), 2) + '<br/>' + int2Str(t.getHours(), 2)+':' + int2Str(t.getMinutes(), 2);
                                    }
                                }
                            }
                        },
                        yAxis: {
                            opposite: false,
                            gridLineWidth: 1,
                            gridLineDashStyle: 'Dash',
                            gridLineColor: '#333333',
                            labels: {
                                align: "right",
                                x: 38,
                                style: {
                                    color: 'gray'
                                }
                            },
                            lineWidth: 0,
                            tickLength: 0
                        },
                        tooltip: {
                            borderColor: 'gray',
                            backgroundColor: '#fff',
                            borderWidth: 2,
                            style: {
                                color: 'black'
                            },
                            formatter: function () {
                                return '价格:'+this.y;
                            },
                            followPointer: false,
                            followTouchMove: true
                        },
                        series: [{
                            id: '1',
                            name: '行情',
                            type: "area",
                            data: _data,
                            fillColor: {
                                linearGradient: {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops: [
                                    [0, 'rgba(63, 63, 63, 0.8);'],
                                    [1, 'rgba(63,63,63,0.8)']
                                ]
                            },
                            states:{
                                hover: {
                                    enabled: false
                                }
                            },
                            lineColor: '#717171',
                            lineWidth: 2,
                            threshold: null,
                            animation: false,
                            tooltip: {
                                valueDecimals: _contracts[current_code_p].precision
                            },
                            zIndex: 10
                        }]
                    });
                } else {
                    _chart1.get("1").setData(_data);
                }
            } else {
                // 分时图转1分钟蜡烛图，数据点太多，截取40个点
                _chart1 = null;
                if (_chart1 == null) {
                    _chart1 = new Highcharts.StockChart({
                        chart: {
                            panning: false,
                            marginLeft: 0,
                            marginRight: 0,
                            pinchType: '',
                            zoomType: '',
                            backgroundColor: '#161618',
                            renderTo: $('#container')[0],
                            animation: false
                        },
                        credits: {
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
                        plotOptions: {
                            candlestick: {
                                dataGrouping: {
                                    enabled: false
                                }
                            }
                        },
                        tooltip: {
                            animation: false,
                            borderColor: 'gray',
                            borderWidth: 2,
                            backgroundColor: '#fff',
                            style: {
                                color: 'black'
                            },
                            headerFormat: '',
                            pointFormat: '开盘价: {point.open}<br/>最高价: {point.high}<br/>最低价: {point.low}<br/>收盘价: {point.close}<br/>',
                            followPointer: false,
                            followTouchMove: true
                        },
                        xAxis: {
                            gridLineWidth: 1,
                            gridLineDashStyle: 'Dash',
                            gridLineColor: '#333333',
                            lineColor: '#333333',
                            labels:{
                                formatter: function() {
                                    if(this.isLast){
                                        return '';
                                    }
                                    var t = new Date(this.value);
                                    return int2Str(t.getMonth()+1, 2) + '-' + int2Str(t.getDate(), 2) + '<br/>' + int2Str(t.getHours(), 2)+':' + int2Str(t.getMinutes(), 2);
                                },
                                useHTML : false,
                                style: {
                                    color: 'gray'
                                },
                                zIndex: -1
                            },
                            tickPositioner: function () {
                                var positions = [];
                                for(var i = 0; i < _data.length; i +=4){
                                    positions.push(_data[i][0] - Number(_marketKData_interval_val[_marketKData_interval_position])*60*1000/2);
                                }
                                positions.push(this.dataMax + Number(_marketKData_interval_val[_marketKData_interval_position])*60*1000/2);
                                return positions;
                            },
                            lineWidth: 0,
                            tickLength: 0,
                            crosshair: {
                                color: '#d52f31',
                                width: 0.5,
                                label: {
                                    enabled: true,
                                    backgroundColor: '#d52f31',
                                    formatter: function (value) {
                                        var t = new Date(value);
                                        return int2Str(t.getMonth()+1, 2) + '-' + int2Str(t.getDate(), 2) + '<br/>' + int2Str(t.getHours(), 2)+':' + int2Str(t.getMinutes(), 2);
                                    }
                                }
                            }
                        },
                        yAxis: {
                            opposite: false,
                            gridLineWidth: 1,
                            gridLineDashStyle: 'Dash',
                            gridLineColor: '#333333',
                            labels: {
                                align: "right",
                                x: 38,
                                style: {
                                    color: 'gray'
                                }
                            },
                            lineWidth: 0,
                            tickLength: 0,
                            crosshair: {
                                snap: false,
                                color: '#d52f31',
                                width: 1,
                                label: {
                                    enabled: true,
                                    align: 'left',
                                    backgroundColor: '#d52f31',
                                    format: '{value:.2f}'
                                }
                            }
                        },
                        series: [{
                            id: "1",
                            name: '行情',
                            type: 'candlestick',
                            upColor: '#d52f31',
                            upLineColor: '#d52f31',
                            color: '#1cab39',
                            lineColor: '#1cab39',
                            data: _data,
                            animation: false,
                            pointPadding: -0.1,
                            tooltip: {
                                valueDecimals: _contracts[current_code_p].precision
                            }
                        }]
                    });
                } else {
                    _chart1.get("1").setData(_data);
                }
            }

            if (_time == null) {
                _time = _data[_data.length - 1][0];
            }
            var price = Number($('#currentMoney_' + _codes[current_code_p]).text());
            if (/^\d+(\.\d+)?$/.test(price)) {
                showNewLine(Number(price), _time, true);
            }
        }
    });
}
/*["1","1","5","15","30","60","1440","120","180","240"];*/
/*点击加号*/
var i = 4;
function addDate(adds) {
    /*if(i >= 4 && i < 6){
     i = i + Number(num);
     }else{
     i = 4;
     }*/
    i = i + Number(adds);
    if (i > 6 || i < 4) {
        if (Number(adds) == 1) {
            i = 6;
        } else {
            i = 4;
        }
        return;
    }
    showK(i);
}

function newCall(data) {
    if (!data || data.length == 0) {
        return;
    }
    if (!!window.console) {
        if (!data[0].timestamp) {
            // console.log(JSON.stringify(data));
        }
    }
    var isZhang = false;
    var it = null, code = null, price = null, open = null, heigh = null, low = null, close = null, off1 = null,
        off2 = null;
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
        heigh = Number(it.high);
        low = Number(it.low);
        close = Number(it.close);

        /*isZhang = price > open;*/

        var flat;
        if (i == 0) {
            isZhang = price > oldPrice;
            flat = price == oldPrice;
            oldPrice = Number(it.price);
        } else if (i == 1) {
            isZhang = price > oldTwoPrice;
            flat = price == oldTwoPrice;
            oldTwoPrice = Number(it.price);
        } else if (i == 2) {
            isZhang = price > oldThreePrice;
            flat = price == oldThreePrice;
            oldThreePrice = Number(it.price);
        } else if (i == 3) {
            isZhang = price > oldFourPrice;
            flat = price == oldFourPrice;
            oldFourPrice = Number(it.price);
        }


        off1 = (isZhang ? '+' : '') + (price - open);
        off2 = (isZhang ? '+' : '') + decimalAfter2((price - open) * 100 / open) + '%';

        for (var j = 0; j < _contracts.length; j++) {
            if (_contracts[j].code == code) {
                _inde = j;
            }
        }

        //如果价格变动才显示
        var currentMoney = $('#currentMoney_' + it.code).text();
        if (price != currentMoney) {
            $('#currentTime_' + it.code).text('时间：' + serverCurrentTime.toTimeString().substr(0, 8));
        }
        //$('#currentMoney_' + it.code).
        // 改变页面显示
        if (flat) {

        } else {
            if (isZhang) {
                $('#currentMoney_' + it.code).removeClass('my-bar_green').addClass('my-bar_red');
                $('#currentMoney_' + it.code).html(formatNumber(price, _contracts[_inde].precision) + ' <i class="icon i_red"></i>');
            } else {
                $('#currentMoney_' + it.code).removeClass('my-bar_red').addClass('my-bar_green');
                $('#currentMoney_' + it.code).html(formatNumber(price, _contracts[_inde].precision) + ' <i class="icon i_green"></i>');
            }
        }
        //弹出框的当前价
        if (it.code == _contracts[current_code_p].code && null == obj) {
            $('#showPrice').text($('#currentMoney_' + _contracts[current_code_p].code).text());
            $('#now_Price').text($('#currentMoney_' + _contracts[current_code_p].code).text());
            if (0 != _hold.length && [] != _hold.length) {
                if (((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))) {
                    $("#trend").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                } else if (((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))) {
                    $("#trend").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                } else {
                    $("#trend").removeClass('point_red').removeClass('point_green').addClass('point_grey').text('平');
                }
            }
        }

        //持仓的当前价
        //如果选择了持仓订单走这里
        if (null != obj) {
            if (it.code == obj.children().eq(0).attr('name')) {
                $('#now_Price').text(price);
                if (((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))) {
                    $("#trend").removeClass('point_red').removeClass('point_grey').addClass('point_green').text('亏');
                } else if (((price > _hold[num].buyPoint) && (_hold[num].buyUpDown == "0")) || ((price < _hold[num].buyPoint) && (_hold[num].buyUpDown == "1"))) {
                    $("#trend").removeClass('point_green').removeClass('point_grey').addClass('point_red').text('盈');
                } else {
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

function getCodes() {
    var __codes = [];
    for (var i = 0; i < _contracts.length; i++) {
        if (!_contracts[i].marketOpen) {
            continue;
        }
        __codes.push(_contracts[i].code);
    }
    return __codes;
}

/**
 * {"status": 0, "body":
 *    {"High": 382.05, "BV1": 0.0, "TotalVol": "0", "SV1": 0.0, "SP1": 0.0, "Price": 381.56,
 *    "Time": "2017-04-19 10:14:57", "LastClose": 381.91, "Diff": -0.35, "Volume": "0", "Source": "11210", "BP1": 0.0,
 *    "Low": 381.53, "TotalAmount": "0", "DiffRate": -0.09, "StockCode": "YCLMASTER", "price2": "0",
 *    "LastTime": 1492568097.0, "Open": 381.91, "price3": 381.91, "LastDiff": -0.04},
 *      "event": "OptionData", "timestamp": 1492568097000}
 */
var ws = null;
function marketNewWebSocket() {
    var __codes = getCodes();
    if (__codes.length == 0) {
        return;
    }
    try{
        if(ws != null)
            ws.close();
    }catch (e){

    }
    ws = new WebSocket('ws://47.94.225.214:8888/ws');
    ws.onopen = function() {
        var data = {
            "event": "REG",
            'Key': __codes.join(',')
        };
        ws.send(JSON.stringify(data));
    };
    ws.onmessage = function(ev) {
        var data = JSON.parse(ev.data.toString());
        // console.log(data.body);
        var _body = {};
        for(var attr in data.body){
            _body[attr.toLowerCase()] = data.body[attr];
        }
        _body.code = data.body.StockCode;
        newCall([_body]);
    };
    ws.onclose = function(ev) {
        marketNewWebSocket_on_error();
    };
    ws.onerror = function(ev) {
        marketNewWebSocket_on_error();
    };
}

function marketNewWebSocket_on_error() {
    setTimeout(function () {
        try{
            if(window.console){
                window.console.log('重连webSocket');
            }
            marketNewWebSocket();
        } catch (e){
            marketNewWebSocket_on_error();
        }
    }, 2000);
}

/**
 * [{"code":"YKCMASTER2","price":"4437.38","open":"4453.38","close":"4453.38","high":"4453.38","low":"4432.25","time":"2017-04-19 10:24:56","timestamp":1492568696000}
 * ,{"code":"OKYBBTC","price":"7101.28","open":"7091.77","close":"7091.77","high":"7107.82","low":"7089.96","time":"2017-04-19 10:24:56","timestamp":1492568696000}
 * ,{"code":"YCLMASTER","price":"381.53","open":"381.93","close":"381.93","high":"381.93","low":"381.53","time":"2017-04-19 10:24:56","timestamp":1492568696000}
 * ,{"code":"OKYBHG","price":"2326.45","open":"2304.20","close":"2304.20","high":"2335.18","low":"2302.38","time":"2017-04-19 10:24:56","timestamp":1492568696000}]
 */
/*保留上一秒的价格*/
var oldPrice;
var oldTwoPrice;
var oldThreePrice;
var oldFourPrice;
var _time = null;
// 固定频率动态刷新行情
function marketNew() {
    if (!!window.console) {

    }

    var __codes = getCodes();
    if (__codes.length == 0) {
        return;
    }

/*    $.ajax({
        url: ctx + '/market/new',
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "codes": __codes.join(',')
        }),
        success: function (data) {
            newCall(data);
        }
    });
    setTimeout(function () {
        marketNew();
    }, 1500);*/

    var codes = __codes.join(',');
    $.ajax({
        url: 'http://47.94.214.39/new.php?codes='+codes+'&callback=?',
        type: 'get',
        contentType: 'application/json;charset=UTF-8',
        dataType : "jsonp",
        success: function (data) {
            newCall(data);
        }
    });
    setTimeout(function () {
        marketNew();
    }, 1000);
}

// 添加标记线
function addPlotLine(price) {
    // if(_marketKData_interval_position <= 0){
    //     return;
    // }
    if(price == null){
        return;
    }
    _chart1.get('1').yAxis.removePlotLine('2');
    _chart1.get('1').yAxis.addPlotLine({
        color: '#d52e32',
        dashStyle: 'ShortDash',
        id: '2',
        width: 1,
        value: price,
        label: {
            text: '<span style="color: #d3af30;font-family:\'微软雅黑\';">'+ formatNumber(price, _contracts[current_code_p].precision) + '</span>',
            align: 'right',
            useHTML: true,
            y: -5,
            x: 0
        },
        zIndex: 100
    });
}
// 计算价格变动
var _new_line_time_pre = null;
var _new_line_price_pre = null;
var _new_line_ms = null;
function showNewLine(price, time, force) {
    if (_chart1 != null) {
        if (_new_line_price_pre != price) {

            _new_line_ms = _new_line_time_pre == null ? 0 : new Date().getTime() - _new_line_time_pre.getTime();
            _new_line_price_pre = price;
            _new_line_time_pre = new Date();

            addPlotLine(price);
        } else {
            if (force === true) {
                addPlotLine(price);
            } else {
                if (_plotLine_refresh) {
                    addPlotLine(price);
                    _plotLine_refresh = false;
                }
            }
        }
        refreshId1(price, time);
    }
}

function refreshId1(price, time) {
    time = formatTime(time);
    if (_chart1 != null) {
        var is0 = _marketKData_interval_position == 0;
        var _it = _chart1.get("1");
        var _last_p = is0 ? _data[_data.length-1] : _it.data[_it.data.length-1];
        if (_it != null) {
            var _p = is0 ? [_last_p[0], _last_p[1]] : [_last_p.options.x, _last_p.options.open, _last_p.options.high, _last_p.options.low, _last_p.options.close];
            var isRemove = false;
            if (_p == null || _p[0] < time) {
                if (_p != null) {
                    _p = is0 ? [time, _p[1]] : [time, _p[1], price, price, price];
                } else {
                    _p = is0 ? [time, price] : [time, price, price, price, price];
                }
            } else {
                isRemove = true;
            }

            if (_marketKData_interval_position == 0) {
                if(isRemove && _p[1] == price){
                    return;
                }
                _p[1] = price;
            } else {
                if (isRemove && _p[4] == price) {
                    return;
                }
                _p[2] = Math.max(price, _p[2]);
                _p[3] = Math.min(price, _p[3]);
                _p[4] = price;
            }

            if(!is0){
                if (isRemove) {
                    _last_p.update(_p);
                }else{
                    _it.removePoint(0);
                    _it.addPoint(_p);
                }
            }else{
                if(isRemove){
                    _it.removePoint(_data.length-1, false);
                }
                _it.addPoint(_p);
            }
        }
    }
}
/**
 * 格式化时间戳，以适应K线图数据展示（主要是蜡烛图）
 * @param time
 * @returns {number}
 */
function formatTime(time) {
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

    if (step < 60) {
        if (step != 1) {
            off = (minute - minute % step) + (minute % step != 0 || second != 0 ? step : 0);
            t.setMinutes(off);
        }
    } else if (step >= 60 && step < 1440) {
        step = step / 60;
        t.setMinutes(0);
        off = (hour - hour % step) + (hour % step != 0 || second != 0 ? step : 0);
        if (step != 1) {
            t.setHours(off);
        }
    } else if (step == 1440) {
        step = step / 1440;
        t.setMinutes(0);
        t.setHours(0);
        off = (date - date / step) + (date % step != 0 || second != 0 ? step : 0);
        if (step != 1) {
            t.setDate(off);
        }
    }
    if (!!window.console) {
//                console.log(new Date(time).toLocaleString() + ' *********** ' + step + ' ************ ' + off + ' *********** ' + t.toLocaleString());
    }
    return t.getTime();
}
// Highcharts全局配置
Highcharts.setOptions({
    global: {
        useUTC: true,
        timezoneOffset: -8 * 60
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
        numericSymbols: ["k", "M", "G", "T", "P", "E"],
        printChart: "Print chart",
        rangeSelectorFrom: "From",
        rangeSelectorTo: "To",
        rangeSelectorZoom: "Zoom",
        resetZoom: "恢复",
        resetZoomTitle: "Reset zoom level 1:1",
        shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        shortWeekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
        thousandsSep: "",
        weekdays: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"]
    }
});

// 券
var _coupons = null;
function refreshCoupons() {
    $.ajax({
        url: ctx + '/coupon/findCoupons',
        type: 'GET',
        success: function (data) {
            _coupons = data;
            var coupon_total_count = 0;
            for (var i = 0; i < data.length; i++) {
                if(data[i] != null && data[i].num != null && /^\d+$/.test(data[i].num)){
                    coupon_total_count += Number(data[i].num);

                }
            }
            $('#coupon_total_count').text(coupon_total_count);
        }
    });
}
if (_isLogin) {
    refreshCoupons();
}

// 广播内容
// var _broadcast_index = 0;
// var _broadcast_index_cur = 0;
function an() {
    // $('#bc_show').fadeOut('slow', function () {
    //     $('#bc_show').text(_broadcastsJson[_broadcast_index].title);
    //     $('#bc_show').fadeIn('slow');
    //     _broadcast_index_cur = _broadcast_index;
    //     _broadcast_index++;
    //     if (_broadcast_index >= _broadcastsJson.length) {
    //         _broadcast_index = 0;
    //         $.ajax({
    //             url: ctx + '/index/broadcastList',
    //             type: 'GET',
    //             data: {},
    //             success: function (data) {
    //                 _broadcastsJson = data;
    //             }
    //         });
    //     }
    // });
    // setTimeout(function () {
    //     an();
    // }, 7000);
}
//表的样式切换
function changeBlock(the, index) {
    imageId = index;
    _selectOffTime = index;
    $(the).addClass("bianpan-li").siblings().removeClass("bianpan-li");
}
//切换图时间
var $tradeChart = $(".trade-chart-period");
$tradeChart.click(function () {
    if ($(".trade-chart-type").eq(0).hasClass('active')) {
        $(".trade-chart-period").eq(0).addClass("active").siblings().removeClass("active");
        return;
    }
    $(this).addClass("active").siblings().removeClass("active");
});
//切换 柱状线图
var $articleSpan = $("#article>span");
$articleSpan.click(function () {
    $(".trade-chart-period").eq(0).addClass("active").siblings().removeClass("active");
    $(this).addClass("active").siblings().removeClass("active");
});
/*register*/
function formSubmit() {
    // document.getElementById("regist_form").submit();
    if (!$('#regist_form').valid()) {
        return;
    }
    hideBox();
    $.ajax({
        url: ctx + '/reg',
        type: 'POST',
        data: $('#regist_form').obj(),
        success: function (data) {
            var now = new Date();
            $.cookie('_auth', JSON.stringify(data), {
                path: '/',
                expires: now.setTime(now.getTime() + (7 * 24 * 60 * 60 * 1000))
            });
            window.location.href = ctxWap + '/home?tokenId='+'renrenbao';
        }
    });

}
function sendSms(the) {
    var mobile = $('input[name="mobile"]').val();
    if (!/^[1-9]{1}[0-9]{10}$/.test(mobile)) {
        layer.msg("手机号不合法");
        $('.layui-m-layer').css('z-index','99999999999999');
        return;
    }
    sendSMSValidCode('reg', mobile, function (data) {
        settime(the);
    });
}
/*获取验证码倒计时*/
var countdown = 60;
function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.value="获取验证码";
        countdown = 60;
        return;
    } else {
        obj.setAttribute("disabled", true);
        obj.value="重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function() {
            settime(obj) }
        ,1000)
}
function goLogin() {
    window.location.href = ctxWap + '/login?' + Math.random();
}

/* 查询用户当前充值记录 */
function findMoneyRecordStatus(){
    $.ajax({
        url: ctx + '/users/getMoneyRecord',
        type: 'GET',
        success: function(data){
            console.log("充值未到账的记录：" + data);
            if(data != "0"){
                setTimeout('findMoneyRecordStatus()', 2000);
            }else {
                moneyNow(0);
            }
        }
    })
}

var _move_flag = 0;
function _on_touch_start() {
    // _move_flag = (_move_flag + 1) % 2;
    // if(_move_flag % 2 == 0){
    //     return false;
    // }
    return false;
}
function _on_touch_end() {
    var _series = _chart1.get("1");
    for (var i = 0; i < _series.data.length; i++) {
        _series.data[i].setState();
    }
    _chart1.axes[0].hideCrosshair();
    _chart1.axes[1].hideCrosshair();

    _chart1.tooltip.hide(false);

    _series.onMouseOut();
}

/*function hello(){
    if(getCodes().length != 0){
        layer.open({
            content: '尾号' + int2Str(Math.round(Math.random()*10000), 4)
            + '<span style="color: #FA2E42;"> 盈利+'
            + Math.round(Math.random()*100)*10 + '</span>'
            ,skin: 'msg'
            ,time: 2
            ,anim: 'up'
        });
    }
    setTimeout(hello, (Math.random() > 0.5 ? 3000 : 1000 ) + Math.random()*1000 + 500);
}
if(_isLogin){
    setTimeout(function () {
        hello();
    }, 5000);
}*/
