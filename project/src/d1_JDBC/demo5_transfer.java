package d1_JDBC;

import org.junit.Test;
import JDBC_util.JDBC_utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 转账
 *      开启事务
 */
public class demo5_transfer {
    @Test
    public void Test()   {
        //1
        Connection connection = null;
        PreparedStatement stmt1= null;
        PreparedStatement stmt2= null;
        try {
            connection = JDBC_utils.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            /**
             * 2.定义SQL zs--->ls (500)
             *          -500
             *          +500
             */
            String sql1= "update account set money=money- ? where id = ?";
            String sql2= "update account set money=money+ ? where id = ?";

            //3.

            stmt1 = connection.prepareStatement(sql1);

            stmt2 = connection.prepareStatement(sql2);

            //4.
            stmt1.setDouble(1,500);
            stmt1.setInt(2,1);
            stmt2.setDouble(1,500);
            stmt2.setInt(2,2);

            //5.
            stmt1.executeUpdate();
            stmt2.executeUpdate();

            //事务提交
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //细节，不为空时才可以回滚
                if (connection!=null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            //6.
            JDBC_utils.close(null,stmt1,connection);
            JDBC_utils.close(null,stmt2,null);
        }



    }
}
