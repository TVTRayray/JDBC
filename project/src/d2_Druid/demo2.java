package d2_Druid;

import JDBC_util.JDBC_utils_Druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Druid 工具类
 */
public class demo2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBC_utils_Druid.getConnection();
            String sql = "insert into user values(null,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Ray");
            preparedStatement.setString(2,"111");
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            JDBC_utils_Druid.close(preparedStatement,connection);
        }
    }
}
