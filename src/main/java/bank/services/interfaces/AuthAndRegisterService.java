package bank.services.interfaces;

import bank.db.entity.UserData;
import bank.db.entity.UserPersonal;

public interface AuthAndRegisterService {
    void saveNewUser(UserData userData, UserPersonal userPersonal, int numberRole);
}
