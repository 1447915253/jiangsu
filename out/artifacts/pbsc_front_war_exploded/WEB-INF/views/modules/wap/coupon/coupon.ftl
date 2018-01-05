<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8" /><meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>我的优惠券</title>
    <meta name="format-detection" content="telephone=no" />
<#include "/wap/header.ftl" >
    <!--<link type="text/css" href="css/detail.css?v=110" rel="stylesheet" />-->
    <style>
        .head-menu>a {
            color: #a1a1a1;
            display: inline-block;
            width: 25%;
            text-align: center;
            box-sizing: border-box;
            line-height: 3.4em;
            height: 3.4em;
        }
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

        p,
        ul,
        ol,
        dl,
        blockquote,
        pre,
        address,
        fieldset,
        figure {
            margin: 0 0 15px 0;
        }

        * + p,
        * + ul,
        * + ol,
        * + dl,
        * + blockquote,
        * + pre,
        * + address,
        * + fieldset,
        * + figure {
            margin-top: 15px;
        }

        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            margin: 0 0 15px 0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-weight: normal;
            color: #bbbbbb;
            text-transform: none;
        }

        * + h1,
        * + h2,
        * + h3,
        * + h4,
        * + h5,
        * + h6 {
            margin-top: 25px;
        }

        h1,
        .uk-h1 {
            font-size: 36px;
            line-height: 42px;
        }

        h2,
        .uk-h2 {
            font-size: 24px;
            line-height: 30px;
        }

        h3,
        .uk-h3 {
            font-size: 18px;
            line-height: 24px;
        }

        h4,
        .uk-h4 {
            font-size: 16px;
            line-height: 22px;
        }

        h5,
        .uk-h5 {
            font-size: 14px;
            line-height: 20px;
        }

        h6,
        .uk-h6 {
            font-size: 12px;
            line-height: 18px;
        }

        ul,
        ol {
            padding-left: 30px;
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
            margin: 15px 0;
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

        .uk-block {
            position: relative;
            box-sizing: border-box;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-bottom: 1px;
        }

        .uk-block-default {
            background: #1e1e1e;
        }

        .ntice {
            padding: 10px;
            text-align: center;
            font-size: 12px;
            color: #969090;
        }

        .ntice > span {
            color: #d3af30
        }

        .grid {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .grid > * {
            float: left;
        }

        .grid:after {
            content: ""; display: block;overflow: hidden; clear: both;
        }

        .uk-grid:before,
        .uk-grid:after {
            content: "";
            display: block;
            overflow: hidden;
        }

        .width-1-2 {
            width: 50%;
        }

        ul, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .p_ind .headpor {
            width: 43px;
            height: 43px;
            border-radius: 3px;
            overflow: hidden;
        }

        button.btn
        {
            width:100%; border:0; line-height:16px !important;
        }
        .btn{ display: block; border-radius: 6px;padding:3px 18px; text-align: center; }
        .row>.btn{ line-height: 35px; height: 35px; margin-top: 10px; margin-bottom: 10px;}
        .btn-primary{ color: #FDBE19; }
        .btn-primary:hover{ background-color: #e7bd30; color:#111; text-decoration: none; }
        .btn-danger{background-color:#FA2E42; color: #fff; }
        .btn-success{background-color:#1FC65B; color: #fff; }
        .btn-black{ background-color: #191919; color: #c0bebe;}
        .btn.btn-black.btn-right{padding:0 10px;font-size: 12px; box-sizing: border-box; line-height: 34px; height: 34px; position: absolute; right: 13px; top: 18px;}
        .btn.btn-black.btn-right2{padding:0 10px;font-size: 12px; box-sizing: border-box; line-height: 34px; height: 34px; position: absolute; right: 13px; top: 0px;}
        .btn.bind
        {
            background-color:#555 !important; color:#888;
        }

        .input{ border:1px solid; display: block; border-radius: 6px; box-sizing: border-box; font-size: 14px;}
        .input-primary{ background-color: #262626; border-color: #555454; color: #7c7c7c; padding: 5px 10px; }

        /*layer自定义样式*/
        .pop
        {
            width:80%;}
        .pop .layermbtn{ border-color:#2b2a2a;border-bottom-left-radius:6px; border-bottom-right-radius:6px; overflow:hidden; height:45px; }
        .pop .layermbtn span{ background-color: #b28c26;  border-radius:0 !important; background-color: #d3af30 !important;color:#000000; height:45px; line-height:45px; }
        .pop .layermbtn span:first-child{ width:100%; height:45px;line-height:45px;}
        .pop .layermbtn span:nth-child(2){  width:50%; }
        .pop .layermbtn span:nth-last-child(2){  width:50%;background-color:#464646 !important; color:#bbbbbb  }

        .pop .layermbtn::before{ background:#2b2a2a;}
        .pop.layermchild{background-color: #363636}
        .pop.layermchild>h3{border-color: #2b2a2a; background-color: #464646;  text-align: center;padding-left: 40px;}
        .pop>.layermend{background-color: #3a3a3a;  padding: 0;}
        .pop>.layermcont{ padding-top:0px; max-height: 300px;
            overflow: auto;}
        .pop > .layermcont h4{        margin:0    }
        /**自定义弹窗**/

        .no_close .layermend {
            display:none;
        }
        .pop-panel{ width: 100%; height: 100%; position: fixed;  left:0; top:0; z-index: 999; display: none;}
        .pop-panel.show{ display: block;}
        .pop-panel.show>.pop-box{animation: popshow 0.2s;}
        .pop-panel>.pop-box{ border-radius:6px; overflow: hidden;  background-color: #3c3b3b; width: 90%; height: auto; position: absolute; top: 10%; left: 5%;}
        .pop-panel>.bg{ background: rgba(0,0,0,0.5); height: 100%; width: 100%; z-index: -1; position: absolute; top: 0; left: 0;}
        .pop-panel .pop-title{background-color: #5e5e5e; color: #aaaaaa; text-align: center; font-size: 16px; line-height: 45px; height: 45px;}
        .pop-panel .pop-content{ padding: 10px;}
        .pop-panel .pop-title>.btn{ position: absolute; right: 0; top: 0; width: 45px; height: 45px; border-radius: 0; background: #515151; padding: 0;}
        @keyframes popshow {
            from{transform:scale(0.5,0.5)}
            to{ transform:scale(1,1)}
        }

        .small{font-size: 10px;}
        .icon{display: inline-block;  height: 14px; width: 15px; vertical-align:middle;background-size: 100%; background-repeat: no-repeat; margin-top: -2px;}
        .icon-cz{ background-image: url("../imgs/cz.png"); }
        .icon-tx{ background-image: url("../imgs/tx.png"); }
        .icon-yh{ background-image: url("../imgs/yh1.png"); }
        .icon-wx{ background-image: url("../imgs/wx1.png"); }
        .icon-pay4{ background-image: url("../imgs/wx1.png"); }
        .icon-pay7{ background-image: url("../imgs/yh1.png"); }
        .nav{ height:58px; position:fixed; width:100%; background:#1e1e1e; border-top:2px solid #484848; bottom:0; left:0; z-index:99;}
        .nav a{ display:block; width:20%; text-align:center; font-size:0.95em; color:#8e8e8e; float:left; padding-top: 0.6em;padding-bottom: 0.6em;}
        .nav a.red{ color:#f91800;}
        .nav a i{ margin-right: -5px; font-size: 1.2em;}
        i.gred{ color: #d3af30}

        *.text-red{ color: #f91800;}
        *.text-green,*.text-gred{ color: #19962a;}

        *.text-yellow{color:#ffc11a;}
        .nodata
        {
            text-align:center;
            color:#555454
        }
        .bottom_more{
            display:block;
            line-height:40px;
            text-align:center;
            color:#ccc
        }
        .bottom_no_more{
            display:block;
            line-height:40px;
            text-align:center;
            color:#555
        }
        .bottom_no_more:hover{
            color:#555
        }
        ul.btn_list{ margin-top: 15px; padding: 0;}
        ul.btn_list>li{border-bottom: 1px solid #2f2f2f;position: relative;  }
        ul.btn_list>li:last-child{ border-bottom: none}
        ul.btn_list>li>a{ display:block; line-height: 45px; padding-left: 10px; padding-right: 10px; font-size: 1.1em;}
        ul.btn_list .iconfont{margin-right: 10px;}

        .ad>img{ width: 100%; height: auto;}

        .btn-row{}
        .btn-row:after{content: ""; display: block;overflow: hidden; clear: both;   }
        .btn-row>a{ display:block; float:left;height: 30px; line-height: 30px; margin-top:10px;  background-color:#323232; border:1px solid #2a2929;  box-sizing: border-box; text-align: center}
        .btn-row.l_3>a{width: 31.3%; margin-left: 1%; margin-right:1%;}
        .btn-row.l_4>a{width: 23%; margin-left: 1%; margin-right:1%;}

        .btn-row>a.active{background-color: #b28c26; color: #111111;}
        .btn-row>a>input{width: 100%; height: 100%; padding: 0; border: none; margin: 0;  text-align: center; background: none; color:#a6a0a0; font-size: 14px; }
        /**底部安全提示**/
        .foot_safety{ position: relative; height: 100px; padding-top: 1px;}
        .foot_safety>hr{margin-top: 50px; border-color: #1c1c1c}
        .foot_safety>div{ left: 50%; width:120px; margin-left: -60px; top:15px;position: absolute; text-align: center; color: #606060}
        .foot_safety>div>i{ font-size: 50px; line-height: 60px; width: 50px; margin-left: 35px;  background-color:#000000;  display: block;}
        .foot_safety>div>span{ line-height:30px; font-size: 12px; }


        .prompt{ line-height: 30px; text-align: center; font-size: 12px;}
        .form{ }
        .form-row{padding: 15px 10px 0 10px; position: relative;}
        .form-row select,.form-row input{ width: 100%;  height: 40px;}
        .form-row span{display: block; font-size: 11px; color: #868686; margin:5px 0;}
        .form-row>.btn{ height: 40px; line-height: 40px;  text-align: center;}

        .message{ padding: 20px 0;}
        .message>h2{ font-size: 25px;  text-align: center; line-height: 50px;}
        .message>h2>i{ font-size: 40px;margin-bottom: 20px; vertical-align: middle;}
        .message>.text-red{ text-align: center; display: block; text-decoration: underline;}
        @media screen and ( max-width: 360 px){
            .pop-panel>.pop-box{width: 90%;  left: 5%;}
        }
        @media screen and ( max-width: 320px){
            .pop-panel>.pop-box{width: 100%;  left: 0%;}
        }

        /*加载动画*/
        .loader
        {
            display:none;
            position:fixed;
            width:100%;
            height:100%;
            top:0px;
            left:0px;
            z-index:99999999;
        }
        .loader.show
        {
            display:none;

        }
        .loader > .act
        {
            position:absolute;
            left:50%;
            top:50%;
            margin-left:-30px;
            margin-top:-30px;
            text-align:center;
            color:#888;
            font-size:11px;
        }
        .page_load .loader .act {
            top: 45%;
        }
        .loader > .text {
            text-align:center;
            position: absolute;
            left: 50%;
            top: 45%;
            margin-left: -40px;
            margin-top: 40px;
            text-align: center;
            color: #837b61;
            font-size: 14px;
        }
        .spinner {
            width: 60px;
            height: 60px;

            position: relative;
        }

        .double-bounce1, .double-bounce2 {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #d3af30;
            opacity: 0.6;
            position: absolute;
            top: 0;
            left: 0;
            -webkit-animation: bounce 2.0s infinite ease-in-out;
            animation: bounce 2.0s infinite ease-in-out;

        }

        .form-row .ban
        {

            height:35px;
            display: block;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;

            display:block;
            background:#222 !important; /* 标准的语法（必须放在最后） */

            color: #666;
            padding: 5px 10px;
            line-height: 25px;
        }


        .btn-row{}
        .select-row:after{content: ""; display: block;overflow: hidden; clear: both;   }
        .select-row > div > div {
            width:120%;
            overflow: hidden;
        }
        .select-row>div{float:left;margin-top:10px;height: 40px; line-height: 12px; overflow:hidden; text-align: center; }
        .select-row>div a{  position:relative; font-size:0.9em; float:left;display:block; width: 83.3%;padding-top:8px; height:40px;   background-color:#323232; border:1px solid #2a2929;  box-sizing: border-box; transition:width 0.2s;}
        .select-row > div.disabled a {
            background-color: #3E3E3E;
            border: 1px solid #363636;
            color: #7d7d7d;
        }
        .select-row > div a small {
            font-size:10px;display:block;
        }
        .select-row > div a span {
            position:absolute;
            right:0px;
            top:10px;
            padding:3px;
            background:#cf2e1d;
            color:#f59489;
            border-top-left-radius:3px;
            border-bottom-left-radius:3px;
            font-size:12px;
        }
        .select-row.l_3>div{width: 33.3%;}
        .select-row.l_4>div{width: 25%; }
        .select-row div.active.number a{
            width: 55%;
        }
        .select-row>div.active a{background-color: #b28c26; color: #111111;}

        .select-row>div .ticket_number {
            color:#b28c26;
            transition:width 0.2s;
            float:left;
            width:16%;
            height:40px;
            box-sizing: border-box;
            text-align: center;
            background:#111;
            border:0;
            border-radius:0;
        }
        .select-row>div.active .ticket_number {

            width:28%;

        }

        table.open
        {
            margin:5px;
            width:95%;
        }
        table.open tr>td
        {
            text-align:left !important;
            border-bottom: 0 !important;
            font-size:14px !important;
        }

        .double-bounce2 {
            -webkit-animation-delay: -1.0s;
            animation-delay: -1.0s;
        }

        @-webkit-keyframes bounce {
            0%, 100% { -webkit-transform: scale(0.0) }
            50% { -webkit-transform: scale(1.0) }
        }

        @keyframes bounce {
            0%, 100% {
                transform: scale(0.0);
                -webkit-transform: scale(0.0);
            } 50% {
                  transform: scale(1.0);
                  -webkit-transform: scale(1.0);
              }
        }



        .shez_pwd1 .input_number,.shez_pwd1 .code{
            border: 1px solid;
            display: inline-block;
            border-radius: 3px;
            width:60%;
            margin-left:5px;
            box-sizing: border-box;
            font-size: 14px;
            background-color: #262626;
            border-color: #555454;
            color: #7c7c7c;
            padding: 5px 10px;

        }
        .shez_pwd1 .code {
            width:50%;
        }
        .shez_pwd1 .not1 {
            margin-top:5px;
            font-size:12px;
            color:#888;
        }

        .shez_pwd1 .duanxin1 {
            margin-top:5px;
        }

        .shez_pwd1 .getcode {
            background: rgb(194, 52, 52);
            color: #ffbaba;
            line-height: 28px;
            display: inline-block;
            margin-left: 5px;
            font-size: 12px;
            width: 70px;
        }
        .heider2.title {
            height:60px;
            padding-top:15px;
            text-align:center;
            font-size:16px;

        }
        .heider2.title span{
            display:block;
            font-size:12px;
            color:#646464;
        }

        /*登录、注册、验证码*/
        .login-logo-img{ width:160px; margin: 30px auto 15px auto;}
        .login-logo-img>img{ width: 150px;}
        .uk-block-login .form-row .input{ height: 50px; line-height: 50px;}
        .uk-block-login .form-row .btn-right{ top: 23px;}
        .uk-block-login>.a{  text-align: center; line-height: 2.8em; display: block; text-align: center;}
        .ljzc-box>.a{  text-align: center;display: block; text-align: center; line-height: 1.8em;}
        .ljzc-box{ margin-top: 12px;}


        .login_pop input{
            background-color: #262626;
            color: #7c7c7c;
            padding: 5px 10px;
            border:#444 1px solid;
            display: block;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
            width:100%;
            margin-bottom:10px;
            height:48px;
        }
        .login_pop .img_code,.login_pop  .duanxin1 {
            position:relative;
        }
        .login_pop .img_code img ,.login_pop .getcode{
            position:absolute;
            top:10px; right:3px;
            width:80px;
            height:29px;
        }
        .login_pop .getcode {
            background:#000000;
            font-size:10px;
            width:auto;
            padding:0 5px;
            line-height:30px;border-radius:6px;
            color:#999;
        }
        .login_pop a.register_text{
            display:block;
            text-align:left;
            font-size:12px;
        }
        .login_pop .btn {
            line-height:30px;
            margin-top:10px;
        }

        .pop_ewm {
            padding-top:10px;
        }
        .pop_ewm .ewm {
            width:150px;
            border:10px solid #444;
            margin:10px 0;
        }
        .zhmm-zc{margin-top:8px;}
        .zhmm-zc a
        { width:50%;
            display:block;
            float:left;
        }
        .zhmm-zc a:last-of-type
        {text-align:right;
        }
        .card-coupons{}
        .card-coupons>div a{ width: 33.3%}
        .uk-block {
            position: relative;
            box-sizing: border-box;
            padding-top: 10px;
            padding-bottom: 10px;
            margin-bottom: 1px;
        }
        ul, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        a, .uk-link {
            color: #bbbbbb;
            text-decoration: none;
            cursor: pointer;
        }
        .uk-block-default {
            background: #1e1e1e;
        }
        .bottom_no_more{
            display:block;
            line-height:40px;
            text-align:center;
            color:#555
        }
        .card-coupons>.voucher-box{ padding: 0 15px;}
        .card-coupons>.voucher-box li{ background: #ab3c3c; box-sizing: border-box; padding: 0.4em 0 0.4em 0.8em; width: 100%; border-left:1.2em solid #dfb938; margin-top: 20px; position: relative; color: #dfb938; overflow: hidden;}
        .card-coupons>.voucher-box li>span{ color: #dfb938; font-size: 0.9em; display:block; height:20px;}
        .card-coupons>.voucher-box li>.price{ margin: 1em 0 0.6em 0;}
        .card-coupons>.voucher-box li>.price>b{ font-size:3.9em; margin-right: 0.1em;}
        .card-coupons>.voucher-box li>p{ font-size: 0.8em; line-height: .5em;}
        .right-name{ position: absolute; z-index: 1;  width: 40px;  right: 17px;  top: 0; border-left:dashed #d3d3d3 1px;  height: 100%; text-align: center; font-size: 1rem; box-sizing: border-box; padding-top: 1.7em; line-height: 1.2em;padding-left: 1.1rem;}
        .y-uan{position: absolute;z-index: 2; width: 42px; height: 42px; right: -28px; top: 0; background: #000; border-radius: 50px; margin-top: 2.8em;}
        .s-liang{ position: absolute; z-index: 3; width: 52px; height: 52px; right:72px; top: 1em; border: #CBC935 solid 2px; border-radius: 100%; line-height: 52px; text-align: center; color: #fff8a6; transform:rotate(-15deg)}

        .gray {
            -webkit-filter: grayscale(100%);
            -moz-filter: grayscale(100%);
            -ms-filter: grayscale(100%);
            -o-filter: grayscale(100%);
            filter: grayscale(100%);
            filter: gray;
        }
        .bottom_more
        {
            margin:15px;
            background:#303030;
        }
    </style>
</head>
<body>

<div class="card-coupons">
    <div class="head-menu uk-block  uk-block-default"  style="background: #161618"><a  href="#">优惠劵</a></div>
<#list page.data as it>
    <div class="voucher-box" onclick="couponInfo('${it.type}','${it.money}','${it.num?c}','${it.createTime?date}','${it.endDate?date}')" style="<#if it.type == '1'>display: none;</#if>">
        <ul id="${it.type}">
            <li style="<#if it.status != 0>background-color: gray;</#if>">
                    <span >
                    ${it.createTime?date}
                    </span>
                <div class="price"><b>${it.money?c}</b>元</div>
                <p>使用范围：<#if it.type == '1' || it.type == '2'>不限品种，仅限15秒</#if><#if it.type == '3'>全场通用</#if></p>
                <p>有效时间：
                    <#if it.type == '1'>3</#if><#if it.type == '2'>30</#if><#if it.type == '3'>7</#if>天　　

                    <#if it.status == 1>（已使用）</#if>
                    <#if it.status == 2>（已过期）</#if>
                </p>
                <div class="right-name"><#if it.type == '1'>必盈券</#if><#if it.type == '2'>直盈券</#if><#if it.type == '3'>增益券</#if>
                    </div>
                <div class="y-uan"></div>
                <div class="s-liang"><b>${it.num?c}</b><small>张</small></div>
            </li>
        </ul>
    </div>
</#list>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
    <script>
        var total = ${page.total};
        var start = 0;
        var rows = 3;
        start = total > start + rows ? start + rows : start;

        $('#pager1').text('加载中...');
        function _more(){
            $.ajax({
                url: ctx + '/coupon/list',
                data: {
                    start: start
                },
                success: function(page){
                    start = page.total > start ? start + rows : start;
                    var list = page.data;
                    var html = [];
                    var it = null;
                    for(var i = 0; i < list.length; i++){
                        it = list[i];
                        html.push('<div class="voucher-box" onclick="couponInfo('+it.type+','+it.money+','+it.num+',\''+it.createTime.substring(0, 10)+'\',\''+it.endDate.substring(0, 10)+'\')">');
                        html.push('<ul>');
                        html.push('<li style="'+(it.status != '0' ? 'background-color: gray;' : '')+'">');
                        html.push('<span >'+it.createTime.substring(0, 10));
                        html.push(it.status == 1 ? '（已使用）' : (it.status == 2 ? '（已过期）' : ''));
                        html.push('</span>');
                        html.push('<div class="price"><b>'+it.money+'</b>元</div>');
                        html.push('<p>使用范围：'+(it.type == '1' || it.type == '2' ? '不限产品、限选15秒交易' : '全场通用')+'</p>');
                        html.push('<p>有效时间：'+(it.type == '1' ? 3 : (it.type == '2' ? 30 : 7))+'天</p>');
                        html.push('<div class="right-name">'+(it.type == '1' ? '必盈券' : (it.type == '2' ? '直盈券' : '增益券'))+'</div>');
                        html.push('<div class="y-uan"></div>');
                        html.push('<div class="s-liang"><b>'+it.num+'</b><small>张</small></div>');
                        html.push('</li>');
                        html.push('</ul>');
                        html.push('</div>');
                    }
                    $(html.join('')).insertAfter($('.voucher-box').last());
                    if(page.total <= start){
                        $('#pager1').hide();
                        $('#pager2').show();
                    }else{
                        $('#pager1').text('加载更多');
                    }
                }
            });
        }

        /*查看代金券信息*/
        function couponInfo(type, money, num, createTime, endTime){
            window.location.href = ctxWap + '/user/couponInfo?type=' + type +'&money=' + money + '&num=' + num + '&createTime=' + createTime + '&endTime=' + endTime+'&v='+Math.random();
        }
    </script>
</div>
<a id="pager1" class="bottom_more" style="<#if page.total gt page.data.size()>display: block;<#else>display: none;</#if>" onclick="_more();">加载更多</a>
<a id="pager2" class="bottom_no_more" style="<#if page.total lte page.data.size()>display: block;<#else>display: none;</#if>">没有更多了~</a>
<div class="loader">
    <div class="act">
        <div class="spinner">
            <div class="double-bounce1"></div>
            <div class="double-bounce2"></div>
        </div>
    </div>
    <div class="bg"></div>
</div>
</body>
</html>




