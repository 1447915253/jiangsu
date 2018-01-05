<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
<#include "/wap/header.ftl">
    <title>${title}</title>
    <link href="${ctxStatic}/all/css/xaidan.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/aa/global.css">
    <link rel="stylesheet" href="${ctxStatic}/css/aa/withdraw.css">
    <link rel="stylesheet" href="${ctxStatic}/css/aa/cd.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/aa/cd.css" />

    <script type="text/javascript"  src="${ctxStatic}/js/rem.js"></script>
    <style>
        .top_div a:before{
            content: "";
            display: inline-block;
            width: 16px;
            height: 28px;
            background:url("${ctxStatic}/img/images2/css_sprites.png");
            background-position-y:-111px;
            background-position-x:0;
            line-height:6rem;
            -webkit-transform: translateY(7px);margin-right: 0.5rem;
        }
    </style>
</head>
<body style="max-width: 768px;margin: 0 auto;overflow-x:hidden;overflow-y:auto;">
<div class="wrap"  style="background: #000c17; max-width: 768px;">
    <div class="index" style="margin-top: 0rem;">
        <form id="withDraw" class="i-form" method="post" action="#" onSubmit="return false;" style="background: #161618">
            <ul class="form-box">
                <#--<li class="f-line clearfix">
                    <label class="f-label">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
                    <input class="f-input c-1" type="text" name="province" placeholder="请输入所在省份" value="<#if bankCard??>${bankCard.province}</#if>" data-validtype="require">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line clearfix">
                    <label class="f-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
                    <input class="f-input c-1" type="text" name="city" placeholder="请输入所在城市" value="<#if bankCard??>${bankCard.city}</#if>" data-validtype="require">
                    <i class="bank_line"></i>
                </li>-->
                <#--<li class="f-line clearfix">-->
                    <#--<label class="f-label">选择银行</label>-->
                    <#--<input class="f-input c-1" type="text" id="bank" name="bankName" placeholder="请选择银行名称" value="<#if bankCard??>${bankCard.bankName}</#if>" data-validtype="require" readonly="readonly">-->
                    <#--<i class="bank_line"></i>-->
                    <#--<div class="nice-select" style="z-index: 9999;">-->
                        <#--<ul id="selectBank" style="display: none; overflow: auto; position:absolute; width: 7rem; top: 1rem; left: 76px;padding-top:5px;z-index: 9999; height: 11rem; background: #000000; font-size: 0.35rem;">-->
                        <#--<#if bankNameList??>-->
                            <#--<#list bankNameList as bankName>-->
                                <#--<li class="province_i" style="height: 30px;line-height: 30px;overflow: hidden;padding: 0px 10px;cursor: pointer;">${bankName}</li>-->
                            <#--</#list>-->
                        <#--</#if>-->
                        <#--</ul>-->
                    <#--</div>-->
                <#--</li>-->
                <#--<li class="f-line clearfix">-->
                    <#--<label class="f-label">银行支行</label>-->
                    <#--<input class="f-input c-1" onblur="hideOpenBankWin();" onkeypress="showOpenBankWin();" type="text" id="search" name="openBankName" placeholder="请输入银行支行名称" value="<#if bankCard??>${bankCard.openBankName}</#if>" data-validtype="require">-->
                    <#--<i class="bank_line" style="margin-left: 0.3rem;"></i>-->
                <#--</li>-->
                <#--<div id="searchresult" style="display: none;">-->
                <#--</div>-->
                <#--<div class="nice-select" style="z-index: 9999;">-->
                    <#--<ul id="selectOpenBank" style="display:none;background-color: rgb(0, 0, 0);overflow: auto;position: absolute;width: 100%;top: 3.5rem;left: 2rem;z-index: 9999;padding: 0.2rem;height: 7rem;">-->
                    <#--</ul>-->
                <#--</div>-->
                <li class="f-line clearfix">
                    <label class="f-label">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                    <input class="f-input c-1" type="text" name="bankAccount" placeholder="请输入借记卡卡号" value="<#if bankCard??>${bankCard.bankAccount}</#if>" data-validtype="require">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line clearfix">
                    <label class="f-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
                    <input class="f-input c-1" type="text" placeholder="请输入持卡人姓名" name="chnName" value="<#if bankCard??>${bankCard.chnName}</#if>" data-validtype="require">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line clearfix">
                    <label class="f-label">身份证号</label>
                    <input class="f-input c-1" type="text" placeholder="请输入身份证号" name="idCard" value="<#if bankCard??>${bankCard.idCard}</#if>" data-validtype="require">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line clearfix" id="inputPass" style="display: none">
                    <label class="f-label">输入密码</label>
                    <input class="f-input c-1" type="password" name="password" placeholder="请输入登录密码" data-validtype="require">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line clearfix" id="inputSamePass" style="display: none">
                    <label class="f-label">确认密码</label>
                    <input class="f-input c-1" type="password" placeholder="请再次输入登录密码"data-validtype="same">
                    <i class="bank_line"></i>
                </li>
                <li class="f-line titijiao clearfix" id="submitButton" style="display: none;">
                    <button onclick="addBankCard()">提交</button>
                </li>
            </ul>

            <div class="f-line titijiao clearfix" id="updateBankInfo" style="display: none;">
                <button>修改信息</button>
            </div>
            <div class="f-line titijiao clearfix" id="saveBankInfo" style="display: none;">
                <button onclick="updateInfo()">保存</button>
            </div>




        </form>
    </div>
</div>
<!--错误弹框-->
<div class="popBox" id="errorBox" style="display:none">
    <div class="popCont" id="popCont">
<div class="pop_bd f36" style="height:4.6rem;margin-top: 3rem;">
    <div class="tishi miss tishi_bank" style="background: ##1b1a1f;border-top-left-radius: 0.2rem;border-top-right-radius: 0.2rem;border-bottom:1px solid #2b2a2a;">
        <div class="tishi_text point bank_tishi left" ><i class="img image"></i>提示</div>
        <div class="close bank_close" style="top:0.3rem; right:0rem;" onclick="hidePrompt()"><img src="${ctxStatic}/all/image/q_03.png"></div>
    </div>
    <p class="xiadan_num xiadan_no">请填写完整信息！</p>
    <button class="sure right fill_sure" onclick="hidePrompt()">确认</button>
</div>
</div>
</div>
<div id="OpenBankWin"></div>
<#include "/wap/footer.ftl">
<script type="text/javascript" src="${ctxStatic}/js/picker.js"></script>
<script type="text/javascript">
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $(function () {
        <#if bankCard??>
            $('#withDraw input').attr('disabled', true);
            $('#updateBankInfo').attr('disabled', false).css('display', 'block');
        <#else>
            $("#inputPass").show();
            $("#inputSamePass").show();
            $('#submitButton').show();
        </#if>

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

        $(document).on('click','#updateBankInfo', function () {
            $('#withDraw input').attr('disabled', false);
            $('#updateBankInfo').css('display', 'none');
            $('#inputPass').css('display', 'block');
            $('#saveBankInfo').css('display', 'block');
            $('input[data-validtype=same]').remove();
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
//                            $("#selectOpenBank").append('<li style="width: 100%;background: ;height: 30px;line-height: 30px; font-size: 0.32rem; color: #B4B4B4;" onclick="clickBank(this)">'+data[i]+'</li>')
//                        }
//                    }
//                }
//            });
//
//        });
    });
    function addBankCard() {
        var valid = $('#withDraw').valid();
        if(!valid){
            $("#errorBox").show();
            return;
        }
        var obj = $('#withDraw').obj();
        obj.password = md5(obj.password);
        $.ajax({
            url: ctx + '/users/addBankCard',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                layer.msg('绑定成功！', function () {
                    window.location.replace(ctxWap.concat('/user?r=').concat(Math.random()+""));
                });
            }
        });
    }
    function hidePrompt() {
        $('#errorBox').hide();
    }
    function showAgreement() {
        $('#errorBox').show();
    }

    /*隐藏银行*/
    function hideShowSelectBank(){
        $("#selectBank").hide();
    }

    function updateInfo(){
        var valid = $('#withDraw').validUpdate();
        if(!valid){
            $("#errorBox").show();
            return;
        }
        var obj = $('#withDraw').obj();
        obj.password = md5(obj.password);
        $.ajax({
            url: ctx + '/users/updateBankCard',
            type: 'POST',
            data: JSON.stringify(obj),
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                $('#withDraw input').attr('disabled', true);
                $('#updateBankInfo').css('display', 'block');
                $('#inputPass').css('display', 'none');
                $('#saveBankInfo').css('display', 'none');
                $('input[name=password]').val(null);
                layer.msg("保存成功");
            }
        });
    }

    function clickBank(param) {
        $("#search").val($(param).html());
        $("#selectOpenBank").hide();
    }

    function hideOpenBankWin() {
        setTimeout(function () {
            $("#selectOpenBank").hide();
        }, 200);
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