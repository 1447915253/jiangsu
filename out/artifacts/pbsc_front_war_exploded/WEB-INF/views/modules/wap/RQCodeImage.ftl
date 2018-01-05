<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
        <#include "/wap/header.ftl" >
        <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
        <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="${ctxStatic}/all/css/denglu.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
        <title>${title}</title>
    </head>
    <body>
        <section class="text-center">
            <div>
                <img style="width: 100%;" id="images" src="${ctxWap}/displayQRCode?mobile=${mobile}">
            </div>
            <span><font color="black" size="3rem;">请识别二维码，关注后登录</font></span>
        </section>
        <#include "/wap/footer.ftl"/>
    </body>
</html>