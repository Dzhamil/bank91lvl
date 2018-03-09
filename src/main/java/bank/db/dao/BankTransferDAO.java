package bank.db.dao;


import bank.db.entity.BankTransfer;

import java.util.List;

public interface BankTransferDAO {
    String newTransfer(int idSenderBankAccount, int sum, int id_payeeBankAccount, int id_user_data);
    String acceptTransfer(int sum, int id_payeeBankAccount, int id_bankTransfer);
    String cancelTrasfer(int sum, int id_bankTransfer);
    List <BankTransfer> getBankTransferByIdUser(int id_user_data);
    List <BankTransfer> getBankTransferByIdBankAccount(int id_bank_account);
    List <BankTransfer> getAllBankTransfer();
}
