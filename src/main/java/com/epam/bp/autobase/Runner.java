package com.epam.bp.autobase;

import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        String str = "abc>";
        char lastSymbol = str.charAt(str.length()-1);
        if ((lastSymbol == '>') || (lastSymbol == '<')) str = str.substring(0,str.length()-1);
        System.out.println("abc> | last: "+lastSymbol+" | result: "+str);
    }
}
