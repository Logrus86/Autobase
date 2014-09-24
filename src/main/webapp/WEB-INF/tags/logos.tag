<%@tag description="header and second logo. user panel must contain login-bae or welcome-bar" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user_panel" fragment="true" %>

<fmt:bundle basename="i18n.text">
    <div id="header">
        <div class="container">
            <a title="<fmt:message key="default.ordering"/>" href='<c:url value="/"/>'><img src='<c:url value="/static/img/logo.jpg"/>'/></a>
            <jsp:invoke fragment="user_panel"/>
            <div class="msg-error">${errormsg}</div>
        </div>
    </div>
    <div id="sub" class="lifestyle_img">
        <div class="content">
            <p id="count"><fmt:message key="default.vhcount"/></p>

            <div id="title">
                <h1 style="font-size: 45px;"><fmt:message key="default.bestprices"/></h1>
            </div>
            <div id="sub-title">
                <p id="h1sub" style="font-size: 16px;"><fmt:message key="default.service-desc"/></p>
            </div>
        </div>
    </div>
</fmt:bundle>