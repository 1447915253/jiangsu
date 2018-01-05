<!-- 定义变量 -->
<#assign cp = "${request.contextPath}"/>
<#assign ctx = "${request.contextPath}/v1"/>
<#assign ctxStatic = "${request.contextPath}/wap"/>
<#assign ctxWap = "${request.contextPath}/wap"/>
<#if _title??>
    <#assign title = _title/>
<#else>
    <#assign title = '恒禾商贸'/>
</#if>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<#if request.serverPort == 80>
    <#assign domain = "${request.scheme}://${request.serverName}"/>
</#if>
<#if request.serverPort != 80>
    <#assign domain = "${request.scheme}://${request.serverName}:${request.serverPort?c}"/>
</#if>
<link href="${ctxStatic}/asserts/layer_mobile/need/layer.css" rel="stylesheet" type="text/css"/>

<!-- js中定义变量 -->
<script type="text/javascript">var cp = '${cp}', ctx = '${ctx}', ctxWap = '${ctxWap}', ctxStatic='${ctxStatic}', domain = '${domain}';</script>