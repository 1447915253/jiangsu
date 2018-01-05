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
    <div class="user_top"  style='width:device-width'>
        <form>
            <p><input style="width:111px;height:25px" id="inputprice" type="text" name="inputprice" class="form-control" readonly="readonly" placeholder="请输入金额" required></p>

            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="xionyun-alipay" value="option1" checked="">
                        支付宝支付</p>
                </label>
            </div>
            <div class="radio" style="font-size: 18px;">
                <label>
                    <p><input type="radio" name="demo1" id="xionyun-weixin" value="option2">
                        微信支付</p>
                </label>
            </div>

            <button type="button" id="demoBtn1" style="font-size: 18px;">确认购买</button>
        </form>

        <form style='display:none;' id='formpay' name='formpay' method='post' action='https://pay.crossex.cn/bear-pay/pay'>
            <form name="uncome" action="<%=AuthorizationURL%>" method="post">
                <input type="text" id="shopId" name="shopId"  value="">
                <input type="text" id="money" name="money"  value="">
                <input type="text" id="payWay" name="payWay"  value="">
                <input type="text" id="notify_url" name="notify_url"  value="">
                <input type="text" id="return_url" name="return_url"  value="">
                <input type="text" id="orderId" name="orderId"  value="">
                <input type="text" id="orderUid" name="orderUid"  value="">
                <input type="text" id="goodsname" name="goodsname"  value="">
                <input type="text" id="token" name="token"  value="">
                <input type='submit' id='submitdemo2'>
        </form>
</section>
<script language="javascript" type="text/javascript">
    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var money = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容

    $("#inputprice").val(money);
    $().ready(function(){
        function getistype(){
             return ($("#xionyun-alipay").is(':checked') ? "103" : ($("#xionyun-weixin").is(':checked') ? "104"  : "103"));
        }
        $("#demoBtn1").click(function(){
            $.post(
                    ctx+"/money/xionyunPagePay",
                    {
                        price : $("#inputprice").val(),
                        istype : getistype(),
                    },
                    function(data){
                        if (data.money > 0){
                            window.location.href = (data.url);
                           /* $("#shopId").val(data.shopId);
                            $("#money").val(data.money);
                            $('#payWay').val(data.payWay);
                            $('#notify_url').val(data.notify_url);
                            $('#return_url').val(data.return_url);
                            $('#orderId').val(data.orderId);
                            $('#orderUid').val(data.orderUid);
                            $('#goodsname').val(data.goodsname);
                            $('#token').val(data.token);
                            $('#submitdemo2').click();*/

                        } else {
                            alert('支付失败！');
                        }
                    }, "json"
            );
        });
    });
</script>

</body>
</html>