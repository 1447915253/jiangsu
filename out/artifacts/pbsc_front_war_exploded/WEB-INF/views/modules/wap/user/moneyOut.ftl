<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl">
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/tixian.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/denglumimano.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/chjonzhicuowu.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page" style="background: #000c17">
    <div class="tixian">可提现余额:<span id="moneyNow">${user.money}元</span></div>
    <div class="user_top" id="reg_form" >
        <div class="ti_money" id="selectButton">
            <a class="left buy_chic" id="moneyOut">
                <i class="icon i_z buy_yu" id="moneyOut_2"></i>个人余额提现
                <br/><span style="color: white; white-space: nowrap">(账户余额:<span id="money_now">${user.money}</span>)</span>
            </a>
            <a class="right buy_dd" id="profitOut">
                <i class="icon i_z shou_yu" id="profitOut_2"></i>战队佣金提现
                <br/><span style="color: white; white-space: nowrap">(收益余额:<span id="returnMoney">${user.returnMoney}</span>)</span>
            </a>
        </div>
            <div class="w-item clearfix">
                <input type="text" placeholder="请输入提现金额（元）" id="money" name="money" maxlength="11" data-validtype="require">
            </div>
        <div class="ti_money_text">

            <a class="act left bankcardshow" >
                <i class="yinhangka"></i>银行卡</a>
            <a class="act right yongjin" style="margin-right:-0.4rem;<#if user.returnMoney==0>display:none;</#if> "  >
                <i class="yongjinicon"></i>  <button style="font-size:0.5rem;color:#000;background:#ff0000;border:0;border-radius:0.2rem;" type="submit" onclick="commissionToMoney()">佣金转余额</button></a>
        </div>

        <form id="withDraw" class="i-form" method="post" action="#" onSubmit="return false;">
            <div id="bankCard">
                <div class="w-item clearfix">
                    <input type="text" placeholder="请输入持卡人姓名" id="chnName" name="chnName" maxlength="11" data-validtype="require" value="<#if bankCard??>${bankCard.chnName}</#if>">
                </div>
                <#--<div class="w-item clearfix">-->
                    <#--<input id="bank" type="text" placeholder="请选择银行" id="password" name="bankName" data-validtype="require" value="<#if bankCard??>${bankCard.bankName}</#if>">-->
                    <#--<ul id="selectBank" style="display: none; overflow: auto; position:absolute; width: 10rem; top: 1rem; left: 0px;padding-top:5px;z-index: 9999; height: 11rem; background: #000000; font-size: 0.35rem;">-->
                    <#--<#if bankNameList??>-->
                        <#--<#list bankNameList as bankName>-->
                            <#--<li class="province_i" style="height: 30px;line-height: 30px;overflow: hidden;padding: 0px 10px;cursor: pointer;">${bankName}</li>-->
                        <#--</#list>-->
                    <#--</#if>-->
                    <#--</ul>-->
                <#--</div>-->
                <#--<div class="w-item clearfix">-->
                    <#--<input type="text" onblur="hideOpenBankWin();" onkeyup="showOpenBankWin();" placeholder="请输入银行支行名称" id="search" name="openBankName" data-validtype="require" value="<#if bankCard??>${bankCard.openBankName}</#if>">-->
                <#--</div>-->
                <#--<div class="nice-select" style="z-index: 9999;">-->
                    <#--<ul id="selectOpenBank" style="display:none;background-color: rgb(0, 0, 0);overflow: auto;position: absolute;width: 100%;top: 10.3rem; z-index: 9999;padding: 0.2rem;height: 7rem;">-->
                    <#--</ul>-->
                <#--</div>-->
                <div class="w-item clearfix">
                    <input type="number" placeholder="请输入卡号" id="bankAccount" name="bankAccount" data-validtype="require" value="<#if bankCard??>${bankCard.bankAccount}</#if>">
                </div>
                <div class="w-item clearfix">
                    <input type="text" placeholder="请输入身份证号" name="idCard" value="<#if bankCard??>${bankCard.idCard}</#if>">
                </div>
                <#if bankCard??>
                    <button class="login" type="submit" onclick="moneyOut()">确认</button>
                    <#else >
                        <button class="login" type="submit" onclick="addBank()">提交信息</button>
                </#if>
            </div>
        </form>
        <div class="chognzhi_content">
            <p> 注 :
                <#if parameterSet.cashMoneyCount??>
                    每日提现限额10元至20000元。
                </#if>
            </p>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;提现金额小于50元，每笔将收取手续费5元。</p>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;提现金额大于等于50元，每笔将收取手续费2元。</p>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;申请提现时间为工作日上午9点至晚上16点。</p>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;到账时间以银行实际到账时间为准！</p>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;为了您的资金安全，请仔细核对银行卡信息，一旦绑定不得修改！</p>
        </div>
    </div>
</section>
<!--余额不足弹出框-->
<div class="popBox" id="moneyErrorBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd f36">
            <div class="tishi">
                <div class="tishi_text left"><i class="img"></i>提示</div>
                <div class="close pas_close" onclick="hideErrorBox()"><img src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <p class="xiadan_num xiadan_no">提现金额不可大于出余额！</p>
            <button class="sure right" onclick="hideErrorBox()">确认</button>
        </div>
    </div>
</div>

<div class="popBox" id="commissionErrorBox" style="display: none;">
    <div class="popCont">
        <div class="pop_bd f36">
            <div class="tishi">
                <div class="tishi_text left"><i class="img"></i>提示</div>
                <div class="close pas_close" onclick="hideErrorBox()"><img src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <p class="xiadan_num xiadan_no">没有佣金可以转成余额！</p>
            <button class="sure right" onclick="hideErrorBox()">确认</button>
        </div>
    </div>
</div>
<!--密码错误弹出框-->
<div class="popBox" id="passErrorBox" style="display: none" >
    <div class="popCont">
        <div class="pop_bd f36" style="height:4.8rem;">
            <div class="tishi">
                <div class="tishi_text left"><i class="img"></i>提示</div>
                <div class="close pas_close" onclick="hideBox('passErrorBox')"><img src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <p class="xiadan_num xiadan_cuo">密码输入有误，请重试。</p>
            <button class="chognshi " style="margin-left: 3rem; font-size:0.3rem;" onclick="hidePassErrorBox()">重试</button>
            <a onclick="window.location.href = '${ctxWap}/user/firstPartResetTradePassWordWhenMoneyOut'" class="forget right" style="font-size:0.3rem;">忘记密码</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/formatString.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/picker.js"></script>
<!--切换TAB -->
<script type="application/javascript" charset="utf-8">
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    var bankCard = ${bankCardStatus}
    $(function () {
        hideErrorBox();
        hidePassErrorBox();
        $("#moneyOut").css("background","#d3af30").css("color","#000").data('selected', true);
        $("#profitOut").click(function(){
            $("#profitOut").css("background","#d3af30").css("color","#000").data('selected', true);
            $("#profitOut_2").removeClass('shou_yu').addClass('shou_yu_z-active');
            //$("#moneyOut").css("background","").css("color","#959595").data('selected', false);
            $("#moneyOut").css("background","").css("color","#959595").data('selected', false);
            $("#moneyOut_2").removeClass('buy_yu').addClass('buy_yu_z-active');
            //$("#profitOut").removeClass('shou_yu').addClass('shou_yu_z-active').data('selected', true);
            //$("#moneyOut").removeClass('buy_yu').addClass('buy_yu_z-active').data('selected', false);
        })
        $("#moneyOut").click(function(){
            //$("#moneyOut").css("background","#d3af30").css("color","#000").data('selected', true);
            //$("#profitOut").css("background","").css("color","#959595").data('selected', false);

            $("#moneyOut").css("background","#d3af30").css("color","#000").data('selected', true);
            $("#moneyOut_2").removeClass('buy_yu_z-active').addClass('buy_yu');
            //$("#moneyOut").css("background","").css("color","#959595").data('selected', false);
            $("#profitOut").css("background","").css("color","#959595").data('selected', false);
            $("#profitOut_2").removeClass('shou_yu_z-active').addClass('shou_yu');
        });

        /*显示银行*/
        $("#bank").on("click",function(){
            $(document).unbind('click', hideShowSelectBank);
            $('#selectBank').toggle(function(){
                $(document).unbind('click', hideShowSelectBank);
            }, function(){
                setTimeout(function(){
                    $(document).one('click', hideShowSelectBank);
                }, 50);
            });
        });
        /*Choose a bank*/
        $(".province_i").on('click', function(){
            $("#bank").val($(this).text()).attr('readOnly', true);

        });

        /*监听支行*/
//        var mainWord = null;
//        $('#search').bind(bind_name+' paste',function(event){   //输入框的id为search，这里监听输入框的keyup事件
//            event = event || window.event;
//            event.preventDefault();
//
//            $("#selectOpenBank").show();
//            var val = $('#search').val();
//            if(val == mainWord){
//                return;
//            }
//            mainWord = val;
//            $.ajax({
//                type:"POST",     //AJAX提交方式为GET提交
//                url: ctx + "/users/getOpenBankName",   //处理页的URL地址
//                data:"mainWord="+val,   //要传递的参数
//                success:function(data){   //成功后执行的方法
//                    $("#selectOpenBank").empty();
//                    if(data != ""){
//                        for(var i=0;i<data.length;i++){
//                            $("#selectOpenBank").append('<li style="width: 100%;background: ;height: 30px;line-height: 30px; font-size: 0.2rem;" onclick="clickBank(this)">'+data[i]+'</li>')
//                        }
//                    }
//                }
//            });
//
//        });

        if(bankCard != null){
//            $("button").hide();\
            $('#withDraw input').attr('disabled', true);
        }
    });

    /*绑定银行卡*/
    function addBank() {
        var valid = $('#withDraw').addBankMessage();
        if(!valid){
            $("#errorBox").show();
            return;
        }
        var obj = $('#withDraw').obj();
        $.ajax({
            url: ctx + '/users/addBank',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                layer.msg('绑定成功！', lod());
            }
        });
    }
    function commissionToMoney(){
          var commission= parseFloat( $("#returnMoney").text(),10);
          if(commission<=0){
             $("#commissionErrorBox").show();
             return ;
          }
        $.ajax({
            url: ctx + '/users/commissionToMoney',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                layer.msg('转换成功！', lod());
            }
        });
    }

    function lod(){
        console.log(ctxWap + '/out');
        setTimeout("window.location.href = ctxWap + '/money/out'", 2000);
    }

    /*隐藏银行*/
    function hideShowSelectBank(){
        $("#selectBank").hide();
    }

    function clickBank(param) {
        $("#search").val($(param).html());
        $("#selectOpenBank").hide()
    }

    function moneyOut() {
        var valid = $('#reg_form').valid();
        if(!valid){
            return;
        }
        var obj = $('#reg_form').obj();
        obj.password = md5(obj.password);
        obj.type = $('#moneyOut').data('selected') ? 0 : 1;
        console.log(obj.type);
        $.ajax({
            url: ctx + '/money/out',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(obj),
            useDefaultError: false,
            success: function(data){
                $('#money,#password').val('');
                layer.msg("提现成功",withDrawJump());
            },
            error:function (XMLHttpRequest, textStatus, errorThrown, entity) {
                if(entity.error == '余额不足'){
                    showErrorBox();
                }else if(entity.error == '密码不正确'){
                    showPassErrorBox();
                }else{
                        layer.msg(entity.error);
                }
            }
        });
    }
    function withDrawJump(){
        /*setTimeout("window.location.href = ctxWap + '/user?='+Math.random()",1000);*/
        $.ajax({
        url: ctx + '/users/getUserMoney?tradeId=0',
            type: 'get',
            success: function(data) {
                console.log(JSON.stringify(data));
                $("#moneyNow").text(data.money);
                $("#money_now").text(data.money);
                $("#returnMoney").text(data.returnMoney);
            }
        });
    }

    function hideErrorBox() {
        $("#moneyErrorBox").hide();
        $("#commissionErrorBox").hide();
    }
    function showErrorBox() {
        $("#moneyErrorBox").show();
    }
    function hidePassErrorBox() {
        $("#passErrorBox").hide();
    }
    function showPassErrorBox(){
        $("#passErrorBox").show();
    }

    function hideBox() {
        $('.popBox:visible').hide();
    }
    
    function hideOpenBankWin() {
        $("#selectOpenBank").hide();
    }

    var showOpenBankWin_fun = null;
    function showOpenBankWin() {
        clearTimeout(showOpenBankWin_fun);
        showOpenBankWin_fun = setTimeout(function () {
            _showOpenBankWin();
        }, 500);
    }

    function _showOpenBankWin() {
        var val = $('#search').val();
        if(val == ''){
            return;
        }
        $.ajax({
            type:"POST",
            url: ctx + "/users/getOpenBankName",
            data:"mainWord="+val,
            success:function(data){
                pick(null, data, data.length == 0 ? null : data[0], function (val) {
                    $("#search").val(val);
                }).show();
            }
        });
    }
</script>
</body>
</html>