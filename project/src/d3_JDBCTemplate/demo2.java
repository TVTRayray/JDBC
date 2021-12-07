package d3_JDBCTemplate;

import JDBC_util.JDBC_utils_Druid;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 需求练习：
 *          1.修改一号数据的salary为10000
 *          2.添加一条记录
 *          3.删除刚才添加的记录
 *          4.查询id为100的记录，将其封装到Map集合
 *          5.查询所有的记录，将其封装到List
 *          6.查询所有的记录，将其封装为Emp对象的List集合
 *          7.查询总记录条数
 *
 */
public class demo2 {
   @Test
    public void test1(){
       JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
       String sql = "update emp set salary = 10000 where id =100";
       int update = temp.update(sql);
       System.out.println(update);
   }

    @Test
    public void test2(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "insert into user values(null,?,?)";
        int update = temp.update(sql,"cxk","11454");
        System.out.println(update);
    }
    @Test
    public void test3(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "delete from user where id=5";
        int update = temp.update(sql);
        System.out.println(update);
    }

    /**
     * 注意：该方法查询的结果集只能是1个，查询不到两条以上的结果
     */
    @Test
    public void test4(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "select * from emp where id=?";
        Map<String, Object> map = temp.queryForMap(sql, "1001");
        System.out.println(map);
    }

    /**
     * 该方法适合多条查询
     */
    @Test
    public void test5(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "select * from emp ";
        List<Map<String, Object>> list = temp.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
    //6.查询所有的记录，将其封装为Emp对象的List集合
    @Test
    public void test6(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "select * from emp ";
        List<EMP> list = temp.query(sql, new RowMapper<EMP>() {

            @Override
            public EMP mapRow(ResultSet rs, int i) throws SQLException {
                EMP emp = new EMP();
                emp.setId(rs.getInt("id"));
                emp.setEname(rs.getString("ename"));
                emp.setJob_id(rs.getInt("job_id"));
                emp.setMgr(rs.getInt("mgr"));
                emp.setDate(rs.getDate("joindate"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setBonus(rs.getDouble("bonus"));
                emp.setDept_id(rs.getInt("dept_id"));
                return emp;
            }
        });
        for (EMP emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void test6_plus(){
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "select * from emp ";
        List<EMP> list = temp.query(sql, new BeanPropertyRowMapper<EMP>(EMP.class));
        for (EMP emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 常用于执行一些聚合函数
     */
    @Test
    public void test7() {
        JdbcTemplate temp = new JdbcTemplate(JDBC_utils_Druid.getDataSource());
        String sql = "select count(id) from emp";
        Long total = temp.queryForObject(sql, Long.class);
        System.out.println(total);

    }

}
