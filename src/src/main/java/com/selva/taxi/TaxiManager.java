package com.selva.taxi;

import com.selva.database.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class TaxiManager
{


    public static final TaxiManager INSTANCE = new TaxiManager();


    private TaxiManager()
    {

    }


    public void addTaxi(Taxi taxi)
    {

        try
        {
            Connection connection = DataBase.getConnection("jdbc:postgresql://localhost:5432/taxi","superuser","selva" );

            String insertSql = "INSERT INTO taxi_info ( taxi_id, taxi_name ) values( ?, ? )";

            PreparedStatement preparedStatement = connection.prepareStatement( insertSql );

            preparedStatement.setInt(1, taxi.getTaxino());
            preparedStatement.setString(2, taxi.getTaxiName());

            int row = preparedStatement.executeUpdate();

            if ( row > 0)
            {
                System.out.println("row added");
            }
            else
            {
                System.out.println("row not added!!!");
            }

            System.out.println( "Your data added successfully" );
        }
        catch (Exception e)
        {
            System.out.println( "Your data Failed to add!!!");
        }

    }


    public void updateTaxi(int taxiNo, String newTaxiName)
    {

        try
        {
            Connection connection = DataBase.getConnection("jdbc:postgresql://localhost:5432/taxi","superuser","selva");

            String updateSql = " UPDATE taxi_info SET taxi_name = ? WHERE taxi_id = ? " ;

            PreparedStatement  preparedStatement = connection.prepareStatement( updateSql );

            preparedStatement.setString(1, newTaxiName );
            preparedStatement.setInt(2, taxiNo);

            int row = preparedStatement.executeUpdate();

            if ( row > 0)
            {
                System.out.println("row updated");
            }
            else
            {
                System.out.println("row not updated!!!");
            }


            System.out.println( "Your data update successfully" );
        }
        catch (Exception e)
        {
            System.out.println(" Your data Failed to update!!!");
        }


    }


    public void deleteTaxi(int taxiNo)
    {
        try {
            Connection connection = DataBase.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");

            String deleteSql = " DELETE FROM taxi_info WHERE taxi_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);

            preparedStatement.setInt(1, taxiNo);

            preparedStatement.executeUpdate();

            System.out.println("Your data deleted successfully");
        } catch (Exception e) {
            System.out.println(" Failed to delete!!!");
        }

    }

}
