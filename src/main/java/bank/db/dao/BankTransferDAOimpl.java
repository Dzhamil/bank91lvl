package bank.db.dao;


import bank.controllers.AdminPanelController;
import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.db.entity.UserData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


@Repository
public class BankTransferDAOimpl implements BankTransferDAO {

    @Autowired
    private EntityManagerFactory emf;
    @Autowired
    private BankAccountDAO bankAccountDAO;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Override
    public String newTransfer(int idSenderBankAccount, int sum, int id_payeeBankAccount, int id_user_data) {

        logger.debug("method newTransfer is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccountUser = em.find(BankAccount.class, idSenderBankAccount);
        String message;
        if(bankAccountUser.getSum()>=sum&&bankAccountUser.isStatus()) {
            bankAccountUser.setSum(bankAccountUser.getSum()-sum);
            UserData userData = em.find(UserData.class, id_user_data);
            BankAccount bankAccount = em.find(BankAccount.class, id_payeeBankAccount);
            BankTransfer bankTransfer = new BankTransfer();
            bankTransfer.setBank_account(bankAccount);
            bankTransfer.setUser_data(userData);
            bankTransfer.setSum(sum);
            bankTransfer.setStatus(false);
            em.persist(bankTransfer);
            em.merge(bankAccountUser);
            em.getTransaction().commit();
            logger.info("new transfer was successful where id user data = "+id_user_data);
            em.close();
            logger.debug("newTransfer method is uccessfully completed");
            return message = "the operation was successful, please wait...";
        }else
            em.getTransaction().commit();
            em.close();
            logger.info("the operation was not performed, not enough money or maybe account is closed whwrw id user = "+id_user_data);
            logger.debug("newTransfer method is uccessfully completed");
            return message = "the operation was not performed, not enough money or your account is closed";
    }

    @Override
    public String acceptTransfer(int sum, int id_payeeBankAccount, int id_bankTransfer) {
        logger.debug("method acceptTransfer is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccount = em.find(BankAccount.class, id_payeeBankAccount);
        String message;
        if(bankAccount.isStatus()){
            BankTransfer bankTransfer = em.find(BankTransfer.class, id_bankTransfer);
            em.remove(bankTransfer);

            BankAccount bankAccount2 = em.find(BankAccount.class, id_payeeBankAccount);
            bankAccount2.setSum(bankAccount.getSum()+sum);
            em.merge(bankAccount2);
            em.getTransaction().commit();
            em.close();
            logger.debug("acceptTransfer method is uccessfully completed");
            logger.info("accept transfer where id banktransfer = "+id_bankTransfer);
            return message = "the operation was successful";
        }else {
            em.getTransaction().commit();
            em.close();
            logger.info("don't accept transfer id banktransfer = "+id_bankTransfer);
            logger.debug("acceptTransfer method where is uccessfully completed");
            return message = "the operation was not performed,that account is closed";
        }

    }

    @Override
    public String cancelTrasfer( int sum, int id_bankTransfer) {
        logger.debug("method cancelTrasfer is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BankTransfer bankTransfer = em.find(BankTransfer.class, id_bankTransfer);
        int id_user_data = bankTransfer.getUser_data().getId();
        em.remove(bankTransfer);

        BankAccount bankAccount = em.find(BankAccount.class, bankAccountDAO.getUserAccountById(id_user_data).getId());
        bankAccount.setSum(bankAccount.getSum()+sum);
        em.merge(bankAccount);

        em.getTransaction().commit();
        em.close();
        String message = "the operation was successful";
        logger.info("cancel transfer where id transfer = "+id_bankTransfer);
        logger.debug("cancelTrasfer method is uccessfully completed");
        return message;
    }

    @Override
    public List <BankTransfer> getBankTransferByIdUser(int id_user_data) {
        logger.debug("method getBankTransferByIdUser is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(
                "from BankTransfer " +
                        "where user_data_id = :id_user_data");

        query.setParameter("id_user_data",id_user_data);

        List<BankTransfer> elementList = query.getResultList();
        em.getTransaction().commit();
        logger.info("get bank transfer bu id user ="+id_user_data);
        em.close();
        logger.debug("getBankTransferByIdUser method is uccessfully completed");
        return CollectionUtils.isEmpty(elementList ) ? null : elementList;
    }

    @Override
    public List<BankTransfer> getBankTransferByIdBankAccount(int id_bank_account) {
        logger.debug("method getAllAccountsByStatus is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(
                "from BankTransfer " +
                        "where bank_account_id = :id_bank_account");

        query.setParameter("id_bank_account",id_bank_account);

        List<BankTransfer> elementList = query.getResultList();
        em.getTransaction().commit();
        logger.info("get Bank Transfer By Id Bank Account = "+id_bank_account);
        em.close();
        logger.debug("getBankTransferByIdBankAccount method is uccessfully completed");
        return CollectionUtils.isEmpty(elementList ) ? null : elementList;
    }

    @Override
    public List<BankTransfer> getAllBankTransfer() {
        logger.debug("method getAllBankTransfer is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from BankTransfer ");

        List<BankTransfer> elementList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.debug("getAllBankTransfer method is uccessfully completed");
        return CollectionUtils.isEmpty(elementList ) ? null : elementList;
    }

}
