package restShared;

import java.util.List;

public class AccountResponse {
    private boolean success;

    private List<AccountDTO> accounts;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public List<AccountDTO> getAccounts(){
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts){
        this.accounts = accounts;
    }
}
