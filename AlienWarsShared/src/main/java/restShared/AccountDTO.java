package restShared;

public class AccountDTO {
    private int accountID;
    private String username;
    private String password;

    public AccountDTO() {
    }

    public AccountDTO(int accountID, String username, String password) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
