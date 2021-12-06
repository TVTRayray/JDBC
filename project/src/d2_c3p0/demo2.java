package d2_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3po xml参数
 */
public class demo2 {
    public static void main(String[] args) throws SQLException {
        //1.不传入值，默认使用<default-config>
        DataSource dataSource= new ComboPooledDataSource();

        for (int i=1;i<=10;i++) {
            Connection connection = dataSource.getConnection();
            System.out.println(i+":"+connection);
        }
    }
}
