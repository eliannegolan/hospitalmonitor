package com.example.application.views;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainLayout
{

    public Chart MainLayout()
    {
        HR_data HeartRate = new HR_data();
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration(); //Conf object allows access to chart properties from Configuration class
        conf.setTitle("Live Heart Rate Data");

        conf.getChart().setType(ChartType.SPLINE); //Spline is the type of scatter plot desired for our output

        XAxis xAxis = conf.getxAxis();
        xAxis.setTitle(new AxisTitle("Time"));
        xAxis.setType(AxisType.DATETIME);
        xAxis.setTickPixelInterval(150);

        YAxis yAxis = conf.getyAxis();
        yAxis.setTitle(new AxisTitle("Heart Rate (bpm)"));

        conf.getTooltip().setEnabled(false);
        conf.getLegend().setEnabled(false);


        ListSeries series = new ListSeries("Heart Rate");
        series.setPlotOptions(new PlotOptionsSpline());
        series.setData((java.util.List<Number>) HeartRate);

        conf.setSeries(series);

        return chart;



    }


    public int GetHR() throws SQLException
    {
        Integer hr = null;
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e)
        {}

        Connection conn = DriverManager.getConnection(dbUrl, "postgres", "rules;eyes");

        try {
            Statement s = conn.createStatement();
            String sqlS = "SELECT * from hr;";
            ResultSet rs = s.executeQuery(sqlS);

            List<Integer> HeartRate = new ArrayList<>();
            while (rs.next())
            {
                HeartRate.add(rs.getInt("hr"));
            }

            rs.close();
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return hr ;
    }

}



//  private void setContent(VerticalLayout layout) {
//}
