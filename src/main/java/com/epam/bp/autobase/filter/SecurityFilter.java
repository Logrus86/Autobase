package com.epam.bp.autobase.filter;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.model.entity.User;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class SecurityFilter implements javax.servlet.Filter {
    private static final String USER = "user";
    private static Map<String, User.Role> roleMap = new HashMap<>();
    @Inject
    Logger logger;
    @Inject
    SessionState ss;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleMap.put("/admin", User.Role.ADMIN);
        roleMap.put("/mainADMIN", User.Role.ADMIN);
        roleMap.put("/mainDRIVER", User.Role.DRIVER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter0((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

        String pathInfo = req.getPathInfo();
        User user = ss.getSessionUser();
        User.Role currentRole = null;
        if (user != null) currentRole = user.getRole();
        if ((pathInfo != null) && (currentRole != User.Role.ADMIN)) {
            User.Role requiredRole = null;
            for (Map.Entry<String, User.Role> entry : roleMap.entrySet()) {
                if (pathInfo.startsWith(entry.getKey())) {
                    requiredRole = entry.getValue();
                    break;
                }
            }
            if ((requiredRole != null) && (!requiredRole.equals(currentRole))) {
                logger.error("Access forbidden. Page: " + pathInfo + ". Rights: " + currentRole + ", required rights: " + requiredRole);
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Have not required rights. Access forbidden.");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
