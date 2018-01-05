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
    <script language="javascript" type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
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
<body style="width100%;height:100%;margin: 0 auto; overflow: hidden;position:relative;">
<!--顶部开始-->
<#--<div class="top_div" style="max-width: 768px;" id="myteam_top">-->
        <#--<a style="display: inline-block;float: left;height: 5rem;line-height: 5rem;color: #fff;margin-left: 1rem;font-size: 18px;" href="javascript:window.history.back();">返回</a>-->
        <#--<h1 style="margin-right:5.6rem">我的军团</h1>-->
<#--</div>-->
<!--顶部结束-->
<!-- mian开始 -->
<div class="min" style="width: 100%;height: 25rem; overflow-y:auto;position:absolute;">
	    <div class="team_login">
	       <div class="profit_header" style="background-color:#161618">
	       		<ul class="profit_header_u1">
	       			<li>已结算的收益</li>
	       			<li>未结算的收益</li>
	       		</ul>
	       		<ul class="profit_header_u2">
	       			<li id="li1">${user.returnMoneyTotal-user.returnMoney}</li>
	       			<li id="li2">${user.returnMoney}</li>
	       		</ul>
	       </div>
	       <div class="profit_main">

	       		<div id="fitouter">
				    <ul id="fittab">
				        <li class="profitcurrent" onclick="showMoney('all');" style="background: #161618">全部</li>
				        <li onclick="showMoney('today');" style="background: #161618">今天</li>
				        <li onclick="showMoney('week');" style="background: #161618">一周</li>
				        <li onclick="showMoney('month');" style="background: #161618">当月</li>
				    </ul>
				    <div id="fitcontent" style="background: #161618"><!--包住4个DIV-->
				        <div id="all_div" style="display:block;"><!--下面整体的DIV1-->
				            <ul class="fitcontent_header yingdesy">
				           		<li>战队</li>
				           		<li>收益比例</li>
				           		<li>交易总额</li>
				           		<li>应得收益</li>
				            </ul>
				        </div>
				        <div id="today_div"><!--下面整体的DIV2-->
				            <ul class="fitcontent_header">
				           		<li>军团</li>
				           		<li>收益比例</li>
				           		<li>交易总额</li>
				           		<li>应得收益</li>
				           </ul>
				        </div>
				        <div id="week_div"><!--下面整体的DIV3-->
				            <ul class="fitcontent_header">
				           		<li>战队</li>
				           		<li>收益比例</li>
				           		<li>交易总额</li>
				           		<li>应得收益</li>
				           </ul>
				        </div>
				        <div id="month_div"><!--下面整体的DIV4-->
				            <ul class="fitcontent_header">
				           		<li>战队</li>
				           		<li>收益比例</li>
				           		<li>交易总额</li>
				           		<li>应得收益</li>
				           </ul>
				        </div>
                        <div id="week_div"><!--下面整体的DIV3-->
                            <ul class="fitcontent_header">
                                <li>战队</li>
                                <li>收益比例</li>
                                <li>交易总额</li>
                                <li>应得收益</li>
                            </ul>
                        </div>
                        <div id="month_div"><!--下面整体的DIV4-->
                            <ul class="fitcontent_header">
                                <li>战队</li>
                                <li>收益比例</li>
                                <li>交易总额</li>
                                <li>应得收益</li>
                            </ul>
                        </div>
                        <div id="week_div"><!--下面整体的DIV3-->
                            <ul class="fitcontent_header">
                                <li>战队</li>
                                <li>收益比例</li>
                                <li>交易总额</li>
                                <li>应得收益</li>
                            </ul>
                        </div>
                        <div id="month_div"><!--下面整体的DIV4-->
                            <ul class="fitcontent_header">
                                <li>战队</li>
                                <li>收益比例</li>
                                <li>交易总额</li>
                                <li>应得收益</li>
                            </ul>
                        </div>
				    </div>
				</div>

	       </div>
    	</div>
</div><!--597 x 880-min-->
<#include "/wap/footer.ftl">
	<script>
		var _percent3 = ${_percent3};
		$(function(){
			window.onload = function()
			{
				var $li = $('#fittab li');
				var $ul = $('#fitcontent>div');
							
				$li.mouseover(function(){
					var $this = $(this);
					var $t = $this.index();
					$li.removeClass();
					$this.addClass('profitcurrent');
					$ul.css('display','none');
					$ul.eq($t).css('display','block');
				})
			};

            showMoney('all');
		});

		function showMoney(type) {
			$.ajax({
				url: ctx + '/corps/profit/'+type,
				type: 'get',
				success:function (it) {
					var _arr = [];
                    _arr.push('<ul class="fitcontent_header">');
                    _arr.push('<li>战队</li>');
                    _arr.push('<li>收益比例</li>');
                    _arr.push('<li>交易总额</li>');
                    _arr.push('<li>应得收益</li>');
                    _arr.push('</ul>');
                    _arr.push('<ul>');
/*                    _arr.push('<li>炮兵团</li>   ');
                    _arr.push('<li>'+decimalAfter2(_percent3.percentPaoBing*100)+'%</li>');
                    _arr.push('<li>'+it.paoBingMoneyTotal+'</li>');
                    _arr.push('<li>'+it.paoBingMoney+'</li>  ');
                    _arr.push('</ul>            ');
                    _arr.push('<ul>             ');*/
                    _arr.push('<li>亲兵队</li>   ');
                    _arr.push('<li>'+decimalAfter2(_percent3.percentQiBing)+'%</li>');
                    _arr.push('<li>'+it.qiBingMoneyTotal+'</li>');
                    _arr.push('<li>'+it.qiBingMoney+'</li>  ');
                    _arr.push('</ul>            ');
                    _arr.push('<ul>             ');
                    _arr.push('<li>护卫队</li>   ');
                    _arr.push('<li>'+decimalAfter2(_percent3.percentBuBing)+'%</li>');
                    _arr.push('<li>'+it.buBingMoneyTotal+'</li>');
                    _arr.push('<li>'+it.buBingMoney+'</li>  ');
                    _arr.push('</ul>            ');
					$('#'+type+'_div').html(_arr.join(''));
                }
			});
        }
	</script>

</body>
</html>