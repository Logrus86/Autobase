package com.epam.bp.autobase;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Runner {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Date dateStart = new Date();
        System.out.println("Start date: "+dateStart);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(dateStart);
        endDate.add(Calendar.DATE,40);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("End date: "+sdf.format(endDate.getTime()));
    }
}
