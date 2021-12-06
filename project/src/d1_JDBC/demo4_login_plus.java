package d1_JDBC;

import JDBC_util.JDBC_utils;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用PreparedStatement来进行预处理，防止sql注入
 */
public class demo4_login_plus {
    public boolean login(String username,String password)  {
        if (username ==null && password == null){
            return false;
        }
        //1.获取数据库连接
        Connection connection = null;
        //        Statement statement= null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet= null;
        try {
            connection = JDBC_utils.getConnection();
            //2.定义sql

            //String sql = "select * from user where username='"+username+"' and password='"+password+"'";
            String sql = "select * from user where username= ? and password= ?";

            //3.获取执行对象
            preparedStatement = connection.prepareStatement(sql);

            //4.给？赋值
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //5.执行查询
            resultSet = preparedStatement.executeQuery(sql);

            //6.对结果进行判断
            return (resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBC_utils.close(resultSet,preparedStatement,connection);
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
        demo4_login_plus test = new demo4_login_plus();
        boolean login = test.login(name, passwd);
        System.out.println(login);
    }
}
