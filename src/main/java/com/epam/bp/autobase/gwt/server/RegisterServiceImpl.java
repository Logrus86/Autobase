package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.gwt.client.rpc.RegisterService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.service.UserService;
import com.epam.bp.autobase.util.AutobaseCookies;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.util.UUID;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {
    @Inject
    Logger logger;
    @Inject
    SessionState ss;
    @Inject
    UserService us;

    @Override
    public UserDto register(UserDto userDto) {
        try {
            //persist user created from dto
            us.create(userDto);

            //write UUID to successfully created user
            User user = userDto.buildLazyEntity();
            user.setUuid(String.valueOf(UUID.randomUUID()));

            //write this UUID to persistence context
            us.update(user);

            //save it to cookie to perform auto-login in the future
            Cookie uuidCookie = new Cookie(AutobaseCookies.NAME_UUID, String.valueOf(user.getUuid()));
            uuidCookie.setMaxAge(AutobaseCookies.MAX_AGE_UUID);
            this.getThreadLocalResponse().addCookie(uuidCookie);

            //save user to session bean
            ss.setSessionUser(user);
            logger.info("Newly registered user '" + ss.getSessionUser().getUsername() + "' has logged in.");

            //return dto to view
            return userDto;
        } catch (Exception e) {
            logger.error("Registration error: " + e.getMessage());
            return null;
        }
    }
}
