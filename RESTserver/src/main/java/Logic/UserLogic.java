package Logic;

import Hashing.HashingClass;
import Repositories.UserRepo;
import Repositories.UserRepository;
import restShared.AccountDTO;

public class UserLogic {
    private UserRepository repo = new UserRepo();

    public boolean login(String username, String password){
        AccountDTO accountDTO = repo.getUserByUserName(username);
        return HashingClass.checkPassword(password, accountDTO.getPassword());
    }

    public boolean register(String userName, String email, String password, String passwordCheck){
        if(!password.equals(passwordCheck)){
            return false;
        }
        return repo.addAccount(userName, password, email);
    }
}
