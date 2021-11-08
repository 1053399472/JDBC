package JDBC.transaction;

import JDBC.connnectionPool.connection.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

//场景：AA给BB转账1000
//update user_table set balance=balance-100 where user='AA'
//update user_table set balance=balance+100 where user='BB'
public class TransactionTest {
    @Test
    public void testTransaction() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            QueryRunner queryRunner = new QueryRunner();

//            设置不要自动提交
            connection.setAutoCommit(false);

            String sql = "update user_table set balance=balance-100 where user=?";
            queryRunner.update(connection, sql, "AA");

            //            模拟异常中断事务,异常会使得整个流程结束
//            System.out.println(10/0);

            String sql1 = "update user_table set balance=balance+100 where user=?";
            queryRunner.update(connection, sql1, "BB");
//            提交数据
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
//            如果出现了异常就回滚
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            JDBCUtil.close(connection);
        }
    }
}
