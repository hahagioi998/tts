package org.tts.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author xujin
 * @package-name org.tts.util
 * @create 2019-08-18 15:32
 */
@WebFilter(filterName = "ErrorFilter",urlPatterns = {"/view/error.jsp"},dispatcherTypes = {DispatcherType.ERROR})
public class ErrorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
