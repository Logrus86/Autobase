package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;

public class ServerUtils {
    public static UserDtoGwt buildUserDtoGwt(User user) {
        if (user == null) return null;
        UserDtoGwt result = new UserDtoGwt()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setDob(user.getDobString())
                .setEmail(user.getEmail())
                .setBalance(user.getBalance());
        switch (user.getRole()) {
            case ADMIN: result.setRole(0);
                break;
            case CLIENT: result.setRole(1);
                break;
            case DRIVER: result.setRole(2);
                break;
        }
        return result;
    }

    public static UserDtoGwt buildUserDtoGwt(UserDto userDto) {
        if (userDto == null) return null;
        UserDtoGwt result = new UserDtoGwt()
                .setId(userDto.getId())
                .setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setFirstname(userDto.getFirstname())
                .setLastname(userDto.getLastname())
                .setDob(userDto.getDob())
                .setEmail(userDto.getEmail())
                .setBalance(userDto.getBalance());
        switch (userDto.getRole()) {
            case ADMIN: result.setRole(0);
                break;
            case CLIENT: result.setRole(1);
                break;
            case DRIVER: result.setRole(2);
                break;
        }
        return result;
    }
}
