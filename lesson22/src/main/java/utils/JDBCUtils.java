package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection connection, PreparedStatement preparedStatement1,PreparedStatement preparedStatement2,PreparedStatement preparedStatement3){

        if(preparedStatement1!=null){
            try {
                preparedStatement1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(preparedStatement2!=null){
            try {
                preparedStatement2.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(preparedStatement3!=null){
            try {
                preparedStatement3.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
