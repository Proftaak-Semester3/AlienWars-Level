package restShared;

public class AccountDTO {
    private int accountID;
    private String username;
    private String password;
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(int accountID, String username, String password, String email) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {return email; }

    public int getAccountID() {
        return accountID;
    }
}
