package bank.services.implementations;


import bank.controllers.AdminPanelController;
import bank.db.dao.BankAccountDAO;
import bank.db.dao.BankTransferDAO;
import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.services.interfaces.UserShowInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserShowInfoServiceImpl implements UserShowInfoService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private BankAccountDAO bankAccountDAO;
    @Autowired
    private BankTransferDAO bankTransferDAO;

    @Override
    public List<BankAccount> getListOfAllBankClients(){
        logger.debug("method getListOfAllBankClients is started...");
       List<BankAccount> list =  bankAccountDAO.getAllAccountsByStatus(true);
        logger.debug("method getListOfAllBankClients is successfully completed");
       return list;
    }

    @Override
    public List <BankTransfer> getBankTransferByIdUser(int id_user_data) {
        logger.debug("method getBankTransferByIdUser is started...");
        List <BankTransfer> list =  bankTransferDAO.getBankTransferByIdUser(id_user_data);
        logger.debug("method getBankTransferByIdUser is successfully completed");
        return list;
    }

    @Override
    public List<BankTransfer> getBankTransferByIdBankAccount(int id_bank_account) {
        logger.debug("method getBankTransferByIdBankAccount is started...");
        List<BankTransfer> list = bankTransferDAO.getBankTransferByIdBankAccount(id_bank_account);
        logger.debug("method getBankTransferByIdBankAccount is successfully completed");
        return list;
    }
}
