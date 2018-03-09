package bank.db.dao;


import bank.db.entity.RequestBankAccount;

import java.util.List;


public interface RequestBankAccountDAO {

    void openAccount(int id_user_data, RequestBankAccount requestBankAccount);
    List<RequestBankAccount> getAllRequestToBankAccount();
    void acceptOpenAccount(int id_user_data, int id_requestBankAccount);
    void cancelOpenAccount(int id_requestBankAccount);
    RequestBankAccount getUserFromRequestBankAccountById(int id_user_data);

}
