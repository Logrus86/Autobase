package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.cdi.SessionState;
import com.epam.bp.autobase.gwt.client.rpc.RegisterService;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.service.UserService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.logging.Logger;

import javax.inject.Inject;

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
            us.create(userDto);
            ss.setSessionUser(userDto.buildLazyEntity());
            logger.info("Newly registered user: " + ss.getSessionUser().toString());
            return userDto;
        } catch (Exception e) {
            logger.error("Registration error: " + e.getMessage());
        }
        return null;
    }
}
