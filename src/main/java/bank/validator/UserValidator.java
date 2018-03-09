package bank.validator;


import bank.controllers.AdminPanelController;
import bank.db.dao.UserDataDAO;
import bank.db.entity.UserData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private UserDataDAO userDataDAO;


    public String validateLogin(Object target) {
        UserData userData = (UserData) target;
        if(userDataDAO.findUserByLogin(userData.getLogin())!=null) {
            String errors = "Duplicate login!!! TRY AGAIN";
            return errors;
        }
        return null;

    }

    public String validatePassword(Object target) {
        UserData userData = (UserData) target;
        if (!userData.getConfirmPassword().equals(userData.getPassword())) {
          String errors = "Differennt passwords!!! TRY AGAIN";
          return errors;
        }
        return null;
    }
}
