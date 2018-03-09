package bank.db.dao;

import bank.db.entity.BankAccount;

import java.util.List;

public interface BankAccountDAO {
    List<BankAccount> getAllAccountsByStatus(boolean status);
    List<BankAccount> getAllAccount();
    void acceptCloseAccount(int id_bank_account);
    void closeAccount(int id_bank_account);
    String getMoney(int sum, int id_bank_account);
    String putMoney(int sum, int id_bank_account);
    BankAccount getUserAccountById(int id_user_data);


}
