import java.sql.*;

public class Main {
    private static final String REQUEST = "select course_name, COUNT(subscription_date)/(MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date))) AS count FROM purchaselist group by course_name;";
    public static void main(String[] args) {

        try(var connection = ConnectionManager.open();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQUEST)) {
            while(resultSet.next()){
                String courseName = resultSet.getString("course_name");
                double count = resultSet.getDouble("count");
                System.out.println(courseName + '\n' + count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
