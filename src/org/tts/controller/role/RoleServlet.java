package org.tts.controller.role;

import org.tts.entity.Role;
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
 * @createtime 2019-08-21 17:15
 */
@WebServlet(name = "RoleServlet",urlPatterns = {"/roleServlet"})
public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int currentPage=1;
        if(req.getParameter("page")!=null){
            currentPage=Integer.parseInt(req.getParameter("page"));
        }
        List<Role> roleList=new RoleServiceImpl().getRoleByPage(currentPage);
        int pages=0;
        int count=new RoleServiceImpl().getRoleCount();
        if(count%Role.PAGE_SIZE==0){
            pages=count/Role.PAGE_SIZE;
        }else{
            pages=count/Role.PAGE_SIZE+1;
        }
        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <=pages; i++) {
            if(currentPage==i){
                sb.append("["+i+"]");
            }else{
                sb.append("<a href='"+req.getContextPath()+"/roleServlet?page="+i+"'>"+i+"</a>");
            }
            sb.append(" ");
        }
        req.getSession().setAttribute("roleList",roleList);
        req.getSession().setAttribute("bar",sb.toString());
        resp.sendRedirect(req.getContextPath()+"/view/role/role_list.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
