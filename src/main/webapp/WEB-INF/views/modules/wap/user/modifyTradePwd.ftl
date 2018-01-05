<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl">
    <#assign footer = "user"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xiugaipasw.css" rel="stylesheet" type="text/css"/>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
        <form id="data_div">
            <div class="w-item clearfix">
                当前密码&nbsp;&nbsp;  <i class="pas_line"></i>&nbsp;
                <input type="password" placeholder="输入原密码" name="preTradePassword" class="pwd" required>
            </div>
            <div class="w-item clearfix">
                新 密 码&nbsp;&nbsp;  <i class="pas_line"></i>&nbsp;
                <input type="password" placeholder="请输入6-20字母和数字组合" name="tradePassword" class="pwd" required>
            </div>
            <div class="w-item clearfix">
                确认密码&nbsp;&nbsp;  <i class="pas_line"></i>&nbsp;
                <input type="password" placeholder="请再次输入密码" data-validtype="same" class="pwd" required >
            </div>
        </form>
        <button class="login" type="submit" onclick="_do()">确认密码</button>
    </div>
</section>
<#include "/wap/footer.ftl">
<script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
<script type="text/javascript">
    function _do() {
        var v = $('#data_div').valid();
        if(v){
            var obj = $('#data_div').obj();
            $.ajax({
                url: ctx + '/users/modifyTradePwd',
                type: 'PUT',
                data: JSON.stringify(obj),
                contentType: 'application/json;charset=UTF-8',
                success: function(data){
                    layer.msg('', function(){
                        window.location.href = ctxWap + '/user?'+Math.random();
                    })
                }
            });
        }
    }
    function setPwd() {
        $('input[name="password"]').val(md5($('#password').val()));
    }
</script>
</body>
</html>