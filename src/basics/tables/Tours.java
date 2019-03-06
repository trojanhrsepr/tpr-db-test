package basics.tables;

import basics.DBType;
import basics.DBUtil;
import basics.beans.TourBean;

import java.sql.*;
import java.text.NumberFormat;

public class Tours {
    public static void displayData(ResultSet rs) {
        try {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("Tour " + rs.getInt("tourId") + ": ");
                buffer.append(rs.getString("tourName"));
                double price = rs.getDouble("price");
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String priceF = nf.format(price);
                buffer.append(" ("+priceF+")");
                System.out.println(buffer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TourBean getTour(int tourId) {
        TourBean tour = new TourBean();
        String SQL = "SELECT tourId, tourName, price FROM tours WHERE tourId == ?";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL);
                ) {
            stmt.setInt(1,tourId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tour.setTourId(tourId);
                tour.setTourName(rs.getString("tourName"));
                tour.setPrice(rs.getDouble("price"));
                return tour;
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addTour(TourBean tour) {
        String SQL = "INSERT INTO tours (tourId, tourName, price, packageId) VALUES (?,?,?, ?)";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL);
                ) {
            stmt.setInt(1, tour.getTourId());
            stmt.setString(2, tour.getTourName());
            stmt.setDouble(3, tour.getPrice());
            stmt.setInt(4, tour.getpackageId());
            int res = stmt.executeUpdate();
            if(res == 1) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateTour(TourBean tour) {
        String SQL = "UPDATE tours SET tourName = ?, price = ? WHERE tourId = ?";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(SQL);
        ) {
            stmt.setDouble(3, tour.getPrice());
            stmt.setString(2, tour.getTourName());
            stmt.setInt(1, tour.getTourId());
            int res = stmt.executeUpdate();
            if(res == 1) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
