package JDBC.connnectionPool.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DruidTest {
    @Test
    public void testConnection() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("用户名");
        druidDataSource.setPassword("密码");
        druidDataSource.setUrl("连接地址");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testConnection2() throws Exception {
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testJDBCUtil() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
    }

}
