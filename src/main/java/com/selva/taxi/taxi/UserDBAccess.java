package com.selva.taxi.taxi;

import com.selva.taxi.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDBAccess {

    public static int getUserId(String userName) throws Exception {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (Connection con = Database.getConnection("jdbc:postgresql://localhost:5432/taxi", "superuser", "selva");
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, userName);

            try (ResultSet rs = preparedStatement.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (Exception e) {
            throw e;
        }
        return -1;
    }



}
