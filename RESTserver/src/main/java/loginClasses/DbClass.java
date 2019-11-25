package loginClasses;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Map;


public class DbClass {

    final private String hostName = "alien-wars.database.windows.net:1433"; // update me
    final private String dbName = "AlienWars"; // update me
    final private String user = "odin"; // update me
    final private String password = "Piks123="; // update me
    private String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
    private Connection connection = null;

    public DbClass(){
        url = "jdbc:sqlserver://alien-wars.database.windows.net:1433;database=AlienWars;user=odin@alien-wars;password=Piks123=;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }
    public void executeNonQuery(String procedure, Map map){
        try{
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i <= map.size(); i++) {
                statement.setObject( i, map.get(i));
            }

            statement.executeUpdate();
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public CachedRowSet executeQuery(String procedure, Map map) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i <= map.size(); i++) {
                statement.setObject( i, map.get(i));
            }

            resultSet = statement.executeQuery();
            crs.populate(resultSet);
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return crs;
    }
}
