package com.aloha.servlet;

import com.aloha.entity.User;
import com.aloha.service.UserService;
import com.aloha.service.impl.UserServiceImpl;
import com.aloha.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Aloha 2022-03-29 22:23
 */
@WebServlet("/userServlet/*")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * 处理登录的请求
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先判断用户是否存在
        //获取前端传过来的参数，并将其封装成一个对象
        User DIUser = WebUtils.DI(new User(), request.getParameterMap());
        //调用userService判断是否登录成功
        User user = userService.login(DIUser.getUserId(), DIUser.getPassword());
        if (user == null) { //用户不存在，登录失败，转发回去
            //将错误信息保存到请求域中，回显
            String msg = "密码错误";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        //登录成功
        //将用户保存到会话域中，保存其登录状态
        request.getSession().setAttribute("user", user);
        //重定向到登录成功页面
        response.sendRedirect(getServletContext().getContextPath() + "/pages/user/login_success.jsp");
    }

    /**
     * 用于用户注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //因为已经用异步请求判断过，用户是否存在所以不需要再次判断，只要验证验证码即可
        String code = request.getParameter("code").trim();
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //获取验证码后立刻删除
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //不满足条件，返回的错误信息
        String msg = "";


        if (token.equalsIgnoreCase(code) && token != null) { //验证码正确
            //满足条件，执行以下操作
            User user = WebUtils.DI(new User(), request.getParameterMap());
            //判断用户是否已存在
            if (userService.existUser(user.getUserId())) {
                msg = "用户已存在";
            } else {
                //将用户保存到数据库
                userService.register(user);
                getServletContext().log(user.toString()); //在日志中保存用户信息
                //跳转到注册成功页面
                //将用户保存到会话域中，保存其登录状态
                request.getSession().setAttribute("user", user);
                //重定向到登录页面
                response.sendRedirect(getServletContext().getContextPath() + "/pages/user/login_success.jsp");
                return;
            }
        } else {
            msg = "验证码错误";
        }

        //返回的错误信息
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
    }

    /**
     * 异步请求判断用户是否已经存在
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void ajaxExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端获取学号
        String userId = request.getParameter("userId");
        boolean flag = userService.existUser(WebUtils.parseInt(userId));

        Gson gson = new Gson();
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("exist", flag);
        String json = gson.toJson(map);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
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
