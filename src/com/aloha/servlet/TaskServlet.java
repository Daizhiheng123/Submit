package com.aloha.servlet;

import com.aloha.entity.Manager;
import com.aloha.entity.Task;
import com.aloha.service.TaskService;
import com.aloha.service.impl.TaskServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @author Aloha 2022-03-30 18:21
 */
@WebServlet("/taskServlet/*")
public class TaskServlet extends BaseServlet {
    private static final TaskService taskService = new TaskServiceImpl();

    /**
     * 用户查看任务
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void viewTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用异步请求从数据库获取待提交的文件
        List<Task> tasks = taskService.queryExistTaskByState(1);
        String json = "{}";
        if (tasks.size() != 0) {
            Gson gson = new Gson();
            json = gson.toJson(tasks);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }

    /**
     * 管理员发布任务
     *
     * @param request  request
     * @param response response
     * @throws ServletException e
     * @throws IOException      e
     */
    public void releaseTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session域中获取manager的id
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        Integer id = manager.getId();

        //从前端获取任务描述
        String describe = request.getParameter("describe");

        //添加任务
        taskService.addTask(new Task(null, id, 1, describe));

        //重定向到完成插入的页面
        response.sendRedirect(getServletContext().getContextPath() + "/pages/task/add_success.jsp");
    }

    /**
     * 根据管理员id，查看所有待提交的任务列表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        Integer id = manager.getId();
        List<Task> tasks = taskService.queryExistTaskByIdAndState(id, 1);
        //将任务保存到请求域中，转发回去
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/pages/task/task_list.jsp").forward(request, response);
    }

    /**
     * 根据管理者的主键id查询其发布的所有任务
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void manageTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        Integer id = manager.getId();
        List<Task> list = taskService.queryTasksById(id);
        request.setAttribute("list", list);
        String action = request.getParameter("action");
        if ("manage".equals(action)) {
            request.getRequestDispatcher("/pages/task/manage_task.jsp").forward(request, response);
        } else if ("download".equals(action)) {
            request.getRequestDispatcher("/pages/task/download_list.jsp").forward(request, response);
        }
    }

    /**
     * 改变任务的状态
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端获取任务id
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        //获取当前状态
        int state = Integer.parseInt(request.getParameter("state"));
        switch (state) {
            case 1:
                taskService.changeTask(taskId, 0);
                break;
            case 0:
                taskService.changeTask(taskId, 1);
                break;
        }
        request.getRequestDispatcher("/taskServlet/manageTask?action=manage").forward(request, response);
    }
}
