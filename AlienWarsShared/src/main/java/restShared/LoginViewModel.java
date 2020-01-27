package restShared;

public class LoginViewModel implements AccountModel {
    private String username;
    private String password;

    public LoginViewModel() {
    }

    public LoginViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
