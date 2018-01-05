<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/denglu.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">

    <div class="user_top" style="height: 14rem;"><#--18.5rem;-->
        <div class="login_name"></div>
        <form id="login_valid" action="${ctxWap}/login" METHOD="post" enctype="application/x-www-form-urlencoded" class="login_b">
            <div class="w-item clearfix">
                账 &nbsp;&nbsp;户&nbsp; <i class="login_line"></i>
                <input type="text" placeholder="请输入手机号" name="mobile" id="mobile" class="pwd" data-validtype="require" value="<#if mobile??>${mobile}</#if>">
            </div>
            <div class="w-item clearfix">
                密 &nbsp;&nbsp;码&nbsp; <i class="login_line"></i>
                <input type="password" onblur="setPwd()" placeholder="请输入密码" id="password" name="pwd" class="pwd" data-validtype="require">
                <input type="hidden" name="terminal" value="${request.getParameter("terminal")!3}"/>
                <input type="hidden" name="password" value=""/>
            </div>
            <div class="w-item w-item tianxie clearfix">
                <div class="w-item yanzhen clearfix">
                    验证码&nbsp; <i class="login_line"></i>
                    <input type="text" placeholder="请输入验证码" id="randomCode" name="randomCode" class="pwd" data-validtype="require">
                </div>
                <#--<input class="fill right" type="button" id="btn" value="R T  Q I">-->
                <img style="height:1.3rem;border-radius:5px 5px 5px 5px;right -100px;width: 1.5rem; float: right;" id="randomCodeImage" src="${ctx}/validate" onclick="fresh()"/>
            </div>
            <div class="fuxuan clearfix">
                <div class="left checkbox_num">
                    <input type="checkbox" class="checkbox" id="check_id"><span>记住账号</span>
                </div>
                <div class="right f" onclick="resetTradePass()">忘记密码？</div>
            </div>
            <button onclick="submitForm()">立即登录</button>
            <#--<p class="text-center">还没有账户？<span onclick="window.location.href = '${ctxWap}/reg'">立即注册</span></p>
            <p style="color: grey; line-height: 0.5rem; font-size: 0.4rem;">

            </p>-->
        </form>
    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script src="${ctxStatic}/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript">
    <#if errors??>
    layer.msg('${errors.error}');
    </#if>
    <!--记住账号cookie-->
    $(document).ready(function () {
        if ($.cookie("rmbUser") == "true") {
            $("#check_id").attr("checked", true);
            $("#mobile").val($.cookie("username"));
        }
    });
    function resetTradePass() {
        var mobile = $("#mobile").val();
        var parttern = /^[0-9]{11}$/;
        if(parttern.test(mobile)){
            window.location.replace('${ctxWap}/firstPartResetTradePassWord?mobile='+mobile)
        }else{
            layer.msg("手机号不合法");
        }
    }
    function submitForm() {
        if(login() == true){
            //记住用户名密码
            if (document.getElementById('check_id').checked == true) {
                var str_username = $("#mobile").val();
                $.cookie("rmbUser", "true", { expires: 1 }); //存储一个带7天期限的cookie
                $.cookie("username", str_username, { expires: 1 });
            }
            else {
                $.cookie("rmbUser", "false", { expire: -1 });
                $.cookie("username", "", { expires: -1 });
            }
            document.getElementById('login_valid').submit();
        }
    }

    function login(){
        if(!$('#login_valid').valid()){
            return false;
        }
        return true;
    }

    function fresh() {
        var imgSrc = $("#randomCodeImage");
        var src = imgSrc.attr("src");
        imgSrc.attr("src",chgUrl(src));
    }

    function chgUrl(u) {
        var timestamp = (new Date()).valueOf();
        url = u.substring(0,17);
        if((u.indexOf("&")>=0)){
            url = u + "×tamp=" + timestamp;
        }else{
            url = u + "?timestamp=" + timestamp;
        }
        return url;
    }

    function setPwd() {
        $('input[name="password"]').val(md5($('#password').val()));
    }
</script>
</body>
</html>