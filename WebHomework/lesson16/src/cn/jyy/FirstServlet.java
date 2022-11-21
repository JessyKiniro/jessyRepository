package cn.jyy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/firstServlet")

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String loginTime= (String) session.getAttribute("loginTime");
        response.setContentType("text/html;charset=utf-8");
        if(loginTime==null){
            Cookie cookie=new Cookie("JSESSIONID",session.getId());
            session.setAttribute("loginTime",getCurrentTime());
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            response.getWriter().write("您好，欢迎您首次访问");
        }else{
            String currentTime = getCurrentTime();
            session.setAttribute("loginTime",currentTime);
            response.getWriter().write("欢迎回来，您上次访问时间为: "+loginTime);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    private String  getCurrentTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(new Date());

    }
}
