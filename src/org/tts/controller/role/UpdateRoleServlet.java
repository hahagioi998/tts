package org.tts.controller.role;

import org.tts.entity.Power;
import org.tts.entity.Role;
import org.tts.service.impl.PowerServiceImpl;
import org.tts.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.controller.role
 * @createtime 2019-08-22 19:10
 */
@WebServlet(name = "UpdateRoleServlet",urlPatterns = {"/updateRoleServlet"})
public class UpdateRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String rid=req.getParameter("rid");
        String roleName=req.getParameter("roleName");
        List<Integer> pidList=new RoleServiceImpl().getPidByRid(Integer.parseInt(rid));
        List<Power> powerList=new PowerServiceImpl().getAllPower();
        Role role=new Role(Integer.parseInt(rid),roleName,pidList);
        req.getSession().setAttribute("powerList",powerList);
        req.getSession().setAttribute("role",role);
        resp.sendRedirect(req.getContextPath()+"/view/role/role_modi.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
