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
    <style type="text/css">
        .loading{
            width:160px;
            height:56px;
            position: absolute;
            top:30%;
            left:30%;
            line-height:56px;
            color:#fff;
            padding-left:60px;
            font-size:15px;
            background: #000 url(http://img.my.csdn.net/uploads/201211/14/1352886927_7549.gif) no-repeat 10px 50%;
            opacity: 0.7;
            z-index:9999;
            -moz-border-radius:20px;
            -webkit-border-radius:20px;
            border-radius:20px;
            filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);
        }
    </style>
</head>
<body>
<section class="wrap-page">
    <div id="loading" class="loading" hidden="hidden">正在支付中...</div>
    <div class="user_top">
        <form id="regist_form" class="i-form">
            <div class="w-item clearfix">
                验&nbsp; 证&nbsp;码&nbsp; |
                <input id="yzm" type="text" placeholder="请输入验证码" name="yzm" class="pwd" data-validtype="require">
            </div>
        </form>
        <button class="button" onclick="completePay(this)">完成支付</button>
        <div>
            <br>
            <p>当快捷支付失败时，大多是因为未开通认证支付，原因为您的银行卡未开通快捷支付功能，您可以通过以下方式之一解决：</p>
            <p>1、拨打银联客服电话95516开通；</p>
            <p>2、拨打发卡行客服电话开通；</p>
            <p>3、下载银联钱包，注册绑定借记卡后即自动开通；</p>
            <p>4、登录下方银联钱包官网开通(手机无法开通)</p> <a>https://www.95516.com/static/union/pages/card/openFirst.html?entry=openPay</a>
        </div>
    </div>
</section>

<script language="JavaScript" type="text/javascript">
    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var orderId = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
    //alert(orderId);



    function completePay(the) {
        var yzm=$('input[name="yzm"]').val();

        if (yzm == "") {
            alert("请输入验证码！");
            return;
        }
        $('input').attr("readonly",true);
        $("#loading").show();
        $.ajax({
            cache: true,
            type: "POST",
            url: ctx + '/money/moBaoUnionCodePay',
            data: JSON.stringify({
                yzm:yzm,
                orderId:orderId
            }),
            contentType: "application/json",
            error: function(data) {
                //layer.closeAll();
                //console.log(data);
                alert("支付失败！")
                $("#loading").hide();
                window.location.href=ctxWap+'/user';
            },
            success: function(data) {
                //layer.closeAll();
                alert("支付成功!")
                $("#loading").hide();
                window.location.href=ctxWap+'/user';
            }
        });

    }
</script>
</body>
</html>