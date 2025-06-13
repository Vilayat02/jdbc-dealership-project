package dao;

import model.LeaseContract;
import model.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao {
    private final DataSource ds;

    public LeaseDao(DataSource ds) {
        this.ds = ds;
    }

    public void addLease(LeaseContract lease) throws SQLException {
        String sql = "INSERT INTO lease_contracts " +
                "(Vin, CustomerName, CustomerPhone, LeaseStartDate, LeaseEndDate, MonthlyPayment,MileageLimit, Deposit) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, lease.getVin());
            ps.setString(2, lease.getCustomerFullName());
            ps.setString(3, lease.getCustomerPhone());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(lease.getLeaseStartDate()));
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(lease.getLeaseEndDate()));
            ps.setBigDecimal(6, lease.getMonthlyPayment());
            ps.setInt(7, lease.getMileageLimit());
            ps.setBigDecimal(8, lease.getDeposit());
            ps.executeUpdate();
        }
    }

    public List<LeaseContract> getAllLeases() throws SQLException {
        String sql = "SELECT LeaseContractID,Vin,CustomerFullName,CustomerPhone,LeaseStartDate,LeaseEndDate,MonthlyPayment,MileageLimit,Deposit FROM lease_contracts";

        List<LeaseContract> list = new ArrayList<>();
        try (Connection c = ds.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                LeaseContract lc = new LeaseContract(
                        rs.getInt("LeaseContractID"),
                        rs.getString("Vin"),
                        rs.getString("CustomerFullName"),
                        rs.getString("CustomerPhone"),
                        rs.getTimestamp("LeaseStartDate").toLocalDateTime(),
                        rs.getTimestamp("LeaseEndDate").toLocalDateTime(),
                        rs.getBigDecimal("MonthlyPayment"),
                        rs.getInt("MileageLimit"),
                        rs.getBigDecimal("Deposit")
                );
                list.add(lc);
            }
        }
        return list;
    }

    public List<LeaseContract> getAllLease() throws SQLException {
        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement()) {
            return extractLeases(st.executeQuery("SELECT * FROM lease_contracts"));
        }
    }

    private List<LeaseContract> extractLeases(ResultSet rs) throws SQLException {
        List<LeaseContract> list = new ArrayList<>();
        while (rs.next()) {
            LeaseContract lc = new LeaseContract(
                    rs.getInt("LeaseContractID"),
                    rs.getString("Vin"),
                    rs.getString("CustomerFullName"),
                    rs.getString("CustomerPhone"),
                    rs.getTimestamp("LeaseStartDate").toLocalDateTime(),
                    rs.getTimestamp("LeaseEndDate").toLocalDateTime(),
                    rs.getBigDecimal("MonthlyPayment"),
                    rs.getInt("MileageLimit"),
                    rs.getBigDecimal("Deposit")
            );
            list.add(lc);
        }
        return list;
    }
}
