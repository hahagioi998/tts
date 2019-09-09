package org.tts.controller.role;

import org.tts.service.impl.RoleServiceImpl;
import org.tts.util.ErrorMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xujin
 * @package-name org.tts.controller.role
 * @createtime 2019-08-22 18:25
 */
@WebServlet(name = "DeleteRoleServlet",urlPatterns = {"/deleteRoleServlet"})
public class DeleteRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String rid=req.getParameter("rid");
        boolean flag=new RoleServiceImpl().deleteRole(Integer.parseInt(rid));
        if(flag){
            req.getSession().setAttribute("delete_role_s_msg",ErrorMsg.DELETE_ROLE_S_MSG);
            resp.sendRedirect(req.getContextPath()+"/roleServlet");
        }else{
            req.getSession().setAttribute("delete_role_f_msg",ErrorMsg.DELETE_ROLE_F_MSG);
            resp.sendRedirect(req.getContextPath()+"/roleServlet");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
