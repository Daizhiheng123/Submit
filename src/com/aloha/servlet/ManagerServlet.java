package com.aloha.servlet;

import com.aloha.entity.Manager;
import com.aloha.service.ManagerService;
import com.aloha.service.impl.ManagerServiceImpl;
import com.aloha.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aloha 2022-03-30 16:38
 */
@WebServlet("/managerServlet/*")
public class ManagerServlet extends BaseServlet {
    private static final ManagerService managerService = new ManagerServiceImpl();

    /**
     * 管理员登录
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Manager DIManager = WebUtils.DI(new Manager(), request.getParameterMap());
        Manager manager = managerService.login(DIManager.getManagerId(), DIManager.getPassword());
        if (manager != null) {
            //登录成功
            request.getSession().setAttribute("manager", manager);
            response.sendRedirect(getServletContext().getContextPath() + "/pages/manager/manager.jsp");
        } else {
            String msg = "密码错误";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/pages/manager/login.jsp").forward(request, response);
        }

    }

    /**
     * 注销
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.logout(request, response);
    }
}
