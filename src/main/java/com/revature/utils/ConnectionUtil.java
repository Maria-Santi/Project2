package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){
        try{
            String host = System.getenv("HOST");
            String port = System.getenv("PORT");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            String name = System.getenv("DB_NAME");
            System.out.println("jdbc:"+host+":"+port+"/"+name+"?user="+user+"&password="+password);
            Connection connection = DriverManager.getConnection("jdbc:"+host+":"+port+"/"+name+"?user="+user+"&password="+password);

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