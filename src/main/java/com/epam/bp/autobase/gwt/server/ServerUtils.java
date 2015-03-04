package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.model.dto.UserDto;
import com.epam.bp.autobase.model.entity.User;
import com.epam.bp.autobase.util.DateParser;

public class ServerUtils {
    public static UserDto buildUserDtoGwt(User user) {
        if (user == null) return null;
        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setDob(DateParser.stringFromDate(user.getDob()))
                .setEmail(user.getEmail())
                .setBalance(user.getBalance())
                .setRole(user.getRole());
    }

    public static UserDto buildUserDtoGwt(UserDto userDto) {
        if (userDto == null) return null;
        return new UserDto()
                .setId(userDto.getId())
                .setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setFirstname(userDto.getFirstname())
                .setLastname(userDto.getLastname())
                .setDob(userDto.getDob())
                .setEmail(userDto.getEmail())
                .setBalance(userDto.getBalance())
                .setRole(userDto.getRole());
    }
}
