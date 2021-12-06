package d2_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0 连接池
 */
public class demo1 {
    public static void main(String[] args) throws SQLException {
        //1.创建对象
        DataSource dataSource = new ComboPooledDataSource();
        //2.获取连接对象
        Connection connection = dataSource.getConnection();
        //3.打印
        System.out.println(connection);

    }
}
