package Repositories;

import AlienWarsLogin.AlienwarsLogin;
import restShared.AccountDTO;

public class UserRepo implements UserRepository {

    AlienwarsLogin alienwarsLogin = new AlienwarsLogin();
    public AccountDTO getUserByUserName(String username) {
        return alienwarsLogin.getUserByUserName(username);
    }

    public boolean addAccount(String userName, String password, String email) {
        return alienwarsLogin.addAccount(userName, password, email);
    }
}
