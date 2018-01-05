<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl">
    <#assign footer = "user"/>
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xiaoxi.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="user_top">
        <#list pb.data as it>
            <div class="new_huiyuan" onclick="userMsgDetail('${it.id}')">
                <div class="huiyuan_lf left clearfix">
                    <div class="<#if it.readStatus?number ==0>yuandian-active yuandian yuandian-ac left<#else>yuandian yuandian-ac left</#if>"></div>
                    <div class="join left bai" style="color:##bababa">${it.title}</div>
                </div>
                <div class="huiyuan_time right clearfix">
                    <p class="hui">创建日期</p>
                    <p class="hui">${it.createTime?date}</p>
                </div>
            </div>
            <img src="${ctxStatic}/all/image/bank_line.png" class="bank_line">
            </#list>
        <p class="text-center">没有更多了~</p>
    </div>
</section>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="application/javascript" charset="UTF-8" src="${ctxStatic}/asserts/iscroll-4/iscroll.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript">
    function userMsgDetail(id) {
        window.location.href = ctxWap + '/user/userMessageDetail?id='+id;
    }
</script>
</body>
</html>