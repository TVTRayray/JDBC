package JDBC_util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class JDBC_utils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /**
     * 文件只需要读取一次，就可以拿到这些值.
     */
    static {
        //读取资源文件，获取值
        //1.创建Properties集合类
        Properties pro = new Properties();
        //2.加载文件
        try {
            //获取src路径下文件的方式---->ClassLoader
            ClassLoader classLoader = JDBC_utils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");
            String path = resource.getPath();
            pro.load(new FileReader(path));

            //3.获取数据、赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            //4.注册驱动
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取连接对象的方法
     * @return 连接的对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源，关闭连接的方法
     */
    public static void close(ResultSet resultSet, Statement stmt , Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
