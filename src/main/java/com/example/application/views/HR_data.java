package com.example.application.views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HR_data {
    public HR_data() {
    }

    public List<Integer> GetHR() throws SQLException {
        Integer hr = null;
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception var8) {
        }

        Connection conn = DriverManager.getConnection(dbUrl, "postgres", "rules;eyes");
        ArrayList HeartRate = null;

        try {
            Statement s = conn.createStatement();
            String sqlS = "SELECT * from hr;";
            ResultSet rs = s.executeQuery(sqlS);
            HeartRate = new ArrayList();

            while(rs.next()) {
                HeartRate.add(rs.getInt("hr"));
            }

            rs.close();
            s.close();
            conn.close();
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        return HeartRate;
    }
}
