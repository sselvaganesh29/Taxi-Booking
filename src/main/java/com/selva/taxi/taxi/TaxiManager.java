    package com.selva.taxi.taxi;

    import com.selva.taxi.database.Database;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class TaxiManager
    {
        public static final TaxiManager INSTANCE = new TaxiManager();

        private TaxiManager()
        {
        }

        private Connection getConnection() throws Exception
        {
            return Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
        }

        public boolean addTaxi(Taxi taxi) throws Exception {
            String insertSql = "INSERT INTO tbl_taxi (driver_id,taxi_no, taxi_name) values(?,?,?)";

            try ( Connection connection = getConnection() )
            {

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(insertSql) )
                    {
                        preparedStatement.setInt(1,taxi.getDriverId());
                        preparedStatement.setInt(2, taxi.getTaxino());
                        preparedStatement.setString(3, taxi.getTaxiName());

                        int row = preparedStatement.executeUpdate();

                        return row > 0;
                    }


            }
            catch (Exception e)
            {
                throw e;
            }

        }

        public boolean updateTaxi(int taxiNo, String newTaxiName) throws Exception {
            String updateSql = "UPDATE tbl_taxi SET taxi_name = ? WHERE taxi_no = ?";

            try ( Connection connection = getConnection() )
            {

                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql))
                    {
                        preparedStatement.setString(1, newTaxiName);
                        preparedStatement.setInt(2, taxiNo);

                        int row = preparedStatement.executeUpdate();

                        if (row > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }

            }
            catch (Exception e)
            {
                throw e;
            }

        }

        public boolean deleteTaxi(int taxiNo) throws Exception {
            String deleteSql = "DELETE FROM tbl_taxi WHERE taxi_no = ?";

            try ( Connection connection = getConnection() )
            {

                    try ( PreparedStatement preparedStatement = connection.prepareStatement(deleteSql) )
                    {
                        preparedStatement.setInt(1,taxiNo);
                        int row = preparedStatement.executeUpdate();

                        if (row > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }

            }
            catch (Exception e)
            {
                throw  e;
            }

        }

        public boolean getTaxiByNo(int taxiNo) throws Exception {
            String selectSql = "SELECT * FROM tbl_taxi WHERE taxi_no = ?";

            try (Connection connection = getConnection() )
            {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
                    {
                        preparedStatement.setInt(1, taxiNo);

                        try (ResultSet resultSet = preparedStatement.executeQuery())
                        {
                            if (resultSet.next())
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                    }

            }
            catch (Exception e)
            {
                throw e;
            }

        }


        public List<Taxi> getAllTaxi() throws Exception {
            String selectSql = "SELECT * FROM tbl_taxi";
            List<Taxi> taxiList = new ArrayList<>();

            try (Connection connection = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva"))
            {

                    try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
                    {
                        try (ResultSet resultSet = preparedStatement.executeQuery())
                        {
                            while (resultSet.next())
                            {

                               int taxiNo = resultSet.getInt(2);
                               String taxiName =resultSet.getString(3);

                               taxiList.add(new Taxi(taxiNo,taxiName));

                            }

                            return taxiList;
                        }
                    }

            }
            catch (Exception e)
            {
                throw e;
            }

        }
    }
