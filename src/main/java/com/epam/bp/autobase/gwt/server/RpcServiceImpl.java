package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.gwt.client.rpc.RpcService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.ServiceException;
import com.epam.bp.autobase.service.UserService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.UUID;

public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {
    @Inject
    SessionState ss;
    @Inject
    UserService us;
    @Inject
    private Logger logger;

    @Override
    public String login(String username, String password) {
        String resultLog;
        User user = null;
        UserDto userDto = new UserDto()
                .setUsername(username)
                .setPassword(password);
        try {
            user = us.findByCredentials(userDto);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        if (user != null) {
            if (user.getUuid() == null) {
                user.setUuid(UUID.randomUUID());
                try {
                    us.update(user);
                } catch (ServiceException e) {
                    logger.error(e.getMessage(), e.getCause());
                }
            }
            resultLog = "User '" + user.getUsername() + "' has logged-in with role: " + user.getRole();
//            Cookies.setCookie(AutobaseCookies.NAME_UUID, String.valueOf(user.getUuid()));
        } else
            resultLog = "User '" + userDto.getUsername() + "' with password '" + userDto.getPassword() + "' wasn't found.";
        ss.setSessionUser(user);
        logger.info(resultLog);
        return user != null ? user.getUsername() : null;
    }

    @Override
    public String logout() {
        String resultLog;
        User user = ss.getSessionUser();
        if ((user != null) && (user.getUsername() != null))
            resultLog = "User '" + user.getUsername() + "' have logged-out";
        else resultLog = "No user was logged in.";
        this.getThreadLocalRequest().getSession().invalidate();
        ss.setSessionUser(null);
        //     Cookies.removeCookie(AutobaseCookies.NAME_UUID);
        logger.info(resultLog);
        return null;
    }

    @Override
    public String loginCheck() {
        String resultLog;
        User user = ss.getSessionUser();
        if (user != null) resultLog = "User '" + ss.getSessionUser().getUsername() + "' has already logged-in.";
        else resultLog = "No user was logged in.";
        logger.info(resultLog);
        return user != null ? user.getUsername() : null;
    }
}
