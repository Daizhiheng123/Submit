package com.aloha.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Aloha 2022-03-25 23:59
 */
public class WebUtils {

    /**
     * 解析URI，方便利用反射调用对应的函数
     *
     * @param URI URI
     * @return 方法名
     */
    public static String parseURI(String URI) {
        String[] split = URI.split("/");
        return split[split.length - 1];
    }

    /**
     * 解决下载时，文件名乱码问题
     *
     * @param name 文件名
     * @return 重新编码后的文件名
     */
    public static String getURIEncode(String name) {
        //按照UTF-8解码
        byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
        //重新编码
        return new String(bytes, StandardCharsets.ISO_8859_1);
    }

    /**
     * 将前端传输过来的参数注入指定对象中
     *
     * @param obj    targetObject
     * @param values map
     * @param <T>    obj
     * @return DIObj
     */
    public static <T> T DI(T obj, Map values) {
        //调用BeanUtils中的populate注入参数
        try {
            BeanUtils.populate(obj, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 解析从前端传输过来的字符串，将其转换成数字，如果出错返回默认值 -1
     *
     * @param i 数字字符串
     * @return 解析后的数字
     */
    public static int parseInt(String i) {
        int parseInt = -1;
        try {
            parseInt = Integer.parseInt(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return parseInt;
    }
}
