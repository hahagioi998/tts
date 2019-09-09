package org.tts.controller.role;

import org.tts.entity.Power;
import org.tts.service.impl.PowerServiceImpl;

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
 * @createtime 2019-08-22 15:04
 */
@WebServlet(name = "AddRoleServlet",urlPatterns = {"/addRoleServlet"})
public class AddRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
        List<Power> powerList=new PowerServiceImpl().getAllPower();
        req.getSession().setAttribute("powerList",powerList);
        resp.sendRedirect(req.getContextPath()+"/view/role/role_add.jsp");
    }
}
