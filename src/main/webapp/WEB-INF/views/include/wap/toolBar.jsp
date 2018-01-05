<%--
  Created by IntelliJ IDEA.
  User: lichq
  Date: 2016-07-05
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<!-- 工具栏 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="bar bar-tab">
    <a onclick="gotoToolPage('1',this)" id="syBtn" class="tab-item external" href="#">
        <span class="icon "><img width="50%" src="${ctxStatic}/img/icon_home@2x.png"/></span>
        <span class="tab-label">首页</span>
    </a>
    <%--<a onclick="gotoToolPage('2',this)" id="wzBtn" class="tab-item external" href="#">--%>
        <%--<span class="icon"><img width="50%" src="${ctxStatic}/img/icon_chat_double@2x.png"/></span>--%>
        <%--<span class="tab-label">问诊</span>--%>
    <%--</a>--%>
    <a onclick="gotoToolPage('3',this)" id="gwcBtn" class="tab-item external" href="#">
        <span class="icon"><img width="50%" src="${ctxStatic}/img/icon_cart@2x.png"/></span>
        <span class="tab-label">购物车</span>
    </a>
    <a onclick="gotoToolPage('4',this)" id="myBtn" class="tab-item external" href="#">
        <span class="icon"><img width="50%" src="${ctxStatic}/img/icon_mine@2x.png"/></span>
        <span class="tab-label">我的</span>
    </a>
</nav>