package com.epam.bp.autobase.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlFilter implements Filter {
    public final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(super.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String pathInfo = req.getRequestURI().substring(req.getContextPath().length());

        if (pathInfo.startsWith("/static/") || pathInfo.startsWith("/webjars/")) {
            chain.doFilter(req, resp);
            return;
        }
        req.getRequestDispatcher(pathInfo).forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
