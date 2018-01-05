<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/v1"/>
<c:set var="ctxApi" value="${pageContext.request.contextPath}/api"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctxWap" value="${pageContext.request.contextPath}/wap"/>
<c:choose>
    <c:when test="${pageContext.request.serverPort =='80'}">
        <c:set var="domain"
               value="${pageContext.request.scheme}://${pageContext.request.serverName}"/>
    </c:when>
    <c:otherwise>
        <c:set var="domain"
               value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}"/>
    </c:otherwise>
</c:choose>