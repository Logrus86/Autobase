package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.gwt.client.GenericRpcService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.ServiceException;
import com.epam.bp.autobase.service.UserService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class AuthService extends RemoteServiceServlet implements GenericRpcService {
    @Inject
    SessionState ss;
    @Inject
    UserService us;
    @Inject
    private Logger logger;

    @Override
    public String getMessage(String msg) {
        return "Hello, " + msg + "!";
    }

    @Override
    public String login(String username, String password) {
        String result;
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
            if (user.equals(ss.getSessionUser()))
                result = "User '" + user.getUsername() + "' with role: " + user.getRole() + " has logged-in already.";
            else
                result = "User '" + user.getUsername() + "' with password '" + userDto.getPassword() + "' has logged-in with role: " + user.getRole();
        } else
            result = "User '" + userDto.getUsername() + "' with password '" + userDto.getPassword() + "' wasn't found.";
        ss.setSessionUser(user);
        logger.info(result);
        return result;
    }

    @Override
    public String logout() {
        String result;
        User user = ss.getSessionUser();
        this.getThreadLocalRequest().getSession().invalidate();
        if ((user != null) && (user.getUsername() != null))
            result = "User '" + user.getUsername() + "' have logged-out";
        else result = "No user was logged in.";
        logger.info(result);
        return result;
    }
}
