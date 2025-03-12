package com.selva.taxi.taxi;

import com.selva.taxi.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Taxi
{

    private int driverId;

    private int taxiNo;

    private String taxiName;

    private boolean available;

    private static final int pricePerkm = 50;

    public Taxi ()
    {

    }

    public Taxi(int taxiNo, String taxiName)
    {
        this.taxiNo = taxiNo;
        this.taxiName = taxiName;
    }

    public Taxi(int driverId, int taxiNo, String taxiName) {
        this.driverId = driverId;
        this.taxiNo = taxiNo;
        this.taxiName = taxiName;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
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

    public static int getPricePerkm() {
        return pricePerkm;
    }


    public int createRide(int passengersId,int taxiNo,int startingPoint, int endingPoint ) throws Exception {
        int kmTravelled = Math.abs(startingPoint - endingPoint);
        int fare = kmTravelled * pricePerkm;


        String insertSql = " INSERT INTO tbl_ride (taxi_id, taxi_no, starting_point, ending_point, km_travelled, fare, passenger_id) VALUES ( ?,?,?,?,?,?,? )";
        boolean check = TaxiManager.INSTANCE.getTaxiByNo(taxiNo);
        int taxiId = getTaxiId(taxiNo);

        try ( Connection connection = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva") ) {
            if (check) {
                if (connection != null) {

                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

                        preparedStatement.setInt(1, taxiId);
                        preparedStatement.setInt(2, taxiNo);
                        preparedStatement.setInt(3, startingPoint);
                        preparedStatement.setInt(4, endingPoint);
                        preparedStatement.setInt(5, kmTravelled);
                        preparedStatement.setInt(6, fare);
                        preparedStatement.setInt(7,passengersId);

                        int row = preparedStatement.executeUpdate();

                        if (row > 0) {
                            return 1; // successful
                        } else {
                            return 2; // fails
                        }
                    }

                }
            }
            else
            {
                return  3; // no taxi Like this
            }



        }
        catch (Exception e)
        {
            throw  e;
        }
        return -1; // something went wrong;
    }

    private int getTaxiId(int taxiNo) throws Exception {
        String sql = "SELECT taxi_id FROM tbl_taxi WHERE taxi_no = ?";

        int res = -1;


        try(Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
            PreparedStatement preparedStatement = con.prepareStatement(sql)
            )
        {
            preparedStatement.setInt(1,taxiNo);

            try (ResultSet rs = preparedStatement.executeQuery())
            {

                if ( rs.next() )
                {
                    res = rs.getInt("taxi_id");
                    return  res;
                }
            }

        }
        catch (Exception e)
        {
            throw e;

        }
        return res;
    }

}


