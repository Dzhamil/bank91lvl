package bank.services.implementations;

import bank.controllers.AdminPanelController;
import bank.db.dao.UserDataDAO;
import bank.db.entity.UserData;
import bank.db.entity.UserPersonal;
import bank.services.interfaces.AuthAndRegisterService;
import bank.services.interfaces.CreateAdminAndTestUserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateAdminAndTestUserServiceImpl implements CreateAdminAndTestUserService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    public UserDataDAO userDataDAO;
    @Autowired
    private AuthAndRegisterService authAndRegisterService;

    @Override
    public void createAdmin() {
        if(userDataDAO.getUserByLoginAndPass("admin","admin")==null){
            UserPersonal userPersonal = new UserPersonal();
            userPersonal.setFirst_name("firsNameAdmin");
            userPersonal.setSecond_name("secondNameAdmin");
            userPersonal.setLast_name("lastNameAdmin");

            UserData userData = new UserData();
            userData.setLogin("admin");
            userData.setPassword("admin");
            userData.setDate_reg("adminData");
            authAndRegisterService.saveNewUser(userData,userPersonal,1);
            logger.info("admin is created");
            logger.debug("method createAdmin is successfully completed");
        }
        logger.debug("method createAdmin is successfully completed");
    }

    @Override
    public void createTestUsers() {
        logger.debug("method createTestUsers is started...");
        for (int i = 1; i <= 2; i++) {
            if (userDataDAO.getUserByLoginAndPass("user"+i, "user"+i) == null) {
                UserPersonal userPersonal = new UserPersonal();
                userPersonal.setFirst_name("firsNameUser");
                userPersonal.setSecond_name("secondNameUser");
                userPersonal.setLast_name("lastNameUser");

                UserData userData = new UserData();
                userData.setLogin("user"+i);
                userData.setPassword("user"+i);
                userData.setDate_reg("userData");
                authAndRegisterService.saveNewUser(userData, userPersonal, 0);
                logger.info("new TSET user created");
            }

        }
        logger.debug("method createTestUsers is successfully completed");
    }
}
