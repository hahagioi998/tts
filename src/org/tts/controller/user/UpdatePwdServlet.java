package org.tts.controller.user;

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
 * @createtime 2019-08-19 19:01
 */
@WebServlet(name = "UpdatePwdServlet",urlPatterns = {"/updatePwdServlet"})
public class UpdatePwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String first_newPwd=req.getParameter("first_newPwd");
        String md5=Hashing.md5().hashString(first_newPwd,Charsets.UTF_8).toString();
        Admin admin= (Admin) req.getSession().getAttribute("admin");
        boolean flag=new AdminServiceImpl().updatePassword(admin.getAid(),md5,admin.getAname());
        if(flag){
            admin.setApassword(first_newPwd);
            req.getSession().setAttribute("admin",admin);
            req.getSession().setAttribute("update_pwd_s_msg",ErrorMsg.UPDATE_PWD_S_MSG);
            resp.sendRedirect(req.getContextPath()+"/view/user/user_modi_pwd.jsp");
        }else{
            req.getSession().setAttribute("update_pwd_f_msg",ErrorMsg.UPDATE_PWD_F_MSG);
            resp.sendRedirect(req.getContextPath()+"/view/user/user_modi_pwd.jsp");
        }
    }
}
