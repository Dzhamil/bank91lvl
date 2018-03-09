package bank.db.dao;

import bank.controllers.AdminPanelController;
import bank.db.entity.RolesData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


@Repository
public class RoleDAOimpl implements RoleDao {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public RolesData getOne(int numberOfRole) {
        logger.debug("method getOne is started...");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from RolesData");
        List<RolesData> rolesData = query.getResultList();
        em.getTransaction().commit();
        em.close();
        logger.debug("getOne method is uccessfully completed");
        return CollectionUtils.isEmpty(rolesData) ? null : rolesData.get(numberOfRole);
    }


}
