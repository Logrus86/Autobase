<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="/static/css/style.css" rel="stylesheet" media="screen">
<head>
    <title>autobase.com</title>
</head>
<body>
<div id="header">
    <div class="container">
        <a class="logo-header" title="Заказ автотранспорта - autobase.com" itemprop="url"
           href="http://localhost:8080/do/main">
            <img itemprop="logo" src="/static/img/logo.jpg" alt="Заказ автотранспорта - autobase.com">
        </a>

    </div>
</div>

<div id="sub" class="lifestyle_img">
    <div class="content">
        <p id="count">Более <em>200</em> наименований транспортных едениц</p>

        <div id="title">
            <h1 style="font-size: 47px;">Услуги автотранспорта по лучшим ценам!</h1>
        </div>
        <div id="subTitle">
            <p id="h1sub" style="font-size: 16px;">Почасовой и посуточный найм легковых и грузовых
            автомобилей, а также пассажирских автобусов</p>
        </div>
    </div>
</div>
<%-- how to insert this to title ?  <fmt:message key="default.rent_transport"></fmt:message>  --%>
</body>
</html>
