package org.tts.controller.user;

import org.tts.entity.Admin;
import org.tts.service.AdminService;
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
 * @package-name org.tts.controller.user
 * @createtime 2019-08-21 15:55
 */
@WebServlet(name = "UpdateAdminServlet",urlPatterns = {"/updateAdminServlet"})
public class UpdateAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String aname=req.getParameter("aname");
        String atel=req.getParameter("atel");
        String aemail=req.getParameter("aemail");
        String auname=req.getParameter("auname");
        Admin admin= (Admin) req.getSession().getAttribute("admin");
        Admin admin1=new Admin(auname,aname,atel,aemail);
        boolean flag=new AdminServiceImpl().updateAdmin(admin1,admin.getAname());
        if(flag){
            admin.setAname(aname);
            admin.setAtel(atel);
            admin.setAemail(aemail);
            req.getSession().setAttribute("admin",admin);
            req.getSession().setAttribute("update_admin_s_msg",ErrorMsg.UPDATE_ADMIN_S_MSG);
            resp.sendRedirect(req.getContextPath()+"//view/user/user_info.jsp");
        }else{
            req.getSession().setAttribute("update_admin_f_msg",ErrorMsg.UPDATE_ADMIN_F_MSG);
            resp.sendRedirect(req.getContextPath()+"//view/user/user_info.jsp");
        }

    }
}
