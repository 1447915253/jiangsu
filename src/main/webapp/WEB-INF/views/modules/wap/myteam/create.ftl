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
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/cd.css" />
    <link rel="stylesheet" href="${ctxStatic}/css/global.css">
    <link rel="stylesheet" href="${ctxStatic}/css/base.css">
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
<body style="max-width: 768px;margin: 0 auto;position:relative;">
<!--顶部开始-->
<#--<div class="top_div" style="max-width: 768px;" id="myteam_top">-->
        <#--<a style="display: inline-block;float: left;height: 5rem;line-height: 5rem;color: #fff;margin-left: 1rem;font-size: 18px;" href="javascript:window.history.back();">返回</a>-->
        <#--<h1 style="margin-right:5.6rem">我的军团</h1>-->
<#--</div>-->
<!--顶部结束-->
<#include "/wap/footer.ftl">
<!-- mian开始 -->
<div class="min" style="width: 100%;height: 100%;overflow-y: auto;position: absolute;">
	    <div class="team_login" >
	    	<p class="create_banner">
	    		<img src="${ctxStatic}/img/images2/legion/legionbanner.png" alt="" style="width:100%;height:100%">
	    	</p>
	    	<div class="create_class">
	    		<ul class="create_class_u1">
	    			<li>军团层级</li>
	    			<li>炮兵团</li>
	    			<li>骑兵团</li>
	    			<li>步兵团</li>
	    		</ul>
	    		<ul class="create_class_u2">
	    			<li>应得收益</li>
	    			<li>${percent3.percentPaoBing*100}%</li>
	    			<li>${percent3.percentQiBing*100}%</li>
	    			<li>${percent3.percentBuBing*100}%</li>
	    		</ul>
	    	</div>
	    	<div class="create_welcome">
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎进入快赢宝微交易“军团决胜计划”，想要真正做到边玩边赚， 你只需要组建属于自己军团，抱团迎战！当你的战队人数不断增加你获得的收益绝对超乎你的想象！现在就点击加入军团了解“军团决胜计划”的收益规则，去实现那终将逝去的创业梦吧。
	    	</div>
	    	<p class="create_sure"><a href="${ctxWap}/corps/create">创建军团</a></p>
    	</div>
</div><!--597 x 880-->
<!-- main结束 -->
<#include "/wap/footer.ftl">
</body>
</html>