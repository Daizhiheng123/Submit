package com.aloha.servlet;

import com.aloha.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Aloha 2022-03-29 22:07
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取URI，解析后选择对应的方法调用
        String URI = request.getRequestURI();
        String action = WebUtils.parseURI(URI);
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 所有子类都可以使用的注销方法
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、销毁session
        request.getSession().invalidate();
        //2、重定向到首页
        response.sendRedirect(request.getContextPath());
    }
}
