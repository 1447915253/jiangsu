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
    <title>微信支付</title>
</head>
<body>
<div style="width: 100%; height: 400px; text-align: center;  position: absolute; top: 50%; margin-top: -200px;">
</div>
<#include "/wap/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        layer.closeAll();
        window.location.href='${url}';
    })
</script>
</body>
</html>