<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/chognti.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="Stylesheet" href="${ctxStatic}/all/css/imageflow.css"/>
    <link rel="stylesheet" href="${ctxStatic}/js/jquery-ui-1.12.1/jquery-ui.min.css"/>
    <script type="text/javascript" src="${ctxStatic}/all/js/imageflow.js"></script>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <#--<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>-->
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="shaop_name" style="overflow: auto" >
        <div class="user_top_name">类型
            <select id="status">
                <option value="${type!' '}">
                    <#if type??>
                        <#if type == 1 >
                            提现
                        <#elseif type == 0>
                            充值
                        <#else >
                            --请选择--
                        </#if>
                    <#else >
                        --请选择--
                    </#if>
                </option>
                <option value="1">提现</option>
                <option value="0">充值</option>
            </select>
        </div>
        <div style="user_top_time">
        <div class="his_date icon left">日期</div>
        <div class="his_time icon left" ><input type="text" id="inputDate" style="width:3rem;background: #002b54; color:#fff;line-height:0.6rem;font-size: 0.25rem; text-align: center;" value=""/></div>
        <div class="his_chaxun icon right" onclick="selectHistory()">查询</div>
    </div>
    </div>
    <div class="user_top">
        <ul class="user_time">
            <li>时间</li>
            <li>类型</li>
            <li>状态</li>
            <li>金额</li>
        </ul>
        <#if list??>
            <#list list as moneyRecord>
                <ul class="user_time_txt">
                    <li>${moneyRecord.createTime?datetime}</li>
                    <li>
                        <#if moneyRecord.type??>
                            <#if moneyRecord.type?number == 0>
                                充值
                                <#else>
                                    提现
                            </#if>
                        </#if>
                    </li>
                    <li>
                        <#if moneyRecord.status??>
                            <#if moneyRecord.status?number == 0>
                                处理中
                                    <#elseif moneyRecord.status?number == 1>
                                        成功
                                    <#else >
                                        失败
                            </#if>
                        </#if>
                    </li>
                    <li>¥${moneyRecord.money}</li>
                </ul>
            </#list>
        </#if>

        <p class="no_more">没有更多了~</p>
    </div>
</section>
<#include "/wap/footer.ftl"/>
<script TYPE="text/javascript" src="${ctxStatic}/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script TYPE="text/javascript" src="${ctxStatic}/js/jquery-ui-1.12.1/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
    $(function(){
        console.log(getNowFormatDate());
        $("#inputDate").val(getNowFormatDate());

        $("#status").on("click", function(){
            $(this).children().eq(0).text("--请选择--").val(' ');
        });

        $("#inputDate").datepicker({
            changeMonth: true,
            changeYear: true,
            maxDate: new Date()
        });
    });
    function selectHistory() {
        var stringDate = $("#inputDate").val();
        var status = $("#status").val();
        if(/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/.test(stringDate) || stringDate == ""){
            window.location.href = '${ctxWap}/money/payRecord?withDrawTime='+stringDate+"&type="+status;
        }else{
            layer.msg("请输入正确的时间");
        }
    }
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
//                + " " + date.getHours() + seperator2 + date.getMinutes()
//                + seperator2 + date.getSeconds();
        return currentdate;
    }
</script>
</body>
</html>