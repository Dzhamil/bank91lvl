<%--
Created by IntelliJ IDEA.
User: Джамиль
Date: 25.01.2018
Time: 20:25
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welocome</title>
    <link href="/resources/css/style_register.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="а1">
    <div class="login_div">
        <p class="title">нажмите для входа</p>
        <form method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" name="logoutForm" value="выйти">
        </form>
        <form class="login_form"  action="/user" method="post">
            <p class="text"> <input type="submit" name="userPanelButton" value="Панель управления пользователя"/> </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <font color="#a52a2a">
                <h1>${errDenided}</h1>
            </font>
        </form>
        <form class="login_form"  action="/admin" method="post">
            <p class="text"> <input type="submit" name="adminPanelButton" value="Панель управления администратора"/> </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <font color="#a52a2a">
                <h1>${errDenided}</h1>
            </font>
        </form>
    </div>
</div>
</body>
</html>
