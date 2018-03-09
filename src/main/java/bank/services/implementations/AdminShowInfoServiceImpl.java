package bank.services.implementations;


import bank.controllers.AdminPanelController;
import bank.db.dao.BankAccountDAO;
import bank.db.dao.BankTransferDAO;
import bank.db.dao.RequestBankAccountDAO;
import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.db.entity.RequestBankAccount;
import bank.services.interfaces.AdminShowInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminShowInfoServiceImpl implements AdminShowInfoService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);


    @Autowired
    private BankAccountDAO bankAccountDAO;
    @Autowired
    private RequestBankAccountDAO requestBankAccountDAO;
    @Autowired
    private BankTransferDAO bankTransferDAO;

    @Override
    public List<BankAccount> getAllAccount() {
        logger.debug("method getAllAccount getAll account is run");
       return bankAccountDAO.getAllAccount();
    }

    @Override
    public List<RequestBankAccount> getAllRequestToBankAccount() {
        logger.debug("method getAllRequestToBankAccount is started...");
       return requestBankAccountDAO.getAllRequestToBankAccount();
    }

    @Override
    public List<BankTransfer> getAllBankTransfer() {
        logger.debug("method getAllBankTransfer is started...");
        return bankTransferDAO.getAllBankTransfer();
    }

    @Override
    public List<BankAccount> getBankTransferWhereStatusIsFalse() {
        logger.debug("method getBankTransferWhereStatusIsFalse is started...");
        List<BankAccount> list =  bankAccountDAO.getAllAccountsByStatus(false);
        logger.debug("method getBankTransferWhereStatusIsFalse is successfully completed");
        return list;
    }

}
