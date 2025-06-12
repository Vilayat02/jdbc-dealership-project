package dao;

import model.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // Phase 1 search
    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            return extractVehicles(ps.executeQuery());
        }
    }

    public List<Vehicle> findByMakeAndModel(String make, String model) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE VehicleMake LIKE ? OR VehicleModel LIKE ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + (make  != null ? make  : "") + "%");
            ps.setString(2, "%" + (model != null ? model : "") + "%");
            return extractVehicles(ps.executeQuery());
        }
    }

    public List<Vehicle> findByYearRange(int minYear, int maxYear) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE VehicleYear BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);
            return extractVehicles(ps.executeQuery());
        }
    }

    public List<Vehicle> findByColor(String color) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE Color = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, color);
            return extractVehicles(ps.executeQuery());
        }
    }

    public List<Vehicle> findByMileageRange(int minMileage, int maxMileage) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE Mileage BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minMileage);
            ps.setInt(2, maxMileage);
            return extractVehicles(ps.executeQuery());
        }
    }

    public List<Vehicle> findByType(String type) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE VehicleType = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            return extractVehicles(ps.executeQuery());
        }
    }

    // Bonus: Get All Vehicles
    public List<Vehicle> getAllVehicles() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement()) {
            return extractVehicles(st.executeQuery("SELECT * FROM vehicles"));
        }
    }

    // Phase 2 add & delete
    public void addVehicle(Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicles (Vin, VehicleMake, VehicleModel, Color, VehicleYear, VehicleType, Mileage, Price, is_sold) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, v.getVin());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setString(4, v.getColor());
            ps.setInt(5, v.getYear());
            ps.setString(6, v.getVehicleType());
            ps.setInt(7, v.getMileage());
            ps.setDouble(8, v.getPrice());
            ps.setBoolean(9, v.isSold());
            ps.executeUpdate();
        }
    }

    public boolean removeVehicle(int vin) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE Vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vin);
            return ps.executeUpdate() > 0;
        }
    }

    private List<Vehicle> extractVehicles(ResultSet rs) throws SQLException {
        List<Vehicle> list = new ArrayList<>();
        while (rs.next()) {
            Vehicle v = new Vehicle(
                    rs.getInt("Vin"),
                    rs.getString("VehicleMake"),
                    rs.getString("VehicleModel"),
                    rs.getString("Color"),
                    rs.getInt("VehicleYear"),
                    rs.getString("VehicleType"),
                    rs.getInt("Mileage"),
                    rs.getDouble("Price"),
                    rs.getBoolean("is_sold")
            );
            list.add(v);
        }
        return list;
    }
}
