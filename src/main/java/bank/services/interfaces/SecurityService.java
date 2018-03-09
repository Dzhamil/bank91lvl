package bank.services.interfaces;

public interface SecurityService {

    String findLoggedUsersInLogin();
    void autoLogin(String login, String password);

}
