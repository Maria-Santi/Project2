package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){
        try{
            Connection connection = DriverManager.getConnection("");
            return connection;
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(createConnection());
    }
}