package com.epam.bp.autobase;

import com.epam.bp.autobase.entity.Autobase;
import com.epam.bp.autobase.entity.AutobaseFactory;

import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Autobase ab = AutobaseFactory.createRandomAutobase(10);
        System.out.println(ab);


    }
}
