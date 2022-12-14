import java.sql.*;
import java.text.SimpleDateFormat;

public class DBConnection
{
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "dGCvkabb97";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection()
    {
        if(connection == null)
        {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass + "&useSSL=false");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), KEY name_birthDate(name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name,birthDate,`count`) VALUES" + insertQuery;
        DBConnection.getConnection().createStatement().execute(sql);

//        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
//                "VALUES" + insertQuery.toString() +
//                "ON DUPLICATE KEY UPDATE `count` = `count` +  1";
//        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) throws SQLException
    {
        birthDay = birthDay.replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") +
                "('" + name + "','" + birthDay + " ', 1) ");

        if (insertQuery.length() > 200000) {
            executeMultiInsert();
            insertQuery = new StringBuilder();
        }
    }

    public static int customSelect(String name) throws  SQLException
    {
        String sql = "SELECT count(*) AS A FROM voter_count where name='" + name + "'";
        int count = 0;
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (!rs.next()){
            count = rs.getInt("A");
        }
        return count;
    }

    public static void printVoterCounts() throws SQLException
    {
        String sql = "SELECT name, birthDate, count(v.count) as a FROM voter_count as v group by name order by a desc";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while(rs.next()) {
            int count = rs.getInt("a");
            if (count > 1) {
                System.out.println("\t" + rs.getString("name") + " (" +
                        rs.getString("birthDate") + ") - " + count);
            } else break;
        }
    }
}
