package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.ejb.SessionState;
import com.epam.bp.autobase.gwt.client.rpc.AuthService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.ServiceException;
import com.epam.bp.autobase.service.UserService;
import com.epam.bp.autobase.util.AutobaseCookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.util.UUID;

public class AuthServiceImpl extends RemoteServiceServlet implements AuthService {
    @Inject
    SessionState ss;
    @Inject
    UserService us;
    @Inject
    private Logger logger;

    @Override
    public UserDto login(String username, String password) {
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
            user.setUuid(String.valueOf(UUID.randomUUID()));
            try {
                us.update(user);
            } catch (ServiceException e) {
                logger.error(e.getMessage(), e.getCause());
            }
            Cookie uuidCookie = new Cookie(AutobaseCookies.NAME_UUID, String.valueOf(user.getUuid()));
            uuidCookie.setMaxAge(AutobaseCookies.MAX_AGE_UUID);
            this.getThreadLocalResponse().addCookie(uuidCookie);
            resultLog = "User '" + user.getUsername() + "' has logged-in with role: " + user.getRole();
        } else
            resultLog = "User '" + userDto.getUsername() + "' with password '" + userDto.getPassword() + "' wasn't found.";
        ss.setSessionUser(user);
        logger.info(resultLog);
        return ServerUtils.buildUserDto(user);
    }

    @Override
    public void logout() {
        String resultLog;
        User user = ss.getSessionUser();
        if (user != null) {
            user.setUuid(null);
            try {
                us.update(user);
            } catch (ServiceException e) {
                logger.error("Cannot update user to remove UUID");
            }
            resultLog = "User '" + user.getUsername() + "' have logged-out";
        } else resultLog = "No user was logged in.";
        this.getThreadLocalRequest().getSession().invalidate();
        ss.setSessionUser(null);
        Cookie uuidCookie = new Cookie(AutobaseCookies.NAME_UUID, "");
        uuidCookie.setMaxAge(0);
        this.getThreadLocalResponse().addCookie(uuidCookie);
        logger.info(resultLog);
    }

    @Override
    public UserDto loginCheck() {
        String resultLog = null;
        User user = ss.getSessionUser();
        if (user != null) {
            resultLog = "User '" + ss.getSessionUser().getUsername() + "' has already logged-in.";
        } else {
            for (Cookie cookie : this.getThreadLocalRequest().getCookies()) {
                if (AutobaseCookies.NAME_UUID.equals(cookie.getName())) {
                    try {
                        user = us.getByUuidString(cookie.getValue());
                        resultLog = "Logging-in user by UUID:" + cookie.getValue() + " from cookie";
                        ss.setSessionUser(user);
                    } catch (ServiceException e) {
                        logger.error("Cannot retrieve user by UUID: " + cookie.getValue());
                    }
                    break;
                }
            }
            if (user == null) resultLog = "User isn't logged in, going to main page";
        }
        logger.info(resultLog);
        return ServerUtils.buildUserDto(user);
    }
}
