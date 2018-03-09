package bank.services.implementations;

import bank.controllers.AdminPanelController;
import bank.db.dao.RoleDao;
import bank.db.dao.UserDataDAO;
import bank.db.dao.UserPersonalDAO;
import bank.db.entity.RolesData;
import bank.db.entity.UserData;
import bank.db.entity.UserPersonal;
import bank.services.interfaces.AuthAndRegisterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthAndRegisterServiceImpl implements AuthAndRegisterService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private UserDataDAO userDataDAO;
    @Autowired
    private UserPersonalDAO userPersonalDAO;
    @Autowired
    private RoleDao roleDao;



    @Override
    @Transactional
    public void saveNewUser(UserData userData, UserPersonal userPersonal, int numberRole) {
        logger.debug("method saveNewUser is started...");
        int id_user_personal = userPersonalDAO.reg(userPersonal).getId();
        userData.setPassword(userData.getPassword());
        Set<RolesData> rolesData = new HashSet<>();
        rolesData.add(roleDao.getOne(numberRole));
        userData.setRoles_data(rolesData);
        userDataDAO.reg(id_user_personal,userData);
        logger.debug("method saveNewUser is successfully completed");
        logger.info("new user is save id user personal = "+id_user_personal);

    }

}
