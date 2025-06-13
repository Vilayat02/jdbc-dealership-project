package dao;

import model.SalesContract;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {
    private final DataSource ds;

    public SalesDao(DataSource ds) {
        this.ds = ds;
    }

    public void addSale(SalesContract sale) throws SQLException {
        String sql = "INSERT INTO sales_contracts (Vin, CustomerName, CustomerPhone, SalePrice) VALUES (?,?,?,?)";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, sale.getVin());
            ps.setString(2, sale.getCustomerName());
            ps.setString(3, sale.getCustomerPhone());
            ps.setBigDecimal(4, sale.getSalePrice());
            ps.executeUpdate();
        }
    }

    private List<SalesContract> extractSales(ResultSet rs) throws SQLException {
        List<SalesContract> list = new ArrayList<>();
        while (rs.next()) {
            SalesContract sc = new SalesContract(
                    rs.getInt("ContractID"),
                    rs.getString("Vin"),
                    rs.getString("CustomerName"),
                    rs.getString("CustomerPhone"),
                    rs.getBigDecimal("SalePrice")
            );
            list.add(sc);
        }
        return list;
    }

    public List<SalesContract> getAllSales() throws SQLException {
        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement()) {
            return extractSales(st.executeQuery("SELECT * FROM sales_contracts"));
        }
    }
}
