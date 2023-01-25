package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class FileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        if(null!=userName&&!userName.equals("")){
            return true;
        }
        else{
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("无权限访问此接口");
            return false;

        }

    }
}
