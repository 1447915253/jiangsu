
$(function () {

});

//
function _moneyOut(){
    var v = $('#_money_out_div').valid();
    if(v){
        var obj = $('#_money_out_div').obj();
        $.ajax({
            url: ctx + '/money/out',
            contentType: 'application/json;charset=UTF-8',
            type: 'POST',
            data: JSON.stringify(obj),
            success: function (data) {
                layer.msg('提现成功', function () {
                    window.location.href = ctxWap + '/user?'+Math.random();
                });
            }
        });
    }
}
function agentMoneyOut(){
    var v = $('#_money_out_div').valid();
    if(v){
        var obj = $('#_money_out_div').obj();
        $.ajax({
            url: ctx + 'agent/money/out',
            contentType: 'application/json;charset=UTF-8',
            type: 'POST',
            data: JSON.stringify(obj),
            success: function (data) {
                layer.msg('提现成功', function () {
                    window.location.href = ctxWap + '/user?'+Math.random();
                });
            }
        });
    }
}