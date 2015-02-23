package com.epam.bp.autobase.gwt.server;

import com.epam.bp.autobase.gwt.dto.UserDtoGwt;
import com.epam.bp.autobase.model.entity.User;

public class ServerUtils {
    public static UserDtoGwt buildUserDtoGwt(User user) {
        if (user == null) return null;
        return new UserDtoGwt()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setDob(user.getDobString())
                .setEmail(user.getEmail())
                .setRole(user.getRoleInteger())
                .setBalance(user.getBalance());
    }
}
