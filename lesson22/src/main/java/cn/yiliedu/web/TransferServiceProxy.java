package cn.yiliedu.web;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Aspect
public class TransferServiceProxy {

    long startTime;
    long endTime;


    @Before(value = "execution(* cn.yiliedu.web.TransferService.*(..))")
    public void before(){
        startTime=System.currentTimeMillis();
    }
    @After(value = "execution(* cn.yiliedu.web.TransferService.*(..))")
    public void after(){

    }

    @Around(value = "execution(* cn.yiliedu.web.TransferService.transfer(..))")
    public Integer around(ProceedingJoinPoint proceedingJoinPoint) {

        int i = -1;

        MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();

        String methodName = ms.getMethod().getName();

        if (methodName.equals("transfer")) {
            Object[] args = proceedingJoinPoint.getArgs();
            String userFrom = (String) args[0];
            String userTo = (String) args[1];
            Integer money = (Integer) args[2];

            Connection connection = null;
            PreparedStatement preparedStatement1 = null;
            PreparedStatement preparedStatement2 = null;
            try {
                connection = JDBCUtils.getConnection();
                String sql1 = "select * from  account where name = ? and money > ?;";
                String sql2 = "select * from  account where name = ?;";

                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, userFrom);
                preparedStatement1.setInt(2, money);

                ResultSet resultSet = preparedStatement1.executeQuery();
                if (resultSet.next()) {
                    ResultSet resultSet1 = preparedStatement2.executeQuery();
                    if (resultSet1.next()) {
                        i = (int) proceedingJoinPoint.proceed();
                    } else {
                        i = 2;
                    }
                } else {
                    i = 1;
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {

            }
        }

        return i;

    }

}
