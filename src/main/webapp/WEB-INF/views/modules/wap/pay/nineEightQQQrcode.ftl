<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<#include "/wap/header.ftl" >
    <style type="text/css">
        ul{
            font-size: 0.8rem;
            list-style: none;
            line-height: 1.5rem;
        }
    </style>
    <title>qq钱包支付</title>
</head>
<body>
<div style="width: 100%; height: 400px; text-align: center;  position: absolute; top: 50%; margin-top: -200px;">
    <div>
        <img src="${ctxWap}/money/qrcodeImgNotImage?url=${url}">
    </div>
    <div style="text-align: left;">
        <ul>
            <li>为积极响应QQ支付新规，客户支付请按如下步骤操作：</li>
            <li>1.保存本支付二维码至手机</li>
            <li>2.打开QQ扫一扫功能</li>
            <li>3.点击右上角功能键选择从相册选取本次充值支付二维码 </li>
            <li>4.完成支付。</li>
        </ul>
    </div>
</div>
</body>
</html>