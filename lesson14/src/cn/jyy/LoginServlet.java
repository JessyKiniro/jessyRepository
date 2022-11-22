package cn.jyy;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection connection = null;
        String sql1 = "select * from user_info where username = ? and password = ?;";
        String sql2 = "insert into login_log values(?,?);";
        String sql3 = "select * from login_log where username = ?;";
        String sql4 = "update login_log where username= ? and (select timestampdiff(day,?,?)) >1;  ";
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement1 = connection.prepareStatement(sql1);


            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, password);

            resultSet1 = preparedStatement1.executeQuery();

            if (resultSet1.next()) {
                preparedStatement3 = connection.prepareStatement(sql3);
                preparedStatement3.setString(1, username);
                resultSet2 = preparedStatement3.executeQuery();

                if (resultSet2.next()) {
                    Timestamp timestamp = resultSet2.getTimestamp("time");

                    preparedStatement4 = connection.prepareStatement(sql4);
                    preparedStatement4.setString(1, username);
                    preparedStatement4.setTimestamp(2, timestamp);
                    preparedStatement4.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    preparedStatement4.executeUpdate();

                } else {
                    preparedStatement2=connection.prepareStatement(sql2);
                    preparedStatement2.set

                }


                //登录成功
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
