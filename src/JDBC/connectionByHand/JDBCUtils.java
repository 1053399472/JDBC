package JDBC.connectionByHand;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//工具类，主要是将数据库连接过程一斤资源关闭抽出来
public class JDBCUtils {
    public static Connection getConnection() throws Exception {
//        载入配置文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);

        String user = props.getProperty("user");
        String driverCLass = props.getProperty("driverClass");
        String password = props.getProperty("password");
        String url = props.getProperty("url");

        Class.forName(driverCLass);
//      注册数据库驱动

        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);
        return connection;
    }

    public static void closeResource(Connection connection, Statement preparedStatement, ResultSet resultSet) {
//        对于查询来说,因为有结果集,所以需要多关闭一个resultSet资源
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (resultSet != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, Statement preparedStatement) {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
