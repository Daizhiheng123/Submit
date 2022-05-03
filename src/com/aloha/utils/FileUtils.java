package com.aloha.utils;

import com.aloha.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Aloha 2022-03-26 0:00
 */
public class FileUtils {
    //文件的大小 1MB
    public static final int MB = 1024 * 1024;

    /**
     * 上传文件，成功返回 1 失败返回 0(没有文件) 返回 -1(文件内容太大)
     *
     * @param request  request
     * @param tempDict 临时文件的保存位置
     * @param srcDict  上传文件的保存位置
     * @return 0 -1 或者 1
     */
    public static int uploadFile(HttpServletRequest request, String tempDict, String srcDict) {

        //首先判断是否是多段的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            //不满足条件，返回 0
            return 0;
        }
        String src = "";
        String task_id = "";
        //创建FileItemFactory的实现类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置临时文件保存的位置
        factory.setRepository(new File(tempDict));
        //当文件大于2MB时，使用临时文件，否则，直接保存到内存中
        int threshold = 2 * MB;
        factory.setSizeThreshold(threshold);

        //创建文件上传的核心类ServletFileUpload
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置文件上传核心类的一些参数
        fileUpload.setFileSizeMax(10 * MB);
        fileUpload.setHeaderEncoding("UTF-8");
        //解析请求，获取每一个表单项
        try {
            List<FileItem> list = fileUpload.parseRequest(request);
            //遍历每一个表单项
            for (FileItem fileItem : list) {
                //如果表单项是普通表单，获取参数taskId将文件添加到
                if (fileItem.isFormField()) {
                    src = fileItem.getString("UTF-8");
                    task_id = src;
                }
            }
            for (FileItem item : list) {
                //如果表单项是普通表单，跳过
                if (item.isFormField()) {
                    continue;
                }
                //判断文件的大小
                long size = item.getSize();
                if (size > 10 * MB) { //文件内容太大，直接返回
                    return -1;
                } else if (size == 0) {
                    return 0;
                }
                //确保文件的保存路径存在，重命名文件名
                src = srcDict + "/" + src;
                ensureFileExist(tempDict, src);
                User user = (User) request.getSession().getAttribute("user");
                String fileName = user.getUserId() + user.getName() + getFileSuffix(item.getName());
                //上传文件
                item.write(new File(src, fileName));
                //清理临时文件
                item.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return Integer.parseInt(task_id);
    }

    private static void copy(ZipOutputStream zipOut, InputStream is) {
        try {
            int len = -1;
            byte[] buffer = new byte[1024 * 1024 * 2];
            while ((len = is.read(buffer)) != -1) {
                zipOut.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                zipOut.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadZip(HttpServletRequest request, HttpServletResponse response, String zipPath, String zipDir, String srcPath, String fileName) throws Exception {
        //首先对文件进行压缩
        //创建一个Zip流
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath + "/" + fileName));
        //遍历源文件，将每一个文件都加入到zip文件中
        File dir = new File(srcPath);
        File[] files = dir.listFiles();
        for (File file : files) {
            //获取每一个文件的名字，然后将这些名字放入一个ZipEntry中
            //然后将ZipEntry放入Zip流中，再将file的流向zip流中写入数据
            String name = file.getName();
            ZipEntry zipEntry = new ZipEntry(name);
            zipOut.putNextEntry(zipEntry);
            copy(zipOut, new FileInputStream(file));
        }
        //最后再关闭ZipOut流
        zipOut.close();
        //修改文件名的格式
        String encode = WebUtils.getURIEncode(fileName);
        //设置响应头，设置响应的类型为附件，filename为最后客户端得到的文件名
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        //获取输入流
        ServletContext context = request.getServletContext();
        InputStream is = context.getResourceAsStream("/" + zipDir + "/" + fileName);
        //获得响应流
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);

    }

    /**
     * 获取文件名的后缀
     *
     * @param fileName 文件名
     * @return 后缀
     */
    private static String getFileSuffix(String fileName) {
        String[] split = fileName.split("[.]");
        return "." + split[split.length - 1];
    }

    /**
     * 确保文件夹存在
     *
     * @param dict Directory
     */
    public static void ensureFileExist(String... dict) {
        for (String s : dict) {
            File file = new File(s);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
