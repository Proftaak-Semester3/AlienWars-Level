package restShared;

public class AccountDTO {
    private int accountID;
    private String username;
    private String password;
    private String emailRegister;

    public AccountDTO() {
    }

    public AccountDTO(int accountID, String username, String password) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailRegister() {return emailRegister; }

    public int getAccountID() {
        return accountID;
    }
}
