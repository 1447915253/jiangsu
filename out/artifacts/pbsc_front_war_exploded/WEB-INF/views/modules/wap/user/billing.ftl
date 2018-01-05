<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<#include "/wap/header.ftl">
<#assign footer = "user"/>

    <link href="${ctxStatic}/all/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
    <title>${title}</title>
</head>
<body>
<div class="loader">
    <div class="act">
        <div class="spinner">
            <div class="double-bounce1"></div>
            <div class="double-bounce2"></div>
        </div>
    </div>
    <div class="bg"></div>
</div>


<div class="bill-details">
    <div class="head-menu uk-block  uk-block-default" style="background: #002b54">
        <a class="<#if flag == "1">active</#if>" style="width:32%" id="one" onclick="find('1')">今日</a>
        <a style="width:32%" id="two" href="javascript:void(0);" onclick="find('2')">近7日</a>
        <a style="width:32%" id="three" href="javascript:void(0);" onclick="find('3')">全部</a>
    </div>
    <div class="list-bill uk-block  uk-block-default"  style="background: #002b54">
        <table>
            <thead>
            <tr>
                <td width="150px">时间</td>
                <td>类型</td>
                <td>交易额</td>
                <td>余额</td>
            </tr>
            </thead>
            <tbody>
                <#if moneyChangeList??>
                    <#list moneyChangeList as moneyChange>
                        <tr >
                            <td><font size="0.3rem;">${moneyChange.createTime?datetime}</font></td>
                            <td>
                                <#if moneyChange.type == "0">
                                    充值
                                    <#elseif moneyChange.type == "1">
                                        提现
                                    <#elseif moneyChange.type == "2">
                                        建仓
                                <#elseif moneyChange.type == "3">
                                    平仓
                                <#elseif moneyChange.type == "5">
                                    佣金转余额
                                    <#else >
                                </#if>
                            </td>
                            <td>
                                <div >
                                    <span>${moneyChange.difMoney}</span>
                                </div>
                                <small></small>
                            </td>
                            <td>${moneyChange.afterMoney}</td>
                        </tr>
                    </#list>
                </#if>
            </tbody>

        </table>
        <a class="bottom_more">加载更多</a>
        <a class="bottom_no_more" >没有更多了~</a>
    </div>
</div>



</body>
</html>
<script>
    $(document).ready(function () {
        var flag = ${flag};
        if("2" == flag){
            $("#two").addClass("active").siblings().removeClass("active")
        }else if("3" == flag){
            $("#three").addClass("active").siblings().removeClass("active")
        }
        /*$(".head-menu>a").click(function () {
            $(this).addClass("active").siblings().removeClass("active")
        })*/
    });

    function find(value){
        window.location.replace("${ctxWap}" + "/money/getBilling?flag=" + value);
    }
</script>
