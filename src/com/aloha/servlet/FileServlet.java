package com.aloha.servlet;

import com.aloha.entity.Mapper;
import com.aloha.entity.Task;
import com.aloha.entity.User;
import com.aloha.service.MapperService;
import com.aloha.service.TaskService;
import com.aloha.service.impl.MapperServiceImpl;
import com.aloha.service.impl.TaskServiceImpl;
import com.aloha.utils.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aloha 2022-03-30 18:39
 */
@WebServlet("/fileServlet/*")
public class FileServlet extends BaseServlet {
    private final MapperService mapperService = new MapperServiceImpl();

    /**
     * 上传文件
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        //设置临时文件的保存区和所有资源的保存区
        ServletContext servletContext = getServletContext();
        String tmp = servletContext.getRealPath("/tmp");
        String resources = servletContext.getRealPath("/resources");
        FileUtils.ensureFileExist(tmp, resources);
        int flag = FileUtils.uploadFile(request, tmp, resources);
        //flag的取值可以为 -1 0 1 (-1标识文件太大， 0标识文件未上传， 1标识文件成功上传)
        String msg = "";
        switch (flag) {
            case -1:
                msg = "文件大于10MB";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/pages/dotask/error.jsp").forward(request, response);
                break;
            case 0:
                msg = "请选择文件";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/pages/dotask/error.jsp").forward(request, response);
                break;
            default:
                Integer userId = user.getUserId();
                if (mapperService.queryMapper(flag, userId) == null) {
                    mapperService.add(new Mapper(flag, userId, user.getName()));
                }
                response.sendRedirect(request.getContextPath() + "/pages/dotask/success.jsp");
        }
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得要下载的文件
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        //获取文件描述，这将是最后用户下载到的文件名
        String describe = request.getParameter("describe");
        //压缩文件夹的位置
        String zipPath = getServletContext().getRealPath("/zip/" + taskId);
        //源文件的位置
        String srcPath = getServletContext().getRealPath("/resources/" + taskId);
        //确保文件夹存在
        FileUtils.ensureFileExist(zipPath, srcPath);
        //调用下载的方法
        try {
            FileUtils.downloadZip(request, response,
                    zipPath, "zip/" + taskId,
                    srcPath,
                    describe + ".zip");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
