package cn.yiliedu.web;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class TransferDao {

    public int updateAccountMoney(String userFrom, String userTo, int money) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        int i=0;
        //更新账户余额和记录账户余额变动的jdbc代码
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "update account set money=money - ? where name= ?;";
            String sql2 = "update account set money=money + ? where name= ?;";
            String sql3 = "insert into account_log values(?,?,?,?);";

            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, money);
            preparedStatement1.setString(2, userFrom);

            preparedStatement1.executeUpdate();

            preparedStatement2=connection.prepareStatement(sql2);
            preparedStatement2.setInt(1,money);
            preparedStatement2.setString(2,userTo);
            preparedStatement2.executeUpdate();

            preparedStatement3=connection.prepareStatement(sql3);
            preparedStatement3.setString(1,userFrom);
            preparedStatement3.setString(2,userTo);
            preparedStatement3.setInt(3,money);
            preparedStatement3.setTimestamp(4,new Timestamp(System.currentTimeMillis()));

            connection.commit();

        } catch (SQLException throwables) {
            i=4;
            throwables.printStackTrace();

        }
        finally {
            JDBCUtils.close(connection,preparedStatement1,preparedStatement2,preparedStatement3);
        }

        return i;
    }



}
