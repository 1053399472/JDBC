package JDBC.connectionByHand;



import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class connection {
    @Test
    public void testJdbc() throws ParseException {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parse = simpleFormatter.parse("2010-03-07");
//        System.out.println(parse.getTime());

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        String sql1 = "select id,name,email,birth from customers where id<?";

//       java.sql.Date date= new java.sql.Date(parse.getTime());
        int xuan = update(sql, "xua", "nezha@gmail.com", new Date(parse.getTime()));
        if (xuan > 0) {
            System.out.println("更改成功");
        } else {
            System.out.println("更改失败");
        }
        List<Customer> query = Query(Customer.class, sql1, 30);
        query.forEach(System.out::println);
//        foreach依次输出

        //preparedStatement.setDate(3, new Date(parse.getTime()));
    }

    public int update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
        return 0;
    }

    public <T> List<T> Query(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = preparedStatement.getMetaData();
//            获取结果集的列数
            int columnCount = metaData.getColumnCount();
            ArrayList<T> ts = new ArrayList<>();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String columnName = metaData.getColumnLabel(i + 1);
                    Field declaredField = Customer.class.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(t, value);
                }
                ts.add(t);
//                查询出来有多条结果
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return null;
    }
}

