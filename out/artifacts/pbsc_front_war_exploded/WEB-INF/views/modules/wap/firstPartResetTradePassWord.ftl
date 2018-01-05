<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <#assign footer = "home"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/wangji.css" rel="stylesheet" type="text/css"/>
    <!--<link href="all/css/footer.css" rel="stylesheet" type="text/css"/>-->
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
        <p>为了您的账户安全，请先进行身份验证已绑定手机：<br><span id="mobile">${mobile}</span></p>
        <form id="data_div">
            <div class="w-item tianxie clearfix">
                <input class="copy left" type="text" id="validCode" name="validCode" placeholder="填写验证码">
                <input class="fill right" type="button" id="btn" value="获取验证码" onclick="sendSms(this)">
            </div>
        </form>
        <button class="login" type="submit" onclick="_do()">下一步</button>
    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript">
    $(function() {
        var phone = $("#mobile").text();
        var mphone = phone.replace(/^(\d{3})\d{4}(\d+)/,"$1****$2");
        $("#mobile").text(mphone)
    });
    function sendSms(the) {
        sendSMSValidCode('mtpwd', '${mobile}', function(data){
            settime(the);
        });
    }
    function _do() {
        //var v = $('#data_div').valid();
            //var obj = $('#data_div').obj();
            var validCode = $("#validCode").val();
            $.ajax({
                url: ctx + '/users/firstPartResetTradePassWord',
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    "mobile": '${mobile}',
                    "validCode": validCode
                }),
                success: function(data){
                    layer.msg('', function(){
                        window.location.href = ctxWap + '/secondPartResetTradePassWord?mobile='+'${mobile}'+'&validCode='+validCode;
                    })
                }
            });
    }
</script>
</body>
</html>