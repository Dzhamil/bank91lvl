package bank.services.implementations;

import bank.controllers.AdminPanelController;
import bank.db.dao.BankAccountDAO;
import bank.db.dao.BankTransferDAO;
import bank.db.dao.RequestBankAccountDAO;
import bank.db.dao.UserDataDAO;
import bank.db.entity.BankAccount;
import bank.db.entity.RequestBankAccount;
import bank.db.entity.UserData;
import bank.services.interfaces.UserPanelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserPanelServiceImpl implements UserPanelService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private RequestBankAccountDAO requestBankAccountDAO;
    @Autowired
    private UserDataDAO userDataDAO;
    @Autowired
    private BankTransferDAO bankTransferDAO;
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public UserData getUserByLoginAndPass(String login, String pass) {
        logger.debug("method getUserByLoginAndPass is started...");
        logger.info("get User By Login="+login+" And Pass="+pass);
        return userDataDAO.getUserByLoginAndPass(login,pass);
    }

    @Override
    public RequestBankAccount getUserFromRequestBankAccountById(int id_user_data) {
        logger.debug("method getUserFromRequestBankAccountById is started...");
        logger.info("get get User From Request Bank Account By Id="+id_user_data);
       return requestBankAccountDAO.getUserFromRequestBankAccountById(id_user_data);
    }

    @Override
    public String newTransfer(int idSenderBankAccount, int sum, int id_payeeBankAccount,int id_user_data) {
        logger.debug("method newTransfer is started...");
        return bankTransferDAO.newTransfer(
               idSenderBankAccount,
                        sum,
                        id_payeeBankAccount,
                        id_user_data
               );
    }

    @Override
    public BankAccount getUserAccountById(int id_user_data) {
        logger.debug("method getUserAccountById is started...");
        logger.info("get user account where id ="+id_user_data);
       return bankAccountDAO.getUserAccountById(id_user_data);
    }

    @Override
    public void openAccount(int id_user_data) {
        logger.debug("method openAccount is started...");
        RequestBankAccount requestBankAccount = new RequestBankAccount();
        requestBankAccountDAO.openAccount(id_user_data, requestBankAccount);
        logger.info("open account user id="+id_user_data);
        logger.debug("method openAccount is successfully completed");
    }

    @Override
    public void closeAccount(int id_bank_account) {
        logger.debug("method closeAccount is started...");
        bankAccountDAO.closeAccount(id_bank_account);
        logger.info("close bank accounr where id_bank_account="+id_bank_account);
        logger.debug("method closeAccount is successfully completed");
    }

    @Override
    public String getMoney(int sum, int id_bank_account) {
        logger.debug("method getMoney is started...");
        logger.info("get money from id_bank_account="+id_bank_account);
       return bankAccountDAO.getMoney(sum,id_bank_account);
    }

    @Override
    public String putMoney(int sum, int id_bank_account) {
        logger.debug("method putMoney is started...");
        logger.info("put money from id_bank_account="+id_bank_account);
        return bankAccountDAO.putMoney(sum,id_bank_account);
    }
}
