package d1_JDBC;

import java.sql.*;

/**
 * JDBC快速入门
 *              DriverManager       :驱动管理对象
 *              Connection          :数据库连接对象
 *              Statement           :执行sql的对象
 *              ResultSet           :结果集对象
 *              PreparedStatement   :执行sql的对象
 */
public class demo1 {
    public static void main(String[] args) throws Exception {
        //1.导入jar包
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "root");
        //4.定义sql语句
//        String sql = "select * from student";
        String sql ="update emp set age = 25 where id = 1";
        //5.获取执行sql的对象 statement
        Statement statement = connection.createStatement();
        //6.执行sql
        int result = statement.executeUpdate(sql);
        //7.处理结果
        System.out.println(result);
        //8.释放资源

        statement.close();
        connection.close();
    }
}
