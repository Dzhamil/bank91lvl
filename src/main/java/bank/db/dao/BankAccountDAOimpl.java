package bank.db.dao;

import bank.db.entity.BankAccount;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BankAccountDAOimpl implements BankAccountDAO {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BankAccountDAOimpl.class);


    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<BankAccount> getAllAccountsByStatus(boolean status) {
        logger.debug("method getAllAccountsByStatus is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from BankAccount where status = :status");
        query.setParameter("status",status);
        List<BankAccount> bankAccounts = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.debug("getAllAccountsByStatus method is uccessfully completed");
        return CollectionUtils.isEmpty(bankAccounts) ? null : bankAccounts;
    }

    @Override
    public List<BankAccount> getAllAccount() {
        logger.debug("method  getAllAccount is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from BankAccount");
        List<BankAccount> bankAccounts = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.debug("method getAllAccount is successfully completed");
        return bankAccounts;
    }

    @Override
    public void acceptCloseAccount(int id_bank_account) {
        logger.debug("method acceptCloseAccount is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccount =em.find(BankAccount.class, id_bank_account);
        if(bankAccount!=null) {
            em.remove(bankAccount);
            logger.info("remove banc account where id bank account = "+id_bank_account);
        }
        em.getTransaction().commit();
        em.close();
        logger.debug("acceptCloseAccount method is uccessfully completed");
    }

    @Override
    public void closeAccount( int id_bank_account) {
        logger.debug("method  closeAccount is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccount =em.find(BankAccount.class, id_bank_account);
        bankAccount.setStatus(false);
        em.merge(bankAccount);
        logger.info("account is closed where id_bank_account = "+id_bank_account);
        em.getTransaction().commit();
        em.close();

        logger.debug("closeAccount method is uccessfully completed");
    }

    @Override
    public String getMoney(int sum, int id_bank_account) {
        logger.debug("method  getMoney is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccount =em.find(BankAccount.class, id_bank_account);
        if(bankAccount.isStatus()) {
            if (bankAccount.getSum() >= sum) {
                int newSum = bankAccount.getSum() - sum;
                bankAccount.setSum(newSum);
                String message = "That ok";
                em.merge(bankAccount);
                em.getTransaction().commit();
                logger.info("get money from id_bank_account ="+id_bank_account+" sum ="+sum);
                em.close();
                return message;
            } else {
                String message = "you dont have a lot of money";
                em.merge(bankAccount);
                em.getTransaction().commit();
                logger.info("can't get money from id_bank_account ="+id_bank_account+" sum ="+sum);
                em.close();
                return message;
            }
        }else {
                String message = "Sorry! But account is clossed";
                return message;
        }

    }

    @Override
    public String putMoney(int sum, int id_bank_account) {
        logger.debug("method (BankAccountDAOimpl) getAllAccount is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BankAccount bankAccount = em.find(BankAccount.class, id_bank_account);
        if(bankAccount.isStatus()) {
            int newSum = bankAccount.getSum() + sum;
            bankAccount.setSum(newSum);
            String message = "That ok";
            em.merge(bankAccount);
            em.getTransaction().commit();
            logger.info("put money from id_bank_account ="+id_bank_account+" sum ="+sum);
            em.close();
            return message;
        }else {
            logger.info("can't put money from id_bank_account ="+id_bank_account+" sum ="+sum);
            String message= "Sorry! But account is clossed";
            return message;
        }
    }

    @Override
    public BankAccount getUserAccountById(int id_user_data) {
        logger.debug("method getUserAccountById is started");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(
                "from BankAccount " +
                        "where user_data_id = :id_user_data");

        query.setParameter("id_user_data",id_user_data);

        List<BankAccount> elementList = query.getResultList();
        em.getTransaction().commit();
        logger.info("get user bu id ="+id_user_data);
        em.close();
        return CollectionUtils.isEmpty(elementList ) ? null : elementList.get(0);
    }

}
