<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" /><meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>优惠券详情</title>
    <meta name="format-detection" content="telephone=no" />
    <!--公共样式-->
    <style>
        html {
            /*font: normal 14px / 20px "Helvetica Neue", Helvetica, Arial, sans-serif;*/
            font: normal 14px / 20px 'Microsoft Yahei';
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
            background: #001222;
            color: #bbbbbb;
        }

        body {
            margin: 0;
        }

        a {
            background: transparent;

        }

        a:active,
        a:hover {
            text-decoration: none !important;
        }
        a,button,input{-webkit-tap-highlight-color:rgba(255,0,0,0);}
        a::selection{ background: transparent}
        a::-webkit-selection{ background: transparent;}
        a,
        .uk-link {
            color: #bbbbbb;
            text-decoration: none;
            cursor: pointer;
        }

        a:hover,
        .uk-link:hover {
            color: #d3af30;
            text-decoration: underline;
        }

        abbr[title] {
            border-bottom: 1px dotted;
        }

        b,
        strong {
            font-weight: bold;
        }

        :not(pre) > code,
        :not(pre) > kbd,
        :not(pre) > samp {
            font-size: 12px;
            font-family: Consolas, monospace, serif;
            color: #dd0055;
            white-space: nowrap;
        }

        em {
            color: #dd0055;
        }

        ins {
            background: #ffffaa;
            color: #bbbbbb;
            text-decoration: none;
        }

        mark {
            background: #ffffaa;
            color: #bbbbbb;
        }

        q {
            font-style: italic;
        }

        small {
            font-size: 80%;
        }

        sub,
        sup {
            font-size: 75%;
            line-height: 0;
            position: relative;
            vertical-align: baseline;
        }

        sup {
            top: -0.5em;
        }

        sub {
            bottom: -0.25em;
        }

        audio,
        canvas,
        iframe,
        img,
        svg,
        video {
            vertical-align: middle;
        }

        img {
            max-width: 100%;
            border: 0;
        }

        .uk-img-preserve,
        .uk-img-preserve img {
            max-width: none;
        }

        svg:not(:root) {
            overflow: hidden;
        }

        blockquote,
        figure {
            margin: 0;
        }





        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            margin: 0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-weight: normal;
            color: #000;
            text-transform: none;
        }




        ul > li > ul,
        ul > li > ol,
        ol > li > ol,
        ol > li > ul {
            margin: 0;
        }

        dt {
            font-weight: bold;
        }

        dd {
            margin-left: 0;
        }

        hr {
            box-sizing: content-box;
            height: 0;
            margin: 0;
            border: 0;
            border-top: 1px solid #4f4f4f;
        }

        address {
            font-style: normal;
        }

        blockquote {
            padding-left: 15px;
            border-left: 5px solid #4f4f4f;
            font-size: 16px;
            line-height: 22px;
            font-style: italic;
        }

        pre {
            padding: 10px;
            background: #f5f5f5;
            font: 12px / 18px Consolas, monospace, serif;
            color: #bbbbbb;
            -moz-tab-size: 4;
            tab-size: 4;
            overflow: auto;
        }



        article,
        aside,
        details,
        figcaption,
        figure,
        footer,
        header,
        main,
        nav,
        section,
        summary {
            display: block;
        }

        progress {
            vertical-align: baseline;
        }

        audio:not([controls]) {
            display: none;
        }

        [hidden],
        template {
            display: none;
        }

        iframe {
            border: 0;
        }
    </style>
    <!--单独样式-->
    <style>
        body,html{
            background: #313131;
            color: #2E2000;
        }
        .header ,.number ,.main{
            width: 91%;
            height: 13.5rem;
            margin: 0 auto;
            background: #1F1F1F;
            margin-top: 30px;
            /*margin-bottom: 30px;*/
            padding: 0 0.5rem;
        }
        .header {
            height: 13.5rem;
            background: #E0B936;
            position: relative;
        }
        .header i{
            width: 100px;
            height: 80px;
            display: block;
            position: absolute;
            top: 5.6rem;
            background: url(../img/images/coupon/rushboommer.png) no-repeat;
            background-size: 100% 100%;
            right: 1rem;
        }
        .header h6{
            height: 0.2rem;
            background: url(../img/images/coupon/baowen.png) repeat-x;
            position: absolute;
            top:0;
            width: 100%;
            left: -0.06rem;
        }
        .header h1{
            /*margin-top: 0.4rem;*/
            text-align: center;
            font-size: 1.5rem;
            padding-top: 0.9rem;
        }
        .header h3{
            font-size: .9rem;
            padding-top: 1.4rem;
            height: 1.5rem;
            display:-webkit-box;/* android 2.1-3.0, ios 3.2-4.3 */
            display:-webkit-flex;/* Chrome 21+ */
            display:-ms-flexbox;/* WP IE 10 */
            display:flex;/* android 4.4 */
            justify-content:space-around;
            align-items:center;
        }
        .header h3 s{
            width: 1.6rem;
            display: inline-block;
            height: 1.5rem;
            background: url(../img/images/coupon/xiaohei.png) no-repeat;

        }
        .header h3 b{
            font-weight: normal;
        }
        .header h2{
            font-size: 1.6rem;
            margin-top: 2.6rem;
        }
        .header h2 span{
            font-size: 4.2rem;
            font-weight: bold;
        }
        .header>p{
            color: #5f4512;
            padding-left: 0.6rem;
        }
        .number{
            height: 2.4rem;
            margin-top: 0.6rem;
            line-height: 2.4rem;

        }
        .number p{
            margin: 0;
            height: 100%;
            color: #C4C4C4;
            padding-left: 0.6rem;
        }
        .number p span{
            color: #E3C29A;
        }
        .main{
            height: auto;
            color: #787878;
            margin-top: 0.5rem;
            padding: 0 0.9rem;
            width: 88%;
            padding-bottom: 2rem;
            margin-bottom: 2rem;
        }
        .main h4{
            margin: 0;
            color: #C4C4C4;
            padding-top: 0.7rem;
            font-size: 1rem;
            padding-bottom: 0.4rem;
        }
        .main p{
            margin: 0;
            /*padding:0.4rem 0;*/
            line-height: 2.2rem;
        }
    </style>
</head>
<body>
<#if type??>
<#--<#if type == "1">
    <div class="header">
        <h6></h6>
        <h1>必盈券</h1>
        <h3><s></s>有效期:<b>${createTime} - ${endTime}</b><s></s></h3>
        <h2><span>10</span>元</h2>
        <p>使用范围: <span>白银1分钟</span></p>
        <i></i>
    </div>
    <div class="number">
        <p>共 <span>${num}</span> 张</p>
    </div>
    <div class="main">
        <h4>使用说明</h4>
        <p>面额:　<span>10</span>元/张</p>
        <p>如何领券:　关注公众号，即可立马获得1张必盈券和9张增益券;</p>
        <p>如何使用:　必盈券作为新玩家稳赚第一笔收益的10元交易券使用，不限定交易次数，可多次交易直到你赚取首笔收益后消失，盈利部分你全部拿走，而亏损你无需承担任何费用。</p>
        <p>券的属性:　<br>
            1.必盈券限选白银交易品种；    <br>
            2.必盈券限选60秒交易；   <br>
            3.必盈券有效期：3天。   <br>
        </p>
    </div>-->
    <#if type == "2">
    <div class="header">
        <h6></h6>
        <h1>直盈券</h1>
        <h3><s></s>有效期:<b>${createTime} - ${endTime}</b><s></s></h3>
        <h2><span>${money}</span>元</h2>
        <p>使用范围: <span>不限</span></p>
        <i></i>
    </div>
    <div class="number">
        <p>共 <span>${num}</span> 张</p>
    </div>
    <div class="main">
        <h4>使用说明</h4>
        <p>面额:　<span>${money}</span>元/张</p>
        <p>如何领券:　关注公众号，即可立马获得1张直盈券和9张增益券;</p>
        <p>如何使用:　仅限15秒，买涨买跌均可。用户亏损，券消失；用户盈利，账户收益${money}*0.8元，券消失。每个用户每日最多使用10张直盈券。</p>
        <p>券的属性:　<br>
            1.直盈卷不限交易产品；    <br>
            2.直盈券限选15秒交易；   <br>
            3.直盈券有效期：30天。   <br>
        </p>
    </div>
    <#elseif  type == "3">
    <div class="header">
        <h6></h6>
        <h1>增益券</h1>
        <h3><s></s>有效期:<b>${createTime} - ${endTime}</b><s></s></h3>
        <h2><span>${money}</span>元</h2>
        <p>使用范围: <span>不限</span></p>
        <i></i>
    </div>
    <div class="number">
        <p>共 <span>${num}</span> 张</p>
    </div>
    <div class="main">
        <h4>使用说明</h4>
        <p>面额:　<span>${money}</span>元/张</p>
        <p>如何领券:　关注公众号，即可立马获得1张直盈券和9张增益券;</p>
        <p>如何使用:　不限品种，用户下单可使用下单金额的10%的增益券，如：按照平台15秒80%收益率来计算，若投入100元进行15秒交易，不使用增益券可盈利80元，
            按照100元交易金额的10%则可以附加使用1张增益券，参与收益的本金金额为110，因此收益88元。每笔交易最高金额5000元。</p>
        <p>券的属性:　<br>
            1.不限定购买品种；    <br>
            2.最多一次性使用5张；   <br>
            3.增盈券有效期：7天。   <br>
        </p>
    </div>
    <#else >
    </#if>
</#if>

</body>
</html>




