package bank.services.interfaces;


import bank.db.entity.BankAccount;
import bank.db.entity.RequestBankAccount;
import bank.db.entity.UserData;


public interface UserPanelService {

    UserData getUserByLoginAndPass(String login, String pass);
    RequestBankAccount getUserFromRequestBankAccountById(int id_user_data);
    String newTransfer(int idSenderBankAccount, int sum, int id_payeeBankAccount, int id_user_data);
    BankAccount getUserAccountById(int id_user_data);
    void openAccount(int id_user_data);
    void closeAccount(int id_bank_account);
    String getMoney(int sum, int id_bank_account);
    String putMoney(int sum, int id_bank_account);
}
