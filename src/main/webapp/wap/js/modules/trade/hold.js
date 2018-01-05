var _tradePwd = null;
function sell(id){
    if(_tradePwd == null){
        layer.open({
            content: '<input id="_tradePwd" type="password" style="line-height: 0.6rem; font-size: 0.6rem;" placeholder="请输入交易密码" value=""/>',
            btn: '确定',
            yes: function(index){
                _tradePwd = $('#_tradePwd').val();
                if(!/^.{6,}$/.test(_tradePwd)){
                    layer.msg('请输入正确的交易密码');
                    return;
                }
                $.ajax({
                    url: ctx + '/trade/sell',
                    type: 'PUT',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({
                        "id":id,
                        "tradePassword":md5(_tradePwd)
                    }),
                    success: function(data){
                        // 重置chi持仓记录
                        $('#'+id).remove();
                        for (var i = 0; i < _listJson.length; i++){
                            if(_listJson[i].id == id){
                                _listJson.splice(i, 1);
                            }
                        }
                        if(_listJson.length == 0){
                            $('#ykShow,.ykShow').text('0.00');
                        }
                        var m = Math.round((Number(data.difMoney)+Number(data.fee))*100)/100;
                        layer.open({
                            content: '您'+(m <= 0 ? '亏了' : '赚了')
                            +m+'￥！<br/>'+(data.buyUpDown == '0' ? '买涨' : '买跌')
                            +',合约:'+data.contractName+',建仓：'+data.buyPoint+',平仓:'+data.sellPoint+',手续费：'+data.fee,
                            btn: '我知道了'
                        });
                    }
                });

            },
            end: function () {
                _tradePwd = null;
            }
        });
    }

}

// 轮询计算盈亏
var pre_time = null;
var _calculation_settimeout_fun = null;
var _reload_fun = null;
var _calculation = function (){
    if(_listJson != null && _listJson.length != 0){
        var codes = [];
        var exist = false;
        for (var i = 0; i < _listJson.length; i++){
            for (var j = 0; j < codes.length; j++){
                if(_listJson[i].code == codes[j]){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                codes.push(_listJson[i].code);
            }
            exist = false;
        }
        $.ajax({
            url: ctx + '/market/new',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "codes":codes.join(','),
                "type":"0"
            }),
            success: function(data){
                var news = {};
                var d = null;
                var _dif = {};
                // 整理最新行情
                for (var i = 0; i < data.length; i++){
                    d = data[i];
                    news[d.code] = d;
                    _dif[d.code] = 0;
                    $('.'+d.code+'_s').text(d.price);
                }
                var m = null;
                var upDown = null;
                var dif = null, price = null;
                for (var i = 0; i < _listJson.length; i++){
                    m = _listJson[i];
                    if(!!!news[m.code]){
                        continue;
                    }
                    price = Number(news[m.code].price);
                    // 判断是否到达止盈止损点，刷新页面
                    if(price >= Math.max(m.profitMaxPoint, m.lossMaxPoint) || price <= Math.min(m.profitMaxPoint, m.lossMaxPoint)){
                        if(_reload_fun == null){
                            _reload_fun = setTimeout(function () {
                                window.location.reload();
                                _reload_fun = null;
                            }, 1000);
                        }
                    }
                    upDown = m.buyUpDown == 0 ? 1 : -1;
                    dif = m.pointValue * upDown* (price-m.buyPoint);
                    dif = Math.round(dif*100)/100;
                    _dif[m.code] += dif;
                }
                var total = 0;
                // 重置为0.00
                $('#ykShow,.ykShow').text('0.00');
                for (var dd in _dif){
                    $('#ykShow_'+dd).text(_dif[dd].toFixed(2));
                    total += _dif[dd];
                }
                $('#ykShow').text(total.toFixed(2));
                _calculation_settimeout_fun = setTimeout(function(){
                    _calculation();
                }, 1500);
            }
        });
    }
}

// dom over do
$(function(){
    _calculation();
});