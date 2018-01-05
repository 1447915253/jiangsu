<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <#include "/wap/header.ftl" >
    <link href="${ctxStatic}/all/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/zhuce.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/yonghuxieyi.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/xaidan.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/all/css/zhucecuowu.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"  src="${ctxStatic}/all/js/rem.js"></script>
    <title>${title}</title>
</head>
<body>
<section class="wrap-page">
    <div class="login_name" style=""></div>
    <div class="user_top">
        <form id="regist_form" action="${ctxWap}/reg" METHOD="post" enctype="application/x-www-form-urlencoded">
            <#--<div class="input_hidden w-item clearfix ">
                账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp; |
                <input type="hidden"  name="chnName" class="pwd" data-validtype="require" value="18666666666">
            </div>-->
<#--            <div class="w-item clearfix">
                身份证号&nbsp; |
                <input type="text" placeholder="请输入18位身份证号" name="pwd" class="pwd" required>
            </div>
            <div class="w-item clearfix">
                邮箱(选填)&nbsp;|
                <input type="text" placeholder="请输入邮箱地址" name="pwd" class="pwd" required>
            </div>-->
            <div class="w-item clearfix">
                手机号码&nbsp; |
                <input type="text" placeholder="请输入手机号码" name="mobile" class="pwd" data-validtype="require">
            </div>
           <#-- <div class="w-item tianxie clearfix">
                <div class="w-item yanzhen clearfix">
                    验&nbsp; 证&nbsp;码&nbsp; |
                    <input type="text" placeholder="请输入验证码" name="randomCode" class="pwd" data-validtype="require">
                </div>
                &lt;#&ndash;<input class="fill right" type="button" id="btn" value="R T  Q I">&ndash;&gt;
                <img style="height:1rem;border-radius:5px 5px 5px 5px;right -100px;width: 2.3rem;    float: right;" id="randomCodeImage" src="${ctx}/validate" onclick="fresh()"/>
            </div>-->
            <div class="w-item huoqu clearfix">
                <div class="w-item yanduanx clearfix">
                    验&nbsp; 证&nbsp;码&nbsp; |
                    <input type="text" placeholder="请输入短信验证码" name="validCode" class="pwd" data-validtype="require">
                </div>
                <input class="green right" type="button" id="btn" value="获取验证码" onclick="sendSms(this)">
            </div>
            <div class="w-item clearfix">
                输入密码&nbsp; |
                <input type="password" placeholder="请输入登录密码" name="tradePassword" class="pwd" data-validtype="require">
            </div>
            <div class="w-item clearfix">
                确认密码&nbsp; |
                <input type="password" placeholder="请再次输入登录密码" class="pwd" data-validtype="same">
            </div>
            <#assign aaaa=RequestParameters.ah!'false' />
            <#if aaaa != 'true'>
                <div class="w-item clearfix" <#if '${regBean.agentInviteCode}'?? && '${regBean.agentInviteCode}' != ''> style="display: none;" </#if> >
                    邀请码&nbsp;&nbsp;&nbsp; |
                    <input type="text" placeholder="请输入邀请码" name="agentInviteCode" class="text" data-validtype="require" value="${regBean.agentInviteCode}">
                    <input type="hidden" name="userId" value="${regBean.userId}">
                </div>
            </#if>
            <div class="fuxuan clearfix">
                <input type="checkbox" class="checkbox" id="readUserPro">我已阅读并同意<span onclick="showAgreement()">《我们的条款》</span>
                <input type="hidden" value="${RequestParameters.thirdReg!'false'}" name="thirdReg"/>
            </div>
        </form>
        <button class="login" type="button" onclick="formSubmit()">提交</button>
    </div>
</section>
<!--错误弹框-->
<div class="popBox" id="errorBox" style="display:none">
    <div class="popCont" id="popCont">
        <div class="pop_bd f36" style="height:4.6rem;margin-top: 3rem;">
            <div class="tishi miss" style="border-radius:0;border-bottom:1px solid #2b2a2a;">
                <div class="tishi_text point left"><i class="img image"></i>提示</div>
                <div class="close" style="top:0rem;" onclick="hidePrompt()"><img src="${ctxStatic}/all/image/q_03.png"></div>
            </div>
            <p class="xiadan_num xiadan_no">填写完整信息！</p>
            <button class="sure right fill_sure" onclick="hidePrompt()">确认</button>
        </div>
    </div>
</div>
<!--阅读条款弹出框-->
<div class="popBox" style="display: none">
    <div class="popCont" style="top:0;">
        <div class="pop_bd f36" style="height:13.2rem; width: 8rem;">
            <div class="tishi">
                <p class="tishi_text">用户协议</p>
                <div class="close"onclick="hideBox()"><img src="${ctxStatic}/all/image/q_03.png" class="yong_close"></div>
            </div>
            <p class="xieyi xiadan_no">
尊敬的客户：<br>
                ${title}业务是一种潜在收益和潜在风险较高的交易业务,对客户的风险承受能力、理解风险的程度、风险控制能力以及交易经验有较高的要求。<br>
                客户签署本协议，代表其对未来进行大宗商品微交易业务的风险已有了充分的认知与了解。<br>
                本风险揭示书（以下简称“揭示书”）旨在向客户充分揭示大宗商品微交易业务的风险,并且帮助客户评估和确定自身的风险承受能力。本揭示书披露了交易过程中可能发生的各种风险因素，但是鉴于市场的多变和复杂，本揭示书无法穷尽一切与大宗商品交易有关的风险。鉴于大宗商品微交易业务风险的存在，在签订入市协议及进行交易前，客户应该仔细阅读本揭示书，并确保自己已经充分理解有关交易的性质、规则；并依据自身的交易经验、目标、财务状况、承担风险能力等自行决定是否参与该交易。<br>
                若客户对于本揭示书有不理解或不清晰的地方，应该及时咨询相关的机构，对揭示书不存在异议时请亲自确认打勾。<br>
                一、温馨提示<br>
                1、${title}业务具有高投机性和高风险性，不适合用养老基金、债务资金（如银行贷款、借款等负债）等进行交易的客户。<br>
                2、${title}业务只适合于满足以下全部条件的客户：<br>
                （1）年满18周岁（含），且60周岁（含）以内，并具有完全民事行为能力的中国公民或依法在中华人民共和国境内注册成立的企业法人。<br>
                （2）能够充分理解有关此交易的一切风险，并且具有风险承受能力。<br>
                （3）因交易失误而造成账户资金部分或全部损失、仍不会改变其正常的生活方式或影响其正常生产经营状况的。<br>
                二、相关的风险揭示<br>
                1、政策风险<br>
                国家法律、法规、政策以及规章的变化，政府主管部门紧急措施的出台，相关监管部门监管措施的实施，交易中心规则制度的修改等原因，均可能会对客户的交易产生影响，客户必须承担由此导致的一切损失。<br>
                2、价格波动的风险。<br>
                本市场的交易品种作为一种特殊的具有交易价值的商品，其价格受多种因素的影响（包括但不限于国际经济形势、相关市场走势、政治局势、自然因素），这些因素对本市场的客户品价格的影响机制非常复杂，客户在实际操作中难以全面把握，因而存在出现交易失误的可能性，如果不能有效控制风险，则可能遭受较大的损失，客户必须独自承担由此导致的一切损失。<br>
                3、技术风险。<br>
                此业务通过电子通讯技术和互联网技术来实现。有关通讯服务及软、硬件服务由不同的供应商提供，可能会存在品质和稳定性方面的风险；交易中心及其综合会员不能控制电讯信号的强弱，也不能保证客户客户端的设备配置或连接的稳定性以及互联网传播和接收的实时性。故由以上通讯或网络故障导致的某些服务中断或延时可能会对客户的交易产生影响。另外，客户的电脑系统亦有可能被病毒和/或网络黑客攻击，从而使客户的交易决策无法正确和/或及时执行。对于上述不确定因素的出现也存在着一定的风险，有可能会对客户的交易产生影响，客户应该充分了解并承担由此造成的全部损失。<br>
                4、交易风险。<br>
                （1）客户需要了解交易中心的大宗商品微交易业务具有低订金的特点，可能导致快速的盈利或亏损。若与交易方向与行情的波动相反，会造成较大的亏损，根据亏损的程度，客户必须有条件满足随时追加账户可用资金的要求，否则其持有合同将会被强行转让，客户必须承担由此造成的全部损失。<br>
                （2）交易系统商品报价以系统实时成交价为基准，该价格可能会与其他市场的商品价格存在微弱的差距，交易中心并不能保证上述交易价格与其他市场保持完全的一致性。<br>
                （3）交易中心提供的微交易业务，可能会存在以下风险：由于电脑设备运行速度、网络传输速度等原因，可能会导致在行情触及或穿越指定价格时，交易系统没有为客户成交订立或平仓，客户的交易没有达成。因此，特别提示客户在使用交易系统中微交易业务时，应充分考虑上述风险。如果客户无法理解或承受上述风险，建议客户不要进行交易。如果客户坚持进行微交易的交易业务，将认为客户已经完全理解并愿意承担全部风险，并愿意承担因此所带来的一切后果。<br>
                （4）客户在交易系统内，通过网上终端所提交的交易业务一经成交，即不可撤销，客户必须接受这种方式可能带来的风险。<br>
                （5）交易中心、综合会员及其工作人员不会对客户作出获利保证，并且不会与客户分享收益或共担风险。客户应知晓针对大宗商品微交易业务的任何获利承诺或者不会发生亏损的承诺均为不可能或者没有根据的。由此而造成的交易风险由客户自行承担。<br>
                （6）客户的成交单据必须建立在自主决定的基础之上。交易中心、综合会员及其工作人员提供的任何关于市场的分析和信息，仅供客户参考，不构成任何要约或承诺。由此而造成的交易风险由客户自行承担。<br>
                （7）在电子交易的过程中，有可能出现偶然性的明显的错误报价，交易中心可能事后会对错价及错价产生的盈亏作出纠正，由此而造成的交易风险由客户自行承担。<br>
                5、不可抗力风险。<br>
                任何因${title}不能够控制的原因，包括但不限于火灾、爆炸、台风、洪水、地震、潮汐、雷电、战争、供电、电信及通信设备中断、罢工、政府管制、法规政策变更、交易中心暂停交易、交易中心规则中规定的交易异常、设备故障、通讯故障、互联网系统故障及通过互联网未经许可的存取、盗窃交易敏感性数据、他人恶意地对网站攻击、计算机病毒及非可归因于任何一方的信息传递中断等其他无法预测或防范的不可抗力事件，都有可能会对客户的交易产生影响，客户应该充分了解并承担由此造成的全部损失。<br>
                三、特别提示<br>
                1、客户在参与交易之前务必详尽地了解大宗商品交易的基本知识和相关风险以及${title}有关的业务规则等，依法合规地从事大宗商品微交易业务。<br>
                2、交易中心为了确保市场“公开、公平、公正”和健康稳定地发展，将采取更加严格的措施，强化市场的监管。请您务必密切地关注有关该市场的公告、风险提醒（包括但不限于交易场所发布公告、官网公告）等信息，及时了解市场风险状况，做到理性交易，切忌盲目跟风。<br>
                3、客户在开通交易账户之前，请配合综合会员开展客户的适当性管理工作，完整、如实地提供开户所需要的信息，不得采取弄虚作假等手段规避有关的要求。否则，交易中心可以拒绝为其开通交易账户服务。<br>
                4、本揭示书上述风险揭示事项仅为列举性质，未能详尽的列明有关该市场的所有风险因素，客户在参与该市场交易之前，还应认真的对其他可能存在的风险因素有所了解和掌握。<br>
                5、我们诚挚地希望并建议客户，从风险承受能力等自身实际情况出发，审慎地决定是否参与此市场的交易，合理的配置自己的金融资产。<br>
                本人已认真阅读以上风险说明并完全理解和同意，自愿承担由此造成的风险，以及由此带来的一切可能的损失。<br>
            </p>
            <button class="sure right" onclick="hideBox()">确认</button>
        </div>
    </div>
</div>
<#include "/wap/footer.ftl">
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script src="${ctxStatic}/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript">
    <#if errors??>
        layer.msg("${errors.error}");
        $(".clearfix").css('display','block');
    </#if>
function showAgreement() {
    $('.popBox').show();
}
function hideBox() {
    $('.popBox').hide();
}
function hidePrompt() {
    $('#errorBox').hide();
}
function sendSms(the) {
    var mobile = $('input[name="mobile"]').val();
    if(!/^[1-9]{1}[0-9]{10}$/.test(mobile)){
        layer.msg("手机号不合法");
        return;
    }
    sendSMSValidCode('reg', mobile, function(data){
        settime(the);
    });
}
function formSubmit() {
    if(reg() == true){
        document.getElementById("regist_form").submit();
    }
}
function reg(){
    if(!$('#regist_form').valid()){
        return false;
    }
    if($('#readUserPro:checked').length == 0){
        layer.msg("请阅读协议，然后勾选同意！");
        return false;
    }
    return true;
}
    function fresh() {
        var imgSrc = $("#randomCodeImage");
        var src = imgSrc.attr("src");
        imgSrc.attr("src",chgUrl(src));
    }

    function chgUrl(u) {
        var timestamp = (new Date()).valueOf();
        url = u.substring(0,17);
        if((u.indexOf("&")>=0)){
            url = u + "×tamp=" + timestamp;
        }else{
            url = u + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</body>
</html>