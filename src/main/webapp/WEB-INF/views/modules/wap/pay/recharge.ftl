<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/weixinchongzhi.css?v=1.1" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
    <#--<title>彭博商城</title>-->
    <style>
        .pwd{
            background: #000c17;
            color: #bbb;
            width: 2.8rem;
            position: relative;
            top: -0.06rem;
        }
    </style>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
    <#-- <p>充值规则：</p>
     <div class="chognzhi_content">
         <p> &nbsp;&nbsp; &nbsp;&nbsp;单笔入金最低20，</p>
         <p> &nbsp;&nbsp; &nbsp;&nbsp;单笔最高5000，单日最高5万；</p>
         <p> &nbsp;&nbsp; &nbsp;&nbsp;入金时间必须为交易时间，</p>
         <p> &nbsp;&nbsp; &nbsp;&nbsp;如有变动，以交易中心公告为准；</p>-->
        <div class="ad">
            <img class="images" src="${ctxStatic}/all/image/cz_ad.jpg">
        </div>
    </div>
    <div class="prompt">
        <strong class="text-red" style="font-size:0.2rem;">* 注意：充值将收取2%的手续费</strong>
    </div>
    <div class="kkk" style="background: #1e1e1e; padding-bottom: 0.3rem">
        <div class="d_money">到账金额 :
            <span style="color: red">&yen;</span>
            <input type="text" id="zhenmoeny" placeholder="" name="v_amount" style="background: #161618;font-size:0.4rem;margin-top:0.16rem" class="pwd" required readonly>
        </div>
        <!--首信易银联-->
    <#--        <form id="yinLianForm" action="https://api.teegon.com/charge/pay" method="post" style="display: none;">
                <input type="text" name="order_no" value="">
                <input type="text" name="channel" value="">
                <input type="text" name="amount" value="">
                <input type="text" name="subject" value="">
                <input type="text" name="metadata" value="">
                <input type="text" name="client_ip" value="">
                <input type="text" name="return_url" value="">
                <input type="text" name="notify_url" value="">
                <input type="text" name="sign" value="">
                <input type="text" name="client_id" value="">
            </form>-->

        <!--98支付快捷银联-->
<#--        <form id="nineeightYinLianForm" action="http://pay.shopping98.com/pay/gateway.shtml" method="post" style="display: none;">
            <input type="text" name="out_trade_no" id="out_trade_no" value=""  type="hidden"/><br/>
            <input type="text" name="order_time" id="order_time" value="" type="hidden"/><br/>
            <input type="text" name="total_fee" id="total_fee" value="12" type="hidden"/><br/>
            <input type="text" name="body" id="body" value="快捷支付"type="hidden" /><br/>
            <input type="text" name="time_expire" id="time_expire" value="30" type="hidden"/><br/>
            <input type="text" name="client_ip" id="client_ip" value="127.0.0.1" type="hidden"/><br/>
            <input type="text" name="notify_url" id="notify_url" value="http://rrb.guantuanwang.com/front/v1/nineeight/pay/callback" type="hidden"/><br/>
            <input type="text" name="return_url" id="return_url" value="http://www.baidu.com" type="hidden" /><br/>
            <input type="text" name="device_info" id="device_info" value="" type="hidden"/><br/>
            <input type="text" name="attach" id="attach" value="" type="hidden"/><br/>
        </form>-->

        <!--58微信网关模式openPay-->
        <form id="fiveeightWeixinForm" action="http://www.jzmti.com/Manage/Wxcashier/foreverpay.html" method="post" style="display: none;">
            <input type="text" name="userId" id="userId" value=""  type="hidden"/><br/>
            <input type="text" name="tprice" id="tprice" value="" type="hidden"/><br/>
            <input type="text" name="tname" id="tname" value=""  type="hidden"/><br/>
            <input type="text" name="attach" id="attach" value="" type="hidden"/><br/>
            <input type="text" name="attachOrderId" id="attachOrderId" value="" type="hidden"/><br/>
            <input type="text" name="jumpUrl" id="jumpUrl" value="" type="hidden"/><br/>
        </form>

        <div class="pa_yc clearfix">
            <span style="font-size: 0.4rem;">选择充值金额(元)</span>
            <ul class="pa_y clearfix">
               <#-- <li id="m0" >10.0元</li>-->
                <li id="m1" >158元</li>
                <li id="m2" >200元</li>
                <li id="m3" >300元</li>
                <li id="m4" >500元</li>
                <li id="m5" >1000元</li>
                <li id="m6" >2000元</li>
                <li id="m7" >3000元</li>
                <li id="m8" >4000元</li>
                <li id="m9" >5000元</li>
        <!--        <li class="other" ><input type="number" id="inputMoeny" onfocus="if(this.value=='')this.placeholder='';" onblur="if(this.value=='')this.placeholder='其它金额';" placeholder="其它金额"></li> -->
            </ul>
        </div>
        <div class="w-item tianxie clearfix">

            <span style="font-size: 0.4rem;padding-left:0.3rem;display: block">请选择支付方式</span>
           <#-- <div class="copy left"  style="position: relative" onclick="setType('4');">
                <a class="showPic4" style="padding-left: 1.3rem;" ><span id="weixinOpenPay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy right"  style="position: relative" onclick="setType('6');">
                <a class="showPic6" style="padding-left: 1.3rem;" ><span id="shanDePay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信支付2</a>
                <span class="mchecked"></span>
            </div></br>
            <div class="copy right"  style="position: relative" onclick="setType('7');">
                <a class="showPic7" style="padding-left: 1.3rem;" ><span id="shanDePagePay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信扫码支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy left"  style="position: relative" onclick="setType('8');">
                <a class="showPic8" style="padding-left: 1.3rem;" ><span id="shanDeAlipayPagePay" class="tianxie_tu weixin" style="/*left: 0.3rem;*/"></span>支付宝扫码支付</a>
                <span class="mchecked"></span>
            </div>-->

            <#--<div class="copy right"  style="position: relative" onclick="setType('9');">
                <a class="showPic9" style="padding-left: 1.3rem;" ><span id="baiShiWeChatScanPay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信扫码支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy left"  style="position: relative" onclick="setType('14');">
                <a class="showPic14" style="padding-left: 1.3rem;" ><span id="shanDePay2" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信支付</a>
                <span class="mchecked"></span>
            </div><br>-->
            <#--<div class="copy left"  style="position: relative" onclick="setType('10');">
                <a class="showPic10" style="padding-left: 1.3rem;" ><span id="baiShiALiScanPay" class="tianxie_tu weixin" style="/*left: 0.3rem;*/"></span>支付宝扫码支付2</a>
                <span class="mchecked"></span>
            </div>-->
            <#--<div class="copy left"  style="position: relative" onclick="setType('11');">
                <a class="showPic11" style="padding-left: 1.3rem;" ><span id="baiShiWeChatPubPay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信支付</a>
                <span class="mchecked"></span>
            </div>-->


            <#--<div class="copy left"  style="position: relative" onclick="setType('12');">
                <a class="showPic12" style="padding-left: 1.3rem;" ><span id="moBaoUnionPay" class="tianxie_tu kuaijie-active" style="/*left: 0.3rem;*/"></span>银联支付</a>
                <span class="mchecked"></span>
            </div>-->
            <#--<div class="copy left"  style="position: relative" onclick="setType('13');">
                <a class="showPic13" style="padding-left: 1.3rem;" ><span id="xinWebScanPay" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信扫码支付2</a>
                <span class="mchecked"></span>
            </div>-->
            <#--<div class="copy right"  style="position: relative" onclick="setType('15');">
                <a class="showPic15" style="padding-left: 1.3rem;" ><span id="shanDePagePay2" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信扫码支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy left"  style="position: relative" onclick="setType('16');">
                <a class="showPic16" style="padding-left: 1.3rem;" ><span id="shanDeAlipayPagePay2" class="tianxie_tu weixin" style="/*left: 0.3rem;*/"></span>支付宝扫码支付2</a>
                <span class="mchecked"></span>
            </div>-->
            <#--<div class="copy left"  style="position: relative" onclick="setType('17');">
                <a class="showPic17" style="padding-left: 1.3rem;" ><span id="shanDeJDPagePay2" class="tianxie_tu jingdong-active" style="/*left: 0.3rem;*/"></span>京东扫码支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy right" style="position: relative" onclick="setType('18');">
                <a class="showPic18" style="padding-left: 1.3rem;"><span id="shanDeQQScanPay" class="tianxie_tu qqmoney" style=""></span>qq钱包扫码支付</a>
                <span class="mchecked"></span>
            </div>-->
          <#--  <div class="copy left"  style="position: relative" onclick="setType('18');">
                <a class="showPic18" style="padding-left: 1.3rem;" ><span id="qianHongPagePay" class="tianxie_tu qqmoney" style="/*left: 0.3rem;*/"></span>立即支付</a>
                <span class="mchecked"></span>
            </div>-->
            <div class="copy left"  style="position: relative" onclick="setType('19');">
                <a class="showPic19" style="padding-left: 1.3rem;" ><span id="guojiPagePay" class="tianxie_tu weixin" style="/*left: 0.3rem;*/"></span>支付宝支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy right"  style="position: relative" onclick="setType('21');">
                <a class="showPic21" style="padding-left: 1.3rem;" ><span id="xionvxPagePay" class="tianxie_tu qqmoney" style="/*left: 0.3rem;*/"></span>微信支付</a>
                <span class="mchecked"></span>
            </div>
            <#--<div class="copy left"  style="position: relative" onclick="setType('20');">
                <a class="showPic20" style="padding-left: 1.3rem;" ><span id="huizhitongPagePay" class="tianxie_tu qqmoney" style="/*left: 0.3rem;*/"></span>汇智通支付</a>
                <span class="mchecked"></span>
            </div>-->

<#--           <div class="copy left"  style="position: relative" onclick="setType('5');">
                <a class="showPic5" style="padding-left: 1.3rem;" ><span id="weipengH5" class="tianxie_tu yinlian-active" style="/*left: 0.3rem;*/"></span>微信支付</a>
                <span class="mcHhecked"></span>
            </div>-->
<#--            <div class="copy right" style="position: relative" onclick="setType('2');">
                <a class="showPic2" style="padding-left: 1.3rem;"><span id="quickPay" class="tianxie_tu kuaijie" style=""></span>快捷支付</a>
                <span class="mchecked"></span>
            </div></br>
            <div class="copy right active" style="position: relative" onclick="setType('0');">
                <a class="showPic0"  style="padding-left: 1.3rem;"><span id="weixin" class="tianxie_tu weixin" style=""></span>支付宝支付</a>
                <span class="mchecked"></span>
            </div>
            <div class="copy left"  style="position: relative" onclick="setType('1');">
                <a class="showPic1" ><span id="yinlian" class="tianxie_tu yinlian-active" style="left: 0.3rem;"></span>微信支付</a>
                <span class="mchecked"></span>
            </div></br>
            <div class="copy left" style="position: relative" onclick="setType('3');">
                <a class="showPic3" style="padding-left: 1.3rem;"><span id="qqPay" class="tianxie_tu qqmoney" style=""></span>qq钱包支付</a>
                <span class="mchecked"></span>
            </div>-->
        <#--<div class="fill right">
            <a href="javascript:setType('0');"><span id="weixin" class="tianxie_tu weixin"></span>微信支付</a>
        </div>-->
        <#--   <#elseif terminal == '1' >-->

        <#--                <div class="fill right">
                            <a href="javascript:setType('2');"><span id="weixin" class="tianxie_tu weixin"></span>易捷付</a>
                        </div>-->
        <#--  <#else>-->
        <#-- <div class="copy left" >
             <a href="javascript:setType('1');"><span id="yinlian" class="tianxie_tu yinlian-active" style="left: 0rem;"></span>银联支付</a>
         </div>-->
        <#--                <div class="fill right">
                            <a href="javascript:setType('3');"><span id="weixin" class="tianxie_tu weixin"></span>易捷付</a>
                        </div>-->
        <#-- </#if>-->
        </div>
        <button class="login" type="submit" onclick="toPay(payType)" style="margin-bottom: 0.3rem">确认充值</button>
    <#--<button class="login" type="submit" onclick="tongLianToRecharge()" style="margin-bottom: 0.3rem">确认充值</button>-->
        <p style="text-align: center;font-size: 0.4rem">每笔上限5000元，每日上限50000元</p>

    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script src="${ctxStatic}/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/js/modules/pay/recharge.js?v=5"></script>


<script type="text/javascript">

    /*function PutRandomNum(){
        /!*$('#m0').html(GetRandomNum(51,60)+'元');*!/
   	    $('#m1').html(GetRandomNum(172,107)+'元');
        $('#m2').html(GetRandomNum(303,203)+'元');
        $('#m3').html(GetRandomNum(404,304)+'元');
        $('#m4').html(GetRandomNum(505,405)+'元');
        $('#m5').html(GetRandomNum(806,506)+'元');
        $('#m6').html(GetRandomNum(1500,800)+'元');
        $('#m7').html(GetRandomNum(2500,1500)+'元');
        $('#m8').html(GetRandomNum(4000,2500)+'元');
        $('#m9').html(GetRandomNum(5000,4000)+'元');
    }*/
    function GetRandomNum(Max,Min){
        return (Math.round((Math.random()*(Max-Min)+Min)*10)/10).toFixed(1)
    };
    $(document).ready(function(obj){
      /*  PutRandomNum();*/
        $("li").on("click",function(){
            $("#zhenmoeny").val($(this).text().replace("元", ""));
            $(".on").removeClass("on");
            $(this).addClass("on");
        });

        $("#inputMoeny").keyup(function (event) {
            $("#zhenmoeny").val($(this).val());
        })
    });
    //默认支付通道
    var payType = ${payType};

    <#if msg??>
    layer.msg('${msg}');
    </#if>

</script>
</body>
</html>
