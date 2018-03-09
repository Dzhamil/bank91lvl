package bank.services.interfaces;

public interface AdminPanelService {
    String acceptOpenAccount(int id_user_data, int id_requestBankAccount);
    String cancelOpenAccount(int id_requestBankAccount);
    String acceptTransfer(int sum, int id_payeeBankAccount, int id_bankTransfer);
    String cancelTrasfer(int sum, int id_bankTransfer);
    String acceptCloseAccount(int id_bank_account, int id_user_data);
}
