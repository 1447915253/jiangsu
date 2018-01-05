<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1">
    <#include "/wap/header.ftl" >
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/register.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxStatic}/all/js/jquery.min.js"></script>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>银联支付</title>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
       <form id="regist_form" class="i-form">
           <div class="w-item clearfix">
               金&nbsp; 额&nbsp; |
               <input id="money" type="text" name="money" readonly="readonly" class="pwd" data-validtype="require">
           </div>
           <div class="w-item clearfix">
               持卡人姓名&nbsp; |
               <input type="text" placeholder="请输入持卡人姓名" name="cardByName" value="<#if moBaoPer??>${moBaoPer.cardByName}</#if>" class="pwd" data-validtype="require">
           </div>
           <div class="w-item clearfix">
               银行卡号&nbsp; |
               <input type="text" placeholder="请输入银行卡号" name="cardByNo" value="<#if moBaoPer??>${moBaoPer.cardByNo}</#if>" class="pwd" data-validtype="require">
           </div>
           <div class="w-item clearfix"  >
               身份证号&nbsp; |
               <input type="text" placeholder="请输入身份证号" name="cerNumber" value="<#if moBaoPer??>${moBaoPer.cerNumber}</#if>" class="text" data-validtype="require">
           </div>
           <div class="w-item clearfix"  >
               手&nbsp; 机&nbsp;号&nbsp; |
               <input type="text" placeholder="请输入手机号" name="mobile" value="<#if moBaoPer??>${moBaoPer.mobile}</#if>" class="text" data-validtype="require">
           </div>
        </form>
        <button class="button" onclick="getSms(this)">获取验证码</button>
        <div>
            <p>注意：当快捷支付失败时，大多是因为未开通认证支付，原因为您的银行卡未开通快捷支付功能，您可以通过以下方式之一解决：</p>
            <p>1、拨打银联客服电话95516开通；</p>
            <p>2、拨打发卡行客服电话开通；</p>
            <p>3、下载银联钱包，注册绑定借记卡后即自动开通；</p>
            <p>4、登录下方银联钱包官网开通(手机无法开通)</p> <a>https://www.95516.com/static/union/pages/card/openFirst.html?entry=openPay</a>
        </div>
    </div>
</section>
<script language="javascript" type="text/javascript">

    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var money = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
    //alert(money);
    $("#money").val(money);
    //document.write(id)

    function getSms(the) {
        var money=$('input[name="money"]').val();
        var mobile = $('input[name="mobile"]').val();
        var cardByName = $('input[name="cardByName"]').val();
        var cardByNo = $('input[name="cardByNo"]').val();
        var cerNumber = $('input[name="cerNumber"]').val();
        if (cardByName == "" || cardByNo == "" || cardByNo == "" || cerNumber =="") {
            alert("请输入完整信息！");
            //$('.layui-m-layer').css('z-index','99999999999999');
            return;
        }
        if (!/^[1-9]{1}[0-9]{10}$/.test(mobile)) {
            alert("手机号不合法");
            //$('.layui-m-layer').css('z-index','99999999999999');
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: ctx + '/money/moBaoUnionPay',
            data: JSON.stringify({
                money:money,
                mobile: mobile,
                cardByName:cardByName,
                cardByNo:cardByNo,
                cerNumber:cerNumber
            }),
            contentType: "application/json",
            error: function(data) {
                //layer.closeAll();
                console.log(data);
                alert("获取验证码失败,操作过于频繁或填写信息错误,请确认信息后再试！")
            },
            success: function(data) {
                //layer.closeAll();
                //alert("获取验证码成功!")
                window.location.href=ctxWap+'/pay/moBaoUnionPaycode?orderId='+data;
            }
        });

    }
</script>

</body>
</html>