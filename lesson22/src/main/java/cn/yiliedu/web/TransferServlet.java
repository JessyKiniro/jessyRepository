package cn.yiliedu.web;


import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //获取转出人
        String userFrom = request.getParameter("userFrom");
        //获取转入人
        String userTo = request.getParameter("userTo");
        //获取转账金额
        String money = request.getParameter("money");

        if (StringUtils.isEmpty(userFrom) || StringUtils.isEmpty(userTo) || StringUtils.isEmpty(money)) {
            response.getWriter().write("缺少必传参数");
            return;
        }
        int moneyTransfer = Integer.parseInt(money);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
