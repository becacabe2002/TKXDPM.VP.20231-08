package com.grp08.capstoneprojectg08.util;


public class DateUnion {
    public static java.sql.Date SQLDateFromLocalDate(java.time.LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }
    public static java.sql.Date SQLDateFromUtilDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
}
