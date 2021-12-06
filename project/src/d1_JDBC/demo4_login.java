package d1_JDBC;

import JDBC_util.JDBC_utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class demo4_login {
    public boolean login(String username,String password)  {
        if (username ==null && password == null){
            return false;
        }
        //1.获取数据库连接
        Connection connection = null;
        Statement statement= null;
        ResultSet resultSet= null;
        try {
            connection = JDBC_utils.getConnection();

            //2.获取执行对象
            statement = connection.createStatement();

            //3.定义sql
            String sql = "select * from user where username='"+username+"' and password='"+password+"'";

            //4.执行查询
            resultSet = statement.executeQuery(sql);

            //5.对结果进行判断
            return (resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBC_utils.close(resultSet,statement,connection);
        }

       return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input your name and passwd:");
        System.out.println("input your name:");
        String name =scanner.next();
        System.out.println("input your passwd:");
        String passwd =scanner.next();
        demo4_login test = new demo4_login();
        boolean login = test.login(name, passwd);
        System.out.println(login);
    }
}
