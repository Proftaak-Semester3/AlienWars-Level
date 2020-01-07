package restShared;

public class AccountResponse {
    private boolean success;
    private LoginViewModel account;

    public AccountResponse(boolean success, LoginViewModel account) {
        this.success = success;
        this.account = account;
    }

    public AccountResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public LoginViewModel getAccount() {
        return account;
    }
}
