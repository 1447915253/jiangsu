<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <#assign footer = "user"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xiugaipasw.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/chognzhipas.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
        <form id="data_div">
            <div class="w-item clearfix">
                新交易密码&nbsp; |
                <input type="password" placeholder="请输入6-20字母和数字组合" id="tradePassword" name="tradePassword" class="pwd" data-validtype="require">
            </div>
            <div class="w-item clearfix">
                确&nbsp;认&nbsp;密&nbsp;码&nbsp; |
                <input type="password" placeholder="请再次输入密码" data-validtype="same" class="pwd">
            </div>
        </form>
        <button class="login" type="submit" onclick="_do()">确认</button>
    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript">
    function _do() {
        var v = $('#data_div').valid();
        if(v){
            var obj = $('#data_div').obj();
            $.ajax({
                url: ctx + '/users/secondPartResetTradePassWord',
                type: 'POST',
                data: JSON.stringify({
                    "mobile": '${mobile}',
                    "validCode":'${validCode}',
                    "tradePassword": obj.tradePassword
                }),
                contentType: 'application/json;charset=UTF-8',
                success: function (data) {
                    layer.msg('', function () {
                        window.location.href = ctxWap + '/login?' + Math.random();
                    })
                }
            });
        }
    }
</script>
</body>
</html>