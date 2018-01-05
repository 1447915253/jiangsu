<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
<#include "/wap/header.ftl">
<#assign footer = "corps"/>
    <title>${title}</title>
    <link rel="shortcut icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/cd.css" />
    <link rel="stylesheet" href="${ctxStatic}/css/global.css">
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/base.css">
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
	.menus ul li:nth-child(4) a{
		color: #FF8400;
	}
	.legion:before{
		background: url("${ctxStatic}/img/images2/legion/dph.png") no-repeat;
	}
  </style>
  <link rel="stylesheet" href="${ctxStatic}/css/global.css">
</head>
<body style="max-width: 768px;margin: 0 auto;overflow: hidden;">
<!--顶部开始-->
<#--<div class="top_div" style="max-width: 768px;" id="myteam_top">-->
        <#--<a style="display: inline-block;float: left;height: 5rem;line-height: 5rem;color: #fff;margin-left: 1rem;font-size: 18px;" href="myteam.html">返回</a>-->
        <#--<h1 style="margin-right:5.6rem">我的军团</h1>-->
<#--</div>-->
<!--顶部结束-->
<!-- mian开始 -->
<div class="min" style="width: 100%;">
<div class="team_login">
	<form id="reg_form" class="i-form"  method="post" action="#"  onsubmit="return  false;">
		<div class="reg_main">
			<div class="reg_box">
				<#--<div class="item">
					<input type="text" placeholder="请输入您的手机号码" name="regRuser"  id="regRuser" maxlength="11" class="input input2">
				</div>
				<div class="item">
					<input type="text" placeholder="请输入您的真实姓名" name="Ruser"  id="Ruser" class="input input1">
				</div>-->
<#--				<div class="item">
					<input type="text" placeholder="请输入你的手机号" maxlength="18" name="idCard" data-validtype="require">
				</div>
				<div class="item">
					<input type="text" placeholder="请输入您的真实姓名" name="tradePassword" maxlength="12" class="input input4" data-validtype="require">
				</div>-->
                    <div class="item">
                        <input type="text" placeholder="请输入18位身份证号" maxlength="18" name="idCard" data-validtype="require">
                    </div>
                    <div class="item">
                        <input type="password" placeholder="请输入您的交易密码" name="tradePassword" data-validtype="require">
                    </div>
<#--                    <div class="w-item huoqu clearfix">
                            <input type="text" placeholder="请输入短信验证码" name="pwd" class="pwd" required="">
                        <input class="greena right" type="button" id="btn" value="获取验证码">
                    </div>-->
				<#--<div class="item2">
					<input type="text" placeholder="请输入验证码" name="verNum" id="verNum" maxlength="6" class="input input3">
					<button id="btnSendCode" class="btn2" onClick="getVerify()">获取验证码</button>
				</div>-->
			</div>
			<div class="btnbox"><input type="button" value="创建完成" class="btnu" id="legionsub" onclick="createCorps()"></div>
		</div>
	</form>
</div>
</div><!--597 x 880-->
<!--表单验证   AJAX-->

 <script type="application/javascript" charset="utf-8">
	 function createCorps() {
		 var valid = $('#reg_form').valid();
		 if(!valid){
			 return;
		 }
         var obj = $('#reg_form').obj();
         obj.tradePassword = md5(obj.tradePassword);
		 $.ajax({
			 url: '${ctx}/corps/create',
             contentType: 'application/json;charset=UTF-8',
			 type:'POST',
			 data:JSON.stringify(obj),
			 success: function(){
				 window.location.replace('${ctxWap}/corps?r='+Math.random());
			 }
		 });
     }
</script>
<!-- main结束 -->
<#include "/wap/footer.ftl">
</html>