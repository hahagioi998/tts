package org.tts.controller.admin;

import org.tts.entity.Admin;
import org.tts.entity.Role;
import org.tts.service.impl.AdminServiceImpl;
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
 * @package-name org.tts.controller.admin
 * @createtime 2019-08-26 17:48
 */
@WebServlet(name = "AdminServlet",urlPatterns = {"/adminServlet"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int currentPage=1;
        if(req.getParameter("page")!=null){
            currentPage=Integer.parseInt(req.getParameter("page"));
        }
        List<Admin> adminList=new AdminServiceImpl().getAdminByPage(currentPage);
        int pages=0;
        int count=new AdminServiceImpl().getAdminCount();
        if(count%Admin.PAGE_SIZE==0){
            pages=count/Admin.PAGE_SIZE;
        }else{
            pages=count/Admin.PAGE_SIZE+1;
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <=pages; i++) {
            if(currentPage==i){
                sb.append("["+i+"]");
            }else{
                sb.append("<a href='"+req.getContextPath()+"/adminServlet?page="+i+"'>"+i+"</a>");
            }
            sb.append(" ");
        }
        req.getSession().setAttribute("adminList",adminList);
        req.getSession().setAttribute("bar",sb.toString());
        resp.sendRedirect(req.getContextPath()+"/view/admin/admin_list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
