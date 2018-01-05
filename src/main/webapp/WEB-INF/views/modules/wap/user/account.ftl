<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl">
    <#assign footer = "user"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/zhanhuxinxi.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="my_infro text-center">我的信息</div>
    <div class="user_top">
        <div class="new_huiyuan">
            <div class="huiyuan_lf left clearfix">
                <div class="join left bai">交易账户：<span>${user.mobile}</span></div>
            </div>
        </div>
        <img src="all/image/xinhuiyuan_03.png">
        <div class="new_huiyuan">
            <div class="huiyuan_lf left clearfix">
                <div class="join left hui">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${user.chnName}</span></div>
            </div>
        </div>
        <img src="all/image/xinhuiyuan_03.png">
        <div class="new_huiyuan">
            <div class="huiyuan_lf left clearfix">
                <div class="join left hui">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：<span>${user.mobile}</span></div>
            </div>
        </div>
        <img src="all/image/xinhuiyuan_03.png">
        <div class="new_huiyuan">
            <div class="huiyuan_lf left clearfix">
                <div class="join left hui">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<span></span></div>
            </div>
        </div>
        <img src="all/image/xinhuiyuan_03.png">
        <div class="new_huiyuan">
            <div class="huiyuan_lf left clearfix">
                <div class="join left hui">注册信息：<span>${user.registerTime?date}</span></div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
</body>
</html>