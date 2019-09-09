package org.tts.filter;

import org.tts.entity.Admin;
import org.tts.entity.Power;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.util
 * @create 2019-08-18 16:03
 */
@WebFilter(filterName = "RequestFilter",urlPatterns = {"/view/*"},
           initParams = {@WebInitParam(name = "url",value = "login.jsp;error.jsp;nopower.jsp"),
                         @WebInitParam(name = "encoding",value = "utf-8"),
                         @WebInitParam(name = "path",value = "index.jsp;user_info.jsp;user_modi_pwd.jsp")})
public class RequestFilter implements Filter {
    private String url;
    private String encoding;
    private String path;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.url=filterConfig.getInitParameter("url");
        this.encoding=filterConfig.getInitParameter("encoding");
        this.path=filterConfig.getInitParameter("path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(encoding==null){
            encoding="utf-8";
        }
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        String[] urlArray=url.split(";");
        System.out.println(request.getRequestURI());
        if(urlArray!=null&&urlArray.length>0){
            for (String s : urlArray) {
                if(s==null||"".equals(s)) continue;;
                if(request.getRequestURI().indexOf(s)!=-1){
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }
        if(request.getSession().getAttribute("admin")!=null){
            String[] pathArray=path.split(";");
            if(pathArray!=null&&pathArray.length>0){
                for (String p : pathArray) {
                    if(p==null||"".equals(p)) continue;;
                    if(request.getRequestURI().indexOf(p)!=-1){
                        filterChain.doFilter(request,response);
                        return;
                    }
                }
            }
            List<Power> powerList=((Admin)request.getSession().getAttribute("admin")).getPowerList();
            for (Power power : powerList) {
                if(request.getRequestURI().indexOf(power.getBy2())!=-1){
                    filterChain.doFilter(request,response);
                    return;
                }
            }
            response.sendRedirect(request.getContextPath()+"/view/nopower.jsp");
        }else{
            response.sendRedirect(request.getContextPath()+"/view/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
