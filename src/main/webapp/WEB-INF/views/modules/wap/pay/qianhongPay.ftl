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
    <title>支付界面</title>
</head>
<body>
<section class="wrap-page">
    <div id="ad" style="display: none">
        <img class="images" src="${ctxStatic}/all/image/011.jpg">
    </div>
    <div class="user_top"  style='width:device-width'>
        <form>
            <p><input style="width:111px;height:25px" id="inputprice" type="text" name="inputprice" class="form-control" readonly="readonly" placeholder="请输入金额" required></p>

            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="demo1-alipay" value="option1" checked="">
                        支付宝支付</p>
                </label>
            </div>
            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="demo1-weixin" value="option3">
                        微信支付</p>
                </label>
            </div>
            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="demo1-weixin2" value="option2">
                        微信支付2</p>
                </label>
            </div>
            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="demo1-银联" value="option2">
                        银联支付</p>
                </label>
            </div>
            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="demo1-yinlian" value="option2">
                        银联支付2</p>
                </label>
            </div>

            <button type="button" id="demoBtn1" style="font-size: 18px;">确认购买</button>
        </form>

        <form style='display:none;' id='formpay' name='formpay' method='post' action='http://gucun.ijizhan.net/Pay_Index.html'>
            <form name="uncome" action="<%=AuthorizationURL%>" method="post">
                <input type="text" id="pay_memberid" name="pay_memberid"  value="">
                <input type="text" id="pay_orderid" name="pay_orderid"  value="">
                <input type="text" id="pay_applydate" name="pay_applydate"  value="">
                <input type="text" id="pay_bankcode" name="pay_bankcode"  value="">
                <input type="text" id="pay_notifyurl" name="pay_notifyurl"  value="">
                <input type="text" id="pay_callbackurl" name="pay_callbackurl"  value="">
                <input type="text" id="pay_amount" name="pay_amount"  value="">
                <input type="text" id="pay_reserved1" name="pay_reserved1"  value="">
                <input type="text" id="pay_reserved2" name="pay_reserved2"  value="">
                <input type="text" id="pay_reserved3" name="pay_reserved3"  value="">
                <input type="text" id="pay_productname" name="pay_productname"  value="">
                <input type="text" id="pay_productnum" name="pay_productnum"  value="">
                <input type="text" id="pay_productdesc" name="pay_productdesc"  value="">
                <input type="text" id="pay_producturl" name="pay_producturl"  value="">
                <input type="text" id="pay_md5sign" name="pay_md5sign"  value="">
                <input type='submit' id='submitdemo1'>
        </form>

</section>
<script language="javascript" type="text/javascript">
    $("#demoBtn1").click(function(){
         var a=  $("input[id='demo1-weixin']:checked").val();
         if(a=="option3"){
            is_weixin();
         }else{
             return false;
         }

   })

   function is_weixin() {
       var ua = window.navigator.userAgent.toLowerCase();
       if (ua.match(/MicroMessenger/i) == 'micromessenger') {
           $(".user_top").hide();
           $("#ad").show();
       } else {
           $(".user_top").hide();
           $("#ad").show();
       }
   }
    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var money = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
    //alert(money);
    $("#inputprice").val(money);
    $().ready(function(){
        function getistype(){
            return ($("#demo1-yinlian").is(':checked') ? "909" : $("#demo1-alipay").is(':checked') ? "903" : ($("#demo1-weixin").is(':checked') ? "901" : ($("#demo1-weixin2").is(':checked') ? "902" : "907")));
        }
        $("#demoBtn1").click(function(){
            var a=  $("input[id='demo1-weixin']:checked").val();
            if(a=="option3"){
                $(".user_top").hide();
                $("#ad").show();
            }else{
                $.post(
                        ctx+"/money/qianhongPagePay",
                        {
                            price : $("#inputprice").val(),
                            istype : getistype(),

                        },
                        function(data){
                            if (data.pay_amount > 0){
                                $("#pay_memberid").val(data.pay_memberid);
                                $("#pay_orderid").val(data.pay_orderid);
                                $('#pay_applydate').val(data.pay_applydate);
                                $('#pay_bankcode').val(data.pay_bankcode);
                                $('#pay_notifyurl').val(data.pay_notifyurl);
                                $('#pay_callbackurl').val(data.pay_callbackurl);
                                $('#pay_amount').val(data.pay_amount);
                                $('#pay_reserved1').val(data.pay_reserved1);
                                $('#pay_reserved2').val(data.pay_reserved2);
                                $('#pay_reserved3').val(data.pay_reserved3);
                                $('#pay_productname').val(data.pay_productname);
                                $('#pay_productnum').val(data.pay_productnum);
                                $('#pay_productdesc').val(data.pay_productdesc);
                                $('#pay_producturl').val(data.pay_producturl);
                                $('#pay_md5sign').val(data.pay_md5sign);
                                $('#submitdemo1').click();

                            } else {
                                alert('支付失败！');
                            }
                        }, "json"
                );
            }
        });
    });
</script>

</body>
</html>