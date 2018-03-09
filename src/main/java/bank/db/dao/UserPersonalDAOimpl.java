package bank.db.dao;

import bank.controllers.AdminPanelController;
import bank.db.entity.UserPersonal;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class UserPersonalDAOimpl implements UserPersonalDAO {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public UserPersonal reg(UserPersonal userPersonal) {

        logger.debug("register new user is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userPersonal);
        em.getTransaction().commit();
        logger.info("reg new user where user personal id="+userPersonal.getId());
        em.close();
        return userPersonal;
    }
}
