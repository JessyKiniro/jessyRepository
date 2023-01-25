package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @ResponseBody
    @RequestMapping("/login")
    public  String loginCheck(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        if(null==user.getUserName()||null==user.getPassword()){
            return "login failure! 原因：参数缺失";
        }
        boolean correct = loginService.isCorrect(user);
        if (correct) {
            HttpSession session = request.getSession();
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath("/");
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("password", user.getPassword());
            response.addCookie(cookie);
            return "login success!";
        } else {
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
            return "login failure!";
        }

    }
}
