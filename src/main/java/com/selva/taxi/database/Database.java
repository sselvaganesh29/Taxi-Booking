package com.selva.taxi.database;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{

private static HikariDataSource dataSource;

static
{
    try
    {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/taxi");
        config.setUsername("superuser");
        config.setPassword("selva");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);

        dataSource = new HikariDataSource(config);

        System.out.println("-----Connection pooling successfully-----");
    }
    catch (Exception e)
    {
        System.out.println("Failed to connect database!!!");
    }
}

    public static Connection getConnection(String URL,String USERNAME,String PASSWORD)throws Exception
    {
        if (dataSource != null)
        {
            return dataSource.getConnection();
        }
        else
        {
            throw new SQLException("Datasource is not connected");
        }
    }




    public static void closeConnection()
    {
        if ( dataSource != null )
        {
            dataSource.close();
            System.out.println(" Connection Closed");
        }
        else
        {
            System.out.println(" Connection null ");
        }


    }

}