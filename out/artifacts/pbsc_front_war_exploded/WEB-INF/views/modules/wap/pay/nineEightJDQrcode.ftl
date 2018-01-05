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
    <title>京东扫码支付</title>
</head>
<body>
<div style="width: 100%; height: 400px; text-align: center;  position: absolute; top: 50%; margin-top: -200px;">
    <div>
        <img src="${ctxWap}/money/qrcodeImgNotImage?url=${url}">
    </div>
    <div style="text-align: left;">
        <ul>
            <li>此支付需下载京东金融app,支付方式如下:</li>
            <li>1.直接长按识别二维码或通过第二台设备扫描二维码</li>
            <li>2.完成支付</li>
        </ul>
    </div>
</div>
</body>
</html>