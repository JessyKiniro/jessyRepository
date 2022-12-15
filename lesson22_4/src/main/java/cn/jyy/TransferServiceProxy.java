package cn.jyy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import utils.JDBCUtils;

import java.sql.*;

@Component
@Aspect
@Order(2)
public class TransferServiceProxy {

    @Around(value = "execution(* cn.jyy.TransferService.transfer(..))")
    public Integer around(ProceedingJoinPoint proceedingJoinPoint) {

        int i = -1;

        Object[] args = proceedingJoinPoint.getArgs();
        String userFrom = (String) args[0];
        String userTo = (String) args[1];
        Integer money = (Integer) args[2];

        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;


        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        String sql1 = "select * from account where name = ?";
        String sql2 = "select money from account where name = ?";


        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, userFrom);

            resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setString(1, userFrom);
                resultSet1 = preparedStatement2.executeQuery();
                if (resultSet1.next()) {
                    int account = resultSet1.getInt("money");
                    if (account > money) {
                        preparedStatement1.setString(1, userTo);
                        resultSet2 = preparedStatement1.executeQuery();
                        if (resultSet2.next()) {
                          i= (int) proceedingJoinPoint.proceed();

                        } else {
                            //转入账户不存在
                            i = 2;
                        }
                    } else {
                        //转出账户余额不足
                        i = 3;
                    }
                }

            } else {
                i = 1;
            }
            connection.commit();

        } catch (Throwable e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        finally {
            JDBCUtils.close(connection,preparedStatement1,preparedStatement2,null);
        }
        return i;
    }

}
