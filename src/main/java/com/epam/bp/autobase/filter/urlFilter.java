package com.epam.bp.autobase.filter;

import com.epam.bp.autobase.util.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class urlFilter extends Logger implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String pathInfo = req.getRequestURI().substring(req.getContextPath().length());

        if (pathInfo.startsWith("/static/")||pathInfo.startsWith("/webjars/")) {
    //        LOGGER.info("url starts with '/static/' or '/webjars/', filtered off.");
            chain.doFilter(req, resp);
            return;
        }
        req.getRequestDispatcher(pathInfo).forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
