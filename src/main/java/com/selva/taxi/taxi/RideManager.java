package com.selva.taxi.taxi;

import com.selva.taxi.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RideManager {

    public static final RideManager INSTANCE = new RideManager();

    private RideManager()
    {
    }

    public boolean setRideStatus(int rideId,String status) throws Exception {

        if ( !(isRideExist(rideId)) )
        {
              return false;
        }

        String sql = "UPDATE tbl_ride SET status = ? WHERE ride_id = ?";

        try (Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
             PreparedStatement preparedStatement = con.prepareStatement(sql))
        {
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,rideId);

            int updateRow = preparedStatement.executeUpdate();

            return updateRow > 0;
        }
        catch ( Exception e)
        {
            throw e;
        }
    }

    public int deleteRide(int rideId) throws Exception {

        if ( ! (isRideExist(rideId)) )
        {
            return 3; //ride doesn't Exist
        }

        String sql =  "DELETE FROM tbl_ride WHERE ride_id = ?";

       try ( Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
        PreparedStatement preparedStatement = con.prepareStatement(sql) )
       {
           preparedStatement.setInt(1,rideId);

           int row = preparedStatement.executeUpdate();

           if ( row > 0)
           {
               return 1; // successfully deleted
           }
           else {
               return 2; // something went wrong;
           }

       }
       catch ( Exception e)
       {
           throw  e;
       }


    }

    public boolean isRideExist(int rideId) throws Exception {
        String sql = "SELECT * FROM tbl_ride WHERE ride_id = ?";

       try ( Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
        PreparedStatement preparedStatement = con.prepareStatement(sql) ) {


           preparedStatement.setInt(1, rideId);

          try ( ResultSet rs = preparedStatement.executeQuery(); ) {

              if (rs.next()) {
                  return true;
              } else {
                  return false;
              }
          }
       }
       catch ( Exception e)
       {
           throw e;
       }
    }

    public int getRideTaxiId(int rideId) throws Exception {
        String sql = "SELECT taxi_id FROM tbl_ride WHERE ride_id = ?";

        try ( Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
              PreparedStatement preparedStatement = con.prepareStatement(sql) )
        {
            preparedStatement.setInt(1,rideId);

            try ( ResultSet rs = preparedStatement.executeQuery() )
            {
                return rs.getInt("taxi_id");
            }

        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
