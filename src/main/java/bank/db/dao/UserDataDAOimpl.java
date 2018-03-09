package bank.db.dao;

import bank.controllers.AdminPanelController;
import bank.db.entity.UserData;
import bank.db.entity.UserPersonal;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDataDAOimpl implements UserDataDAO {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void reg(int id_user_personal,UserData userData) {
        logger.debug("method reg is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserPersonal userPersonal = em.find(UserPersonal.class, id_user_personal);
        userData.setUser_personal(userPersonal);
        em.persist(userData);
        em.getTransaction().commit();
        logger.info("reg new user where id user personal ="+id_user_personal);
        em.close();
        logger.debug("reg method is uccessfully completed");

    }

    @Override
    public UserData getUserByLoginAndPass(String login, String password) {
        logger.debug("method getUserByLoginAndPass is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(
         "from UserData " +
                "where login = :login " +
                "and password = :password");

        query.setParameter("login",login);
        query.setParameter("password",password);

        List<UserData> elementList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.info("get user by pass and login where login ="+login+" and pass="+password);
        logger.debug("getUserByLoginAndPass method is uccessfully completed");
        return CollectionUtils.isEmpty(elementList ) ? null : elementList.get(0);
    }

    @Override
    public UserData findUserByLogin(String login) {
        logger.debug("method findUserByLogin is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(
                "from UserData " +
                        "where login = :login");
        query.setParameter("login", login);
        List<UserData> elementList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.info("find user by login where login = "+login);
        logger.debug("findUserByLogin method is uccessfully completed");
        return CollectionUtils.isEmpty(elementList ) ? null : elementList.get(0);
    }
}
