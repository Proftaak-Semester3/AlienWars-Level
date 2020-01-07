package Repositories;

import restShared.AccountDTO;

public interface UserRepository {
    public AccountDTO getUserByUserName(String username);
    public boolean addAccount(String userName, String password, String email);
}
