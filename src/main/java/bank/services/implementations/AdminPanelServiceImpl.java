package bank.services.implementations;


import bank.controllers.AdminPanelController;
import bank.db.dao.BankAccountDAO;
import bank.db.dao.BankTransferDAO;
import bank.db.dao.RequestBankAccountDAO;
import bank.services.interfaces.AdminPanelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPanelServiceImpl implements AdminPanelService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private RequestBankAccountDAO requestBankAccountDAO;
    @Autowired
    private BankTransferDAO bankTransferDAO;
    @Autowired
    private BankAccountDAO bankAccountDAO;


    @Override
    public String acceptOpenAccount(int id_user_data, int id_requestBankAccount) {
        logger.debug("method acceptOpenAccount is started...");
       requestBankAccountDAO.acceptOpenAccount(id_user_data,id_requestBankAccount);
       String messge = "bank account is open";
        logger.debug("method acceptOpenAccount is successfully completed");
        logger.info("bank account is open by id user ="+ id_user_data);
       return messge;
    }

    @Override
    public String cancelOpenAccount(int id_requestBankAccount) {
        logger.debug("method cancelOpenAccount is started...");
        requestBankAccountDAO.cancelOpenAccount(id_requestBankAccount);
        String messageCancel = "bank account was not open";
        logger.info("bank account was not open id_requestBankAccount = "+ id_requestBankAccount);
        return messageCancel;
    }

    @Override
    public String acceptTransfer(int sum, int id_payeeBankAccount, int id_bankTransfer) {
        logger.debug("method acceptTransfer is started...");
        logger.info("acceptTransfer sum ="+sum+" id_bankTransfer = "+ id_bankTransfer);
        return bankTransferDAO.acceptTransfer(
                                            sum,
                                            id_payeeBankAccount,
                                            id_bankTransfer);
    }

    @Override
    public String cancelTrasfer( int sum, int id_bankTransfer) {
        logger.debug("method cancelTrasfer is started...");
        logger.info("cancel transfer id_bankTransfer = " +id_bankTransfer);
        return bankTransferDAO.cancelTrasfer(
                                            sum,
                                            id_bankTransfer);
    }

    @Override
    public String acceptCloseAccount(int id_bank_account, int id_user_data) {
        logger.debug("method acceptCloseAccount is started...");
        bankAccountDAO.acceptCloseAccount(id_bank_account);
        if(bankTransferDAO.getBankTransferByIdUser(id_user_data)!=null){
            String acceptClose = "Account not closed!!! cancel the all transfers";
            logger.debug("acceptCloseAccount method is uccessfully completed");
            logger.info("Account not closed!!! cancel the all transfers user data id="+id_user_data);
            return acceptClose;
        }else if(bankTransferDAO.getBankTransferByIdBankAccount(id_bank_account)!=null){
            String acceptClose = "Account not closed!!! cancel the all transfers";
            logger.debug("acceptCloseAccount method is uccessfully completed");
            logger.info("Account not closed!!! cancel the all transfers user data id="+id_user_data);
            return acceptClose;
        }else {
            String message = "Account is Closed";
            logger.debug("acceptCloseAccount method is uccessfully completed");
            logger.info("Account is Closed user data id="+id_user_data);
            return message;
        }
    }
}
