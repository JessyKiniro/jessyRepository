package cn.jyy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Entrance {

    public static void main(String[] args) throws SQLException {

        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        String sql1 = "update account set money = money-500 where name='小明' and money >= 500;";
        String sql2 = "update account set money = money+500 where name='小芳';";
        String sql3 = "insert into account_log values(?,?,?,?);";

        try {
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement3 = connection.prepareStatement(sql3);
            int i = preparedStatement1.executeUpdate();
            if (i > 0) {
                preparedStatement2.executeUpdate();
                preparedStatement3.setString(1, "小明");
                preparedStatement3.setString(2, "小芳");
                preparedStatement3.setInt(3, 500);
                preparedStatement3.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                preparedStatement3.executeUpdate();

            }
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            System.out.println("发生异常，回滚");
        }
        finally {
            JDBCUtils.close(preparedStatement1,preparedStatement2,preparedStatement3,connection);
        }


    }
}
