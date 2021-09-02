package com.tonpower.filter;

import com.tonpower.domain.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/6/2 17:12
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI(); // 【/网站名/资源文件名】
        if (uri.indexOf("login") != -1|| "/mimissm/".equals(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }


        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        // 判断如果本次请求的资源文件与登录有关则放行，或者是欢迎页面也放行

        if (admin != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
