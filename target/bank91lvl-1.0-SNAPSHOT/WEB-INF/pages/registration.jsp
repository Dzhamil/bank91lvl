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
<title>Регистрация</title>
<link href="/resources/css/style_register.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="а1">
<div class="login_div">
    <p class="title">Введите свои данные:</p>
        <form class="login_form" name="auth" action="/registrattion_send" method="post">
            <p class="text">Имя:<input type="text" name="first_name" value="" size="20"/></p>
            <p class="text">Фамилия: <input type="text" name="second_name" value="" size="20"/></p>
            <p class="text">Отчество: <input type="text" name="last_name" value="" size="20"/></p>
            <p class="text">Логин: <input type="text" name="login" value="" size="20"/></p>
            <p class="text">Пароль: <input type="text" name="password" value="" size="20"/></p>
            <p class="text">Повторите пароль: <input type="text" name="confirmPassword" value="" size="20"/></p>
            <p class="text">Дата регистрации: <input type="text" name="data_reg" value="" size="20"/></p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p class="text"> <input type="submit" name="submitRegister" value="отправить"/> </p>
            <font color="#a52a2a">
                <h1 class="logerr">${loginError}</h1>
            </font>
            <font color="#a52a2a">
                <h1 class="passerr">${passError}</h1>
            </font>
        </form>
    <form method="post" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" name="comeback" value="comeback">
    </form>
</div>
</div>
</body>
</html>
