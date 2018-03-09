package bank.services.interfaces;

import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;

import java.util.List;


public interface UserShowInfoService {
    List<BankAccount> getListOfAllBankClients();
    List <BankTransfer> getBankTransferByIdUser(int id_user_data);
    List <BankTransfer> getBankTransferByIdBankAccount(int id_bank_account);
}
