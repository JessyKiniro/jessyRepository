package cn.jyy;

import javax.servlet.*;
import java.io.IOException;

public class Servlet2 implements Servlet {

    int result=0;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        for(int i=1;i<=100;i++){
            result+=i;
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println(result);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
