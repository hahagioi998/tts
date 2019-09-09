package org.tts.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.tts.entity.Admin;
import org.tts.service.impl.AdminServiceImpl;
import org.tts.util.ErrorMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujin
 * @package-name org.tts.controller
 * @create 2019-08-18 14:21
 */
@WebServlet(name = "LoginServlet",urlPatterns ={"/loginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String md5=Hashing.md5().hashString(password,Charsets.UTF_8).toString();
        Admin admin=new AdminServiceImpl().checkLogin(new Admin(username,md5));
        String codeBySession= (String) req.getSession().getAttribute("checkcode");
        String codeByLogin=req.getParameter("checkcode");
        if(admin!=null){
            if(codeBySession.equalsIgnoreCase(codeByLogin)) {
                if (admin.getPowerList() != null && admin.getPowerList().size() > 0) {
                    admin.setApassword(password);
                    req.getSession().setAttribute("admin", admin);
                    resp.sendRedirect(req.getContextPath() + "/view/index.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/view/nopower.jsp");
                }
            }else{
                req.getSession().setAttribute("checkcode_f_msg",ErrorMsg.CHECKCODE_F_MSG);
                resp.sendRedirect(req.getContextPath() + "/view/index.jsp");
            }
        }else{
            req.getSession().setAttribute("login_f_msg",ErrorMsg.LOGIN_F_MSG);
            resp.sendRedirect(req.getContextPath()+"/view/login.jsp");
        }
    }
}
