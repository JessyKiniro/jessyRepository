package cn.jyy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;
import utils.SpringConfig;

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

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);

        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=utf-8");
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
        //转账
        TransferService transferService = context.getBean("transferService", TransferService.class);
        int i = transferService.transfer(userFrom, userTo, moneyTransfer);
        //根据转账结果，响应浏览器不同内容
        if (i == 0) {
            response.getWriter().write("转账成功");
        } else if (i == 1) {
            response.getWriter().write("转账失败，转出账户不存在");
        } else if (i == 2) {
            response.getWriter().write("转账失败，转入账户不存在");
        } else if (i == 3) {
            response.getWriter().write("转账失败，转出账户余额不足");
        } else {
            response.getWriter().write("转账失败，原因未知,请查看日志");
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
