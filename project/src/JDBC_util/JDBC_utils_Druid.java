package JDBC_util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC_utils_Druid {
    //1.定义一个成员变量
    private static DataSource ds;

    static {

        try {
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JDBC_utils_Druid.class.getClassLoader().getResourceAsStream("druid.properties"));

            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭连接
     */
    public static void close(ResultSet res,Statement stmt, Connection coon){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(coon!=null){
            try {
                coon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt, Connection coon){
        close(null,stmt,coon);
    }


    public  static DataSource getDataSource(){
        return ds;
    }
}
