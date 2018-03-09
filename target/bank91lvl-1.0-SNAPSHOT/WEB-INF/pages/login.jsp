<%@ page import="org.springframework.security.web.csrf.CsrfToken" %>
<%@ page import="static bank.controllers.AuthController.csrfForTest" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link href="/resources/css/style_index.css" rel="stylesheet" type="text/css">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</head>

    <body>
        <div  class="main">
            <div class="content">
                <p class="title">Тестовое задание для сбертеха</p>
            </div>
            <div class="login_div">
                <p class="title">Введите свои данные или зарегистрируйтесь:</p>

                <form class="login_form"  action=<%=request.getContextPath() +"/registration"%> method="get">
                    <input type="submit" name="register" value="зарегистрироваться"/>
                </form>
                <form class="login_form" name="auth" action="/login_submit" method="post">
                    <p>Имя:<input type="text" name="login" value="" size="20"/></p>
                    <p>Пароль: <input type="password" name="password" value="" size="20"/></p>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                   <% CsrfToken csrf = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
                       csrfForTest = csrf.getToken();
                   %>

                    <p> <input type="submit" name="submitLogin" value="войти"/> </p>
                </form>
            </div>
            <div class="footer">
                Разработчик: Рахимов Джамиль 2018г.
            </div>
        </div>
    </body>
</html>