package d1_JDBC;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class demo2 {
    @Test
    public void test() throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接对象
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stu",
                "root",
                "root");

        //3.获取执行对象
        Statement statement =connection.createStatement();

        //4.sql
        String sql = "select * from emp";

        //5.执行sql
        ResultSet resultSet = statement.executeQuery(sql);

        //6.遍历结果

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            int dep_id = resultSet.getInt("dep_id");
            System.out.println("id="+id);
            System.out.println("name="+name);
            System.out.println("age="+age);
            System.out.println("dep_id="+dep_id);
            System.out.println("------------------");

        }

        //7.释放资源
        statement.close();
        connection.close();
    }
}
