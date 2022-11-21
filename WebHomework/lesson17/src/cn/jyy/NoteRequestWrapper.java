package cn.jyy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NoteRequestWrapper extends HttpServletRequestWrapper {

    public NoteRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String parameter=super.getParameter(name).replace("蠢货","**").replace("傻逼","**");

        return parameter;
    }
}
