package org.tts.controller.role;

import org.tts.entity.Admin;
import org.tts.entity.Role;
import org.tts.service.impl.RoleServiceImpl;
import org.tts.util.ErrorMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author xujin
 * @package-name org.tts.controller.role
 * @createtime 2019-08-22 16:49
 */
@WebServlet(name = "SaveRoleServlet",urlPatterns = {"/saveRoleServlet"})
public class SaveRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String[] powerList=req.getParameterValues("power");
        String roleName=req.getParameter("roleName");
        Admin admin= (Admin) req.getSession().getAttribute("admin");
        boolean flag=new RoleServiceImpl().insertRole(new Role(roleName,admin.getAname()),powerList);
        if(flag){
            req.getSession().setAttribute("insert_role_s_msg",ErrorMsg.INSERT_ROLE_S_MSG);
            resp.sendRedirect(req.getContextPath()+"/addRoleServlet");
        }else{
            req.getSession().setAttribute("insert_role_f_msg",ErrorMsg.INSERT_ROLE_F_MSG);
            resp.sendRedirect(req.getContextPath()+"/addRoleServlet");
        }
    }
}
