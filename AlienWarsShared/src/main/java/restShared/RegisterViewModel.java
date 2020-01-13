package restShared;

public class RegisterViewModel implements AccountModel {

    private String username;
    private String password;
    private String passwordCheck;
    private String email;

    public RegisterViewModel() {
    }

    public RegisterViewModel(String email, String username, String password, String passwordCheck) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordCheck = passwordCheck;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }
}
