package com.aloha.Filter;

import com.aloha.entity.Manager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Aloha 2022-03-30 14:58
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断管理员是否已经登录
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object manager =  request.getSession().getAttribute("manager");
        if (manager == null) {
            //重定向到管理员登录界面
            request.getRequestDispatcher("/pages/manager/login.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
