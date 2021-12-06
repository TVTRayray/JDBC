package d2_Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 德鲁伊 连接池
 */
public class demo1 {
    public static void main(String[] args) throws Exception {
        //1.jar包
        //2.定义配置文件
        //3.加载配置文件
        Properties properties = new Properties();
        InputStream inputStream = demo1.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(inputStream);

        //4.获取连接池对象---->工厂模式
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //5.获取连接
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }
}
