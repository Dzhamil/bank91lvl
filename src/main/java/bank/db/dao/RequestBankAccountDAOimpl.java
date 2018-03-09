package bank.db.dao;


import bank.controllers.AdminPanelController;
import bank.db.entity.BankAccount;
import bank.db.entity.RequestBankAccount;
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
public class RequestBankAccountDAOimpl implements RequestBankAccountDAO {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void openAccount(int id_user_data, RequestBankAccount requestBankAccount) {
        logger.debug("method openAccount is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserData userData = em.find(UserData.class, id_user_data);
        requestBankAccount.setUser_data(userData);
        em.persist(requestBankAccount);
        em.getTransaction().commit();
        logger.info("open account where id user = "+id_user_data);
        em.close();
        logger.debug("openAccount method is uccessfully completed");
    }

    @Override
    public List<RequestBankAccount> getAllRequestToBankAccount() {
        logger.debug("method getAllRequestToBankAccount is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from RequestBankAccount");
        List<RequestBankAccount> requestBankAccounts = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.debug("getAllRequestToBankAccount method is uccessfully completed");
        return requestBankAccounts;
    }

    @Override
    public void acceptOpenAccount(int id_user_data, int id_requestBankAccount) {
        logger.debug("method acceptOpenAccount is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        RequestBankAccount requestBankAccount = em.find(RequestBankAccount.class,id_requestBankAccount);
        UserData userData = em.find(UserData.class, id_user_data);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUser_data(userData);
        bankAccount.setStatus(true);
        em.persist(bankAccount);
        em.remove(requestBankAccount);
        em.getTransaction().commit();
        logger.info("accept open account where id user = "+id_user_data);
        em.close();
        logger.debug("acceptOpenAccount method is uccessfully completed");
    }

    @Override
    public void cancelOpenAccount(int id_requestBankAccount) {
        logger.debug("method cancelOpenAccount is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        RequestBankAccount requestBankAccount = em.find(RequestBankAccount.class,id_requestBankAccount);
        em.remove(requestBankAccount);
        em.getTransaction().commit();
        logger.info("cancel open account where id_requestBankAccount = "+id_requestBankAccount);
        em.close();
        logger.debug("cancelOpenAccount method is uccessfully completed");
    }

    @Override
    public RequestBankAccount getUserFromRequestBankAccountById(int id_user_data) {
        logger.debug("method getUserFromRequestBankAccountById is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from RequestBankAccount where user_data_id = :id_user_data");
        query.setParameter("id_user_data",id_user_data);
        List<RequestBankAccount> requestBankAccounts = query.getResultList();
        em.getTransaction().commit();
        logger.info(" get user from table request bancAccount where id  = " +id_user_data);
        em.close();
        logger.debug("getUserFromRequestBankAccountById method is uccessfully completed");
        return CollectionUtils.isEmpty(requestBankAccounts ) ? null : requestBankAccounts.get(0);
    }
}
