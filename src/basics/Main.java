package basics;

import basics.beans.TourBean;
import basics.tables.Tours;
import basics.util.InputHelper;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        double maxPrice;
        String SQL = "SELECT tourId, tourName, price FROM tours WHERE price <= ?";
        try {
            maxPrice = InputHelper.getDoubleInput("Enter max value: ");
        } catch (NumberFormatException e) {
            return;
        }

        try(
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                // Will receive a scrollable result set as type is mentioned
                // Scrollable by default in SQL
                // Number of rows: 1-50, not 0-49
                // Same result with both create statements
                // Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                PreparedStatement stmt = conn.prepareStatement(SQL)
                ) {

            // For data control, use LIMIT 0,5 in query
            stmt.setDouble(1, 2000);
            ResultSet rs = stmt.executeQuery();
            Tours.displayData(rs);

            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
            rs.first();
            System.out.println("First row: "+rs.getString("tourName"));
            System.out.println("First row: "+rs.getObject("tourName", String.class));
            rs.last();
            System.out.println("Last row: "+rs.getString("tourName"));
            rs.absolute(3);
            System.out.println("3rd row: "+rs.getString("tourName"));
            TourBean t = new TourBean();
            t.setPrice(123);
            t.setTourName("NewTour");
            t.setTourId(123);
            stmt.setDouble(1, maxPrice);
            rs = stmt.executeQuery();
            Tours.displayData(rs);
            Tours.addTour(t);
            t.setTourName("NewTour1");
            Tours.updateTour(t);
            Tours.displayData(rs);

        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassCastException e) {
            System.err.println(e);
        }
    }
}
