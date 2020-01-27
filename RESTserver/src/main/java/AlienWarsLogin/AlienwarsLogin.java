package AlienWarsLogin;

import restShared.AccountDTO;
import Hashing.HashingClass;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlienwarsLogin {

    private DbClass dbClass = new DbClass();

    public boolean addAccount(String userName, String password, String email) {
        try {
            String hashedPassword = HashingClass.hashPassword(password);
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, userName);
            map.put(2, hashedPassword);
            map.put(3, email);
            String procedure = "INSERT INTO [dbo].[User] (userName, password, email) VALUES(?,?,?)";
            dbClass.executeNonQuery(procedure, map);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AccountDTO getUserByUserName(String userName) {
        String procedure = "SELECT uId, userName, password, email FROM [dbo].[User] WHERE userName = ?";
        if (userName.contains("@"))
            procedure = "SELECT email, password FROM [dbo].[User] WHERE email = ?";
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, userName);
        try {
            CachedRowSet cachedRowSet = dbClass.executeQuery(procedure, map);
            if (cachedRowSet.next()) {
                return new AccountDTO(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AccountDTO> getAll() {
        String procedure = "SELECT uId, userName, password, email FROM [dbo].[User]";
        List<AccountDTO> accounts = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try {
            CachedRowSet cachedRowSet = dbClass.executeQuery(procedure, map);
            while (cachedRowSet.next()) {
                accounts.add(new AccountDTO(
                        cachedRowSet.getInt("userId"),
                        cachedRowSet.getString("userName"),
                        cachedRowSet.getString("password"),
                        cachedRowSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
