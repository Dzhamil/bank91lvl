package bank.services.interfaces;

import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.db.entity.RequestBankAccount;

import java.util.List;


public interface AdminShowInfoService {
    List<BankAccount> getAllAccount();
    List<RequestBankAccount> getAllRequestToBankAccount();
    List <BankTransfer> getAllBankTransfer();
    List <BankAccount> getBankTransferWhereStatusIsFalse();

}
