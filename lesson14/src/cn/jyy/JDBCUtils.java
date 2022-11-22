package cn.jyy;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties properties=new Properties();
            InputStream inputStream=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource= DruidDataSourceFactory.createDataSource(properties);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
