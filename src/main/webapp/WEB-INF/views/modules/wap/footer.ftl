<#if footer??>
<!--footer-->
<nav class="bar bar-tab index" id="bar-tab">
    <a href="javascript:<#if footer == "trade">void(0);</#if><#if footer != "trade">toTradePage();</#if>" class="tab-item jy">
        <span class="icon icontu icon-jy<#if footer == "trade">-active</#if>"></span>
        <span class="tab-label jy<#if footer == "trade">-active</#if>">交易</span>
    </a>
    <a href="javascript:<#if footer == "hold">void(0);</#if><#if footer != "hold">toInvitePage();</#if>" class="tab-item cc">
        <span class="icon icontu icon-cc<#if footer == "hold">-active</#if>"></span>
        <span class="tab-label cc<#if footer == "hold">-active</#if>">邀请</span>
    </a>
    <a href="javascript:<#if footer == "corps">void(0);</#if><#if footer != "corps">toCorpsPage();</#if>" class="tab-item jt">
        <span class="icon icontu icon-jt<#if footer == "corps">-active</#if>"></span>
        <span class="tab-label jt<#if footer == "corps">-active</#if>">战队</span>
    </a>
    <a href="javascript:<#if footer == "user">void(0);</#if><#if footer != "user">toUserPage();</#if>" id="me label" class="tab-item">
        <span class="icon icontu icon-me<#if footer == "user">-active</#if>"></span>
        <span class="tab-label me<#if footer == "user">-active</#if>">个人中心</span>
    </a>
</nav>
</#if>
<script type="application/javascript" charset="utf-8">
    function toHomePage(){
        window.location.replace("${ctxWap}/home?r="+Math.random());
    }
    function toTradePage(){
        window.location.replace("${ctxWap}/trade?r="+Math.random());
    }
    function toHoldTradePage(){
        window.location.replace("${ctxWap}/trade/hold?r="+Math.random());
    }
    function toInvitePage() {
        window.location.replace("${ctxWap}/invite?r="+Math.random());
    }
    function toUserPage(){
        window.location.replace("${ctxWap}/user?r="+Math.random());
    }
    function toCorpsPage() {
        window.location.replace("${ctxWap}/corps?r="+Math.random());
    }
</script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.min.js"></script>
<script type="text/javascript"  src="${ctxStatic}/js/md5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/asserts/layer_mobile/layer.src.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/tools.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
