package cn.jyy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=utf-8");
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginTime")) {
                    String loginTime = cookie.getValue();
                    Cookie cookie1 = new Cookie("loginTime", getCurrentTime());
                    cookie1.setMaxAge(60*60*24);
                    response.addCookie(cookie1);
                    response.getWriter().write("欢迎回来，您上次访问时间为: " + loginTime);
                    return;
                }

            }
        }
        Cookie cookie = new Cookie("loginTime", getCurrentTime());
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        response.getWriter().write("您好，欢迎您首次访问");


    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());

    }
}
