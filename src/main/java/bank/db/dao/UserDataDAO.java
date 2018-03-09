package bank.db.dao;

import bank.db.entity.UserData;

public interface UserDataDAO {
    void reg(int id_user_personal, UserData userData);
    UserData findUserByLogin(String login);
    UserData getUserByLoginAndPass(String login, String password);

}
