package com.aloha.servlet;

import com.aloha.entity.Manager;
import com.aloha.entity.Mapper;
import com.aloha.service.MapperService;
import com.aloha.service.impl.MapperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Aloha 2022-03-30 18:30
 */
@WebServlet("/mapperServlet/*")
public class MapperServlet extends BaseServlet {
    private final MapperService mapperService = new MapperServiceImpl();

    /**
     * 查询为提交指定编号任务的人员名单
     * @param request request
     * @param response repsone
     * @throws ServletException e
     * @throws IOException e
     */
    public void notSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端获取task_id, describe
        String taskId = request.getParameter("taskId");
        String describe = request.getParameter("describe");

        //查询未提交编号为task_id的用户列表
        List<Mapper> list = mapperService.queryUserNotSubmitByTid(Integer.parseInt(taskId));
        //将数据保存到会话域中，然后转发到指定的页面
        request.setAttribute("msg", describe);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/pages/task/not_submit_list.jsp").forward(request, response);
    }
}
