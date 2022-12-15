package cn.jyy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
@Aspect
@Order(1)
public class TransferServiceTimeProxy {

    @Around(value = "execution(* cn.jyy.TransferService.*(..))")
    public Integer around(ProceedingJoinPoint proceedingJoinPoint) throws SQLException {
        int i=-1;
        long startTime = System.currentTimeMillis();

        try {
           i= (int) proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        long interval = endTime - startTime;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into service_log values(?,?,?,?,?);";

        if (interval > 1000) {
            connection=JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String objName = proceedingJoinPoint.getTarget().getClass().getSimpleName();

            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

            String methodName = signature.getMethod().getName();

            Object[] args = proceedingJoinPoint.getArgs();

            String argsName = "";
            for(Object o:args) {
                argsName = argsName + " " + o.getClass().getName() + o.toString();
            }

            String consumeTime=interval+"ms";

            try {
                connection= JDBCUtils.getConnection();
                connection.setAutoCommit(false);
                preparedStatement=connection.prepareStatement(sql);

                preparedStatement.setString(1,objName);
                preparedStatement.setString(2,methodName);
                preparedStatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(4,argsName);
                preparedStatement.setString(5,consumeTime);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                connection.rollback();
            }finally {
                JDBCUtils.close(connection,preparedStatement,null,null);
            }


        }
        return i;

    }
}
