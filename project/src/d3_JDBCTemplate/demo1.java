package d3_JDBCTemplate;

import JDBC_util.JDBC_utils;
import JDBC_util.JDBC_utils_Druid;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate template = new JdbcTemplate(ds)
 * update
 * queryForMap      Map集合
 * queryForList     List集合
 * query            JavaBean对象
 * queryForObject   对象
 */
public class demo1 {


    public static void main(String[] args) {
        //1.创建对象
        JdbcTemplate template = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        //2.sql
        String sql = "update account set money = 1000 where id=?";
        //3.调用方法
        int count = template.update(sql, 1);

        System.out.println(count);

    }
}
