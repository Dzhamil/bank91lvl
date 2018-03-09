<%@ page import="bank.db.entity.BankAccount" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bank.db.entity.RequestBankAccount" %>
<%@ page import="bank.db.entity.BankTransfer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <meta charset="utf-8">
    <link href="/resources/css/style_main.css" rel="stylesheet" type="text/css">

</head>
    <body>
        <div class="users_info6" >
            <p class="title">страница администратора</p>
            <form  method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" name="logoutForm" value="выход">
            </form>

            <div class="users_info5">
                <h2>Все пользователи</h2>

                <style type="text/css">
                    .tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #9dcc7a;border-collapse: collapse;}
                    .tftable th {font-size:12px;background-color:#abd28e;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;text-align:left;}
                    .tftable tr {background-color:#ffffff;}
                    .tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #9dcc7a;}
                    .tftable tr:hover {background-color:#ffff99;}
                </style>

                <table class="tftable" border="1">
                    <tr>
                        <th>id user</th>
                        <th>login</th>
                        <th>password</th>
                        <th>registration data</th>
                        <th>money</th>
                        <th>account status</th>
                        <th>full name</th>
                    </tr>

                    <% if(request.getAttribute("bankAccountList")!=null){%>
                    <% for (BankAccount bankAccount : (ArrayList<BankAccount>)request.getAttribute("bankAccountList")) { %>
                    <tr>
                        <td><%=bankAccount.getUser_data().getId()%></td>
                        <td><%=bankAccount.getUser_data().getLogin()%></td>
                        <td><%=bankAccount.getUser_data().getPassword()%></td>
                        <td><%=bankAccount.getUser_data().getDate_reg()%></td>
                        <td><%=bankAccount.getSum()%></td>
                        <td><%=bankAccount.isStatus()%></td>
                        <td>
                            <%=bankAccount.getUser_data().getUser_personal().getFirst_name()%>
                            <%=bankAccount.getUser_data().getUser_personal().getLast_name()%>
                            <%=bankAccount.getUser_data().getUser_personal().getSecond_name()%>
                        </td>
                    </tr>
                    <%}}%>
                </table>
            </div>
            <div class="users_info5">
                <font color="#a52a2a">
                    <h2 class="messageOk">${messageOk}</h2>
                </font>
                <font color="#a52a2a">
                    <h2 class="messageCancel">${messageCancel}</h2>
                </font>

                <h2>Заявки на открытия счетов</h2>
                <table class="tftable" border="1">
                    <tr>
                        <th>id_request </th>
                        <th>id_user</th>
                        <th>action</th>
                    </tr>
                    <% if(request.getAttribute("requestBankAccountList")!=null){%>
                    <% for (RequestBankAccount requestBankAccount :
                            (ArrayList<RequestBankAccount>)request.getAttribute("requestBankAccountList")) {

                        String id_user_data = String.valueOf(requestBankAccount.getUser_data().getId());
                        String id_requestBankAccount = String.valueOf(requestBankAccount.getId());
                    %>
                    <tr>
                        <td><%=requestBankAccount.getId()%></td>
                        <td><%=requestBankAccount.getUser_data().getId()%></td>
                        <td>
                            <form action="/acceptOpenAccount" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" name="acceptOpenAccount" value="подтвердить открытие"/>
                                <input type="hidden" value="<%=id_user_data%>" name="id_user_data"/>
                                <input type="hidden" value="<%=id_requestBankAccount%>" name="id_requestBankAccount"/>
                            </form>

                            <form action="/cancelOpenAccount" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" value="<%=id_requestBankAccount%>" name="id_requestBankAccount"/>
                                <input type="submit" name="cancelOpenAccount" value="отклонить открытие"/>

                            </form>
                        </td>
                    </tr>
                    <%}}%>
                </table>

            </div>
            <div class="users_info5">
                <h2>Заявки на денежные переводы</h2>
                <font color="#a52a2a">
                    <h1 class="acceptTransfer">${message}</h1>
                </font>
                <font color="#a52a2a">
                    <h1 class="cancelTrasfer">${message1}</h1>
                </font>

                <table class="tftable" border="1">
                    <tr>
                        <th>id_request </th>
                        <th>status</th>
                        <th>sum</th>
                        <th>target_id</th>
                        <th>sender_id</th>
                        <td>action</td>
                    </tr>
                    <% if(request.getAttribute("bankTransferList")!=null){%>
                    <% for (BankTransfer bankTransfer :
                            (ArrayList<BankTransfer>)request.getAttribute("bankTransferList")) {

                        String id_user_data = String.valueOf(bankTransfer.getUser_data().getId());
                        String sum = String.valueOf(bankTransfer.getSum());
                        String id_payeeBankAccount = String.valueOf(bankTransfer.getBank_account().getId());
                        String id_bankTransfer = String.valueOf(bankTransfer.getId());
                    %>
                    <tr>
                        <td><%=bankTransfer.getId()%></td>
                        <td><%=bankTransfer.isStatus()%></td>
                        <td><%=sum%></td>
                        <td><%=id_payeeBankAccount%></td>
                        <td><%=id_user_data%></td>

                        <td>
                            <form action="/acceptTransfer" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" value="<%=sum%>" name="sum"/>
                                <input type="hidden" value="<%=id_payeeBankAccount%>" name="id_payeeBankAccount"/>
                                <input type="hidden" value="<%=id_bankTransfer%>" name="id_bankTransfer"/>
                                <input type="submit" name="acceptTransfer" value="подтвердить перевод"/>


                            </form>
                            <form action="/cancelTrasfer" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" value="<%=id_payeeBankAccount%>" name="id_payeeBankAccount"/>
                                <input type="hidden" value="<%=sum%>" name="sum"/>
                                <input type="hidden" value="<%=id_bankTransfer%>" name="id_bankTransfer"/>
                                <input type="submit" name="cancelTrasfer" value="отклонить перевод"/>

                            </form>
                        </td>
                    </tr>
                    <%}}%>
                </table>

            </div>
            <div class="users_info5">
                <h2>Заявки на закрытие расчетного счета</h2>
                <font color="#a52a2a">
                    <h1 class="acceptClose">${acceptClose}</h1>
                </font>
                <table class="tftable" border="1">
                    <tr>
                        <th>id user</th>
                        <th>money</th>
                        <th>account status</th>
                        <th>action</th>
                    </tr>

                    <% if(request.getAttribute("bankTransferListFalse")!=null){%>
                    <% for (BankAccount bankAccount : (ArrayList<BankAccount>)request.getAttribute("bankTransferListFalse")) {
                        String id_bank_account = String.valueOf(bankAccount.getId());
                        String id_user_data = String.valueOf(bankAccount.getUser_data().getId());
                    %>

                    <tr>
                        <td><%=bankAccount.getUser_data().getId()%></td>
                        <td><%=bankAccount.getSum()%></td>
                        <td><%=bankAccount.isStatus()%></td>
                        <td>
                            <form action="/acceptCloseAccount" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" value="<%=id_bank_account%>" name="id_bank_account"/>
                                <input type="hidden" value="<%=id_user_data%>" name="id_user_data"/>
                                <input type="submit" name="acceptCloseAccount" value="подтвердить закрытие(все условия удовлетворяют)"/>
                            </form>
                        </td>
                    </tr>
                    <%}}%>
                </table>
            </div>
        </div>

    </body>
</html>
