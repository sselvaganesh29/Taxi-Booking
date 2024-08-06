package com.selva.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase
{


    public static Connection getConnection(String url,String user,String password)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgresSQL server successfully.");
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed!!!");
        }

        return connection;
    }


}