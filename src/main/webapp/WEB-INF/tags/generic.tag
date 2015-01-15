<%@ tag import="com.epam.bp.autobase.entity.User" %>
<%@ tag import="com.epam.bp.autobase.entity.Vehicle" %>
<%@ tag import="com.epam.bp.autobase.entity.Order" %>
<%@tag description="Generic Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="hide_footer" fragment="true" %>

<html>
<body>
<div id="header">
    <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/static/js/bp.js"/>'></script>
    <link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
    <link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" media="screen">
    <link href='<c:url value="/static/css/bootstrap-select.css"/>' rel="stylesheet" media="screen">
    <link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/x-icon">

    <div id="locale_changer" class="dropdown">
        <button class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
            <c:if test="${locale.language=='ru'}"><img src='<c:url value="/static/img/RU.png"/>'/></c:if>
            <c:if test="${locale.language=='en'}"><img src='<c:url value="/static/img/EN.png"/>'/></c:if>
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=ru"><img
                    src='<c:url value="/static/img/RU.png"/>'/> Русский</a></li>
            <li role="option"><a role="menuitem" tabindex="-1" href="locale?locale=en"><img
                    src='<c:url value="/static/img/EN.png"/>'/> English</a></li>
        </ul>
    </div>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="footer" align="center" <jsp:invoke fragment="hide_footer"/>>
    EPAM Systems © 2014<br>
    Bobylev P.R. © 2014
</div>
</body>
</html>