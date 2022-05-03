package com.aloha.Filter;

import com.aloha.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Aloha 2022-03-30 11:16
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 对事务进行管理
     *
     * @param servletRequest  servletRequest
     * @param servletResponse servletResponse
     * @param chain           chain
     * @throws IOException      e
     * @throws ServletException e
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        try {
            chain.doFilter(servletRequest, servletResponse);
            JDBCUtils.submitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
