package com.epam.bp.autobase.dao;

import com.epam.bp.autobase.entity.User;

public class UserDao {
    public static User findByCredentials(String username, String password) {
        User user = new User(); //write it
        return user;
    }

    public static void add() {
        //put user into base
    }
}
/*
 get connection
send query
get resultset
set it to user
return user
 */
