package com.selva.taxi;

import com.selva.database.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Taxi
{
    private int taxiNo;

    private String taxiName;

    private boolean available;

    private static final int pricePerkm = 50;

    public Taxi(int taxiNo, String taxiName, boolean available)
    {
        this.taxiNo = taxiNo;
        this.taxiName = taxiName;
        this.available = available;
    }

    public Taxi(int taxiNo, String taxiName)
    {
        this.taxiNo = taxiNo;
        this.taxiName = taxiName;
    }

    public int getTaxino() {
        return taxiNo;
    }

    public void setTaxino(int taxiNo) {
        this.taxiNo = taxiNo;
    }

    public String getTaxiName() {
        return taxiName;
    }

    public void setTaxiname(String taxiName) {
        this.taxiName = taxiName;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static int getPricePerkm() {
        return pricePerkm;
    }


    public void createRide( int rideId, Person person, int startingPoint, int endingPoint )
    {
        int kmTravelled = Math.abs(startingPoint - endingPoint);
        int fare = kmTravelled * pricePerkm;
        Ride ride = new Ride( rideId, this, person, startingPoint, endingPoint, kmTravelled, fare );
        ride.setEndingPoint( endingPoint );

        try
        {

            Connection connection = DataBase.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");

            String insertSql = " INSERT INTO ride_info ( ride_id, taxi_id, person_id, person_name, starting_point, ending_point, km_travelled, fare ) VALUES ( ?,?,?,?,?,?,?,? )";

            PreparedStatement preparedStatement = connection.prepareStatement( insertSql );

            preparedStatement.setInt(1, ride.getRideId());
            preparedStatement.setInt(2, this.getTaxino());
            preparedStatement.setInt(3, person.getPersonId());
            preparedStatement.setString(4, person.getPersonName());
            preparedStatement.setInt(5, startingPoint);
            preparedStatement.setInt(6, endingPoint);
            preparedStatement.setInt(7, kmTravelled);
            preparedStatement.setInt(8, fare);

            int row = preparedStatement.executeUpdate();

            if ( row > 0)
            {
                System.out.println("row added");
            }
            else
            {
                System.out.println("no row added!!!!");
            }

            System.out.println("Ride details add successfully");
        }
        catch (Exception e)
        {
            System.out.println("Ride details fails to add!!!!");
        }
    }

}
/*
   public void printRideDetails()
    {
        for (Ride ride : getRides())
        {
            System.out.println("Ride ID: " + ride.getRideId());
            System.out.println("Person ID: " + ride.getPerson().getPersonId());
            System.out.println("Person Name: " + ride.getPerson().getPersonName());
            System.out.println("Starting Point: " + ride.getStartingPoint());
            System.out.println("Ending Point: " + ride.getEndingPoint());
            System.out.println("km Travelled: " + ride.getDestination());
            System.out.println("Fare: " + ride.getFare());
        }
    }
*/


