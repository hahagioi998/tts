package org.tts.controller.admin;

import org.tts.entity.Role;
import org.tts.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**提交github
 * @author xujin
 * @package-name org.tts.controller.admin
 * @createtime 2019-08-26 19:06
 */
@WebServlet(name = "addAdminServlet",urlPatterns = {"/addAdminServlet"})
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        List<Role> roleList=new RoleServiceImpl().getAllRole();
        req.getSession().setAttribute("roleList",roleList);
        resp.sendRedirect(req.getContextPath()+"/view/admin/admin_add.jsp");
    }
}
