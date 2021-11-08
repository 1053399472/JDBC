package JDBC.connnectionPool.curd;

import JDBC.connnectionPool.connection.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateTest {
    @Test
    public void testUpdate() {
        Connection connection = null;
        try {
//        获取数据库连接
            connection = JDBCUtil.getConnection();
//        提供一个sql
            String sql = "insert into customers(name,email,birth)value('Tom','tom@126.com','2020-09-08')";
            QueryRunner queryRunner = new QueryRunner();
            int update = queryRunner.update(sql);
            System.out.println("添加了" + update + "条方法");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }
    }

    @Test
//    使用占位符
    public void testUpdate1() {
        Connection connection = null;
        try {
//        获取数据库连接
            connection = JDBCUtil.getConnection();
//        提供一个sql
            String sql = "insert into customers(name,email,birth)value(?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
//增删改都使用这一个方法即可
            int update = queryRunner.update(connection, sql, "Tom", "tom@126.com", "2020-09-08");
            System.out.println("添加了" + update + "条记录");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }

    }

    @Test
//    测试查询操作
//    如果换成BeanHandleList则是多个查询结果
    public void testUpdate2() throws SQLException {
        Connection connection = null;

        try {
//        获取数据库连接
            connection = JDBCUtil.getConnection();
//        提供一个sql
            String sql = "select name,email,birth from customers where id=?";
            QueryRunner queryRunner = new QueryRunner();
            BeanHandler<Customer> customerBeanHandler = new BeanHandler<Customer>(Customer.class);
            Customer query = queryRunner.query(connection, sql, customerBeanHandler, 1);
            System.out.println(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }

    }

    @Test
//    测试查询操作,查询特殊值，比如count
//    ScalarHandler方法
//    如果换成BeanHandleList则是多个查询结果
    public void testUpdate3() throws SQLException {
        Connection connection = null;

        try {
//        获取数据库连接
            connection = JDBCUtil.getConnection();
//        提供一个sql
            String sql = "select count(*) from customers";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(connection, sql, scalarHandler);
            System.out.println(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }

    }
}
