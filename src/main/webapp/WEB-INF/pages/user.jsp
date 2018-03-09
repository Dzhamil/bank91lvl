<%@ page import="bank.db.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bank.db.entity.UserData" %>
<%@ page import="bank.db.entity.BankTransfer" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link href="/resources/css/style_main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <p class="title">страница пользователя</p>
    <form  method="post" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" name="logoutForm" value="выход">
    </form>
    <div class="users_info3">
        <div class="users_info2">
        <h2>Открытие счета</h2>
            <h3 class="infoOpenAccount1">
        <%if(request.getAttribute("requestBankAccount")==null&&request.getAttribute("userBankAccount")==null){
        %>У вас нет открытого счета в банке, нажмите чтобы сделать заявку</h3>
        <form action="/openBankAccount"  method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" name="openBankAccount" value="Открыть счет"/>
        </form>
        <%}%>
        <h2 class="infoOpenAccount">
        <%if(request.getAttribute("requestBankAccount")!=null&&request.getAttribute("userBankAccount")==null){
        %>У вас нет открытого счета в банке, но вы уже отправили заявку, ожидайте<%
        }%>

        <%if(request.getAttribute("requestBankAccount")==null&&request.getAttribute("userBankAccount")!=null){
        %>Нельзя открыть более одного счета в банке<%
        }%>
        </h2>
    </div>

    <div class="users_info2">
        <h2>Закрытие счета</h2>
        <%if(request.getAttribute("requestBankAccount")==null&&request.getAttribute("userBankAccount")!=null){
            BankAccount bankAccount = (BankAccount) request.getAttribute("userBankAccount");
            if(bankAccount.isStatus()){
                if(bankAccount.getSum()!=0){%> Вы не можете закрыть счет, счет должен быть = 0 едениц(снимите деньги со счета)
        <%}else { %>
        <form action="/closeBankAccount"  method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" name="closeBankAccount" value="Закрыть счет"/>
        </form>
        <%}
        }else %> вы уже подали заявку на закрытие, ожидайте
        <%}%>

        <%if(request.getAttribute("userBankAccount")==null){
        %>У вас нет открытого счета в банке, чтобы что - то закрывать<%
        }%>
    </div>
    <div class="users_info2">
        <h2>Информация о счете</h2>
        <% if(request.getAttribute("userBankAccount")!=null){
            BankAccount bankAccount = (BankAccount) request.getAttribute("userBankAccount");
        %>
        <tr>
            <td> <p class="bankSum"> Баланс лицевого счета= <%=bankAccount.getSum()%> </p>> </td>
            <td> Владелец счета :
                <%=bankAccount.getUser_data().getUser_personal().getFirst_name()%>
                <%=bankAccount.getUser_data().getUser_personal().getLast_name()%>
                <%=bankAccount.getUser_data().getUser_personal().getSecond_name()%>
            </td>
            <td>
                <form action="/putMoney"  method="post" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="text" value="" name="sumP"/>
                    <input type="submit" name="putMoney" value="Положить на счет"/>
                    <font color="#a52a2a">
                        <h1>${message}</h1>
                    </font>
                </form>
                <form action="/getMoney"  method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="text" value="" name="sumG">
                    <input type="submit" name="getMoney" value="Снять со счета"/>
                    <font color="#a52a2a">
                        <h1>${message1}</h1>
                    </font>
                </form>
            </td>
        </tr>
        <%}else %> откройте пожалуйста счет
    </div>
</div>
    <div class="users_info4">
        <div class="users_info">
            <h2>Клиенты Банка</h2>
                <style type="text/css">
                    .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
                    .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
                    .tftable tr {background-color:#ffffff;}
                    .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
                    .tftable tr:hover {background-color:#ffff99;}
                </style>

                <table class="tftable" border="1">
                    <tr>
                        <th>№</th>
                        <th>Имя</th>
                        <th>Отчество</th>
                        <th>Фамилия</th>
                        <th>Перевод</th>
                    </tr>
                    <% int numerPosition=0; %>
                    <% if(request.getAttribute("bankAccountList")!=null){%>
                    <% for (BankAccount bankAccount : (ArrayList<BankAccount>)request.getAttribute("bankAccountList")) { %>
                    <tr>
                        <td><%=++numerPosition%></td>
                        <td><%=bankAccount.getUser_data().getUser_personal().getFirst_name()%></td>
                        <td><%=bankAccount.getUser_data().getUser_personal().getLast_name()%></td>
                        <td><%=bankAccount.getUser_data().getUser_personal().getSecond_name()%></td>
                        <td>
                            <%if(request.getAttribute("userBankAccount")!=null){
                                BankAccount bankAccount1 = (BankAccount) request.getAttribute("userBankAccount");
                            %>
                            <% if(bankAccount.getId()!=bankAccount1.getId()){%>
                            <form action=<%=request.getContextPath() +"/newTransfer"%> method="post">
                                <input type="submit" name="submitTransfer" value="заказать перевод"/>
                                <input type="text" name="sumT"/>
                                <%UserData userData = (UserData) request.getSession().getAttribute("userData");
                                    String id_user_data = String.valueOf(userData.getId());
                                    String id_payeeBankAccount = String.valueOf(bankAccount.getId());
                                %>
                                <input type="hidden" value="<%=id_user_data%>" name="id_user_data">
                                <input type="hidden" value="<%=id_payeeBankAccount%>" name="id_payeeBankAccount"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <font color="#a52a2a">
                                    <h1 class="operationTransfer">${transfer}</h1>
                                </font>
                            </form>
                        </td>
                    </tr>
                    <%}}}}else %> В настоящее время в банке нет открытых счетов
                </table>
        </div>
        <div class="users_info">
            <h2>Ожидаемые переводы(проверяются администратором)</h2>
            <table class="tftable" border="1">
                <tr>
                    <th>№</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Фамилия</th>
                    <th>Статус</th>
                </tr>
                <% if(request.getAttribute("bankTransfers")!=null){%>
                <% for (BankTransfer bankTransfer : (ArrayList<BankTransfer>)request.getAttribute("bankTransfers")) { %>
                <tr>
                    <td><%=++numerPosition%></td>
                    <td><%=bankTransfer.getBank_account().getUser_data().getUser_personal().getFirst_name()%></td>
                    <td><%=bankTransfer.getBank_account().getUser_data().getUser_personal().getLast_name()%></td>
                    <td><%=bankTransfer.getBank_account().getUser_data().getUser_personal().getSecond_name()%></td>

                    <td><%if(!bankTransfer.isStatus())%>заявка суммой = <%=bankTransfer.getSum()%> обрабатывается</td>
                </tr>
                <%}
                }else %> У вас нет заявок в настоящее время
            </table>
        </div>
    </div>
</div>
</body>
</html>
