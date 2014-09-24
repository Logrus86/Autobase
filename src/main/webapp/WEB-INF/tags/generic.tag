<%@tag description="Generic Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="hide_footer" fragment="true" %>

<html>
<fmt:bundle basename="i18n.text">
    <body>
    <div id="pageheader">
        <html>
        <script type='text/javascript' src='<c:url value="/webjars/jquery/1.11.1/jquery.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js"/>'></script>
        <script type='text/javascript' src='<c:url value="/static/js/bootstrap-select.js"/>'></script>
        <link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" media="screen">
        <link href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>' rel="stylesheet" media="screen">
        <link href='<c:url value="/static/css/bootstrap-select.css"/>' rel="stylesheet" media="screen">
        <link href='<c:url value="/static/img/favicon.ico"/>' rel="icon" type="image/png">
        <head>
            <title>autobase.com</title>
        </head>
        </html>
    </div>
    <div id="body">
        <jsp:doBody/>
    </div>
    <div id="footer" align="center" <jsp:invoke fragment="hide_footer"/>>
        EPAM Systems © 2014<br>
        Bobylev P.R. © 2014
    </div>
    </body>
</fmt:bundle>
</html>