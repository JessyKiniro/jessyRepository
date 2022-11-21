package cn.jyy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet1")

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("访问到Servlet1");
        String money = request.getParameter("money");

        if(money!=null&&Integer.parseInt(money)==100){
            request.getRequestDispatcher("/servlet2").forward(request,response);

        }


    }
}
