package be.technifutur.dao.impl;

import be.technifutur.ConnectionFactory;
import be.technifutur.dao.CustomerDAO;
import be.technifutur.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOimpl implements CustomerDAO {
    @Override
    public void insert(Customer entity) {
        String sql = """
            INSERT INTO Customers (Customer_id, address, city, company_name, contact_name, contact_title, country, fax, phone, postal_code, region)
            VALUES (?,?,?,?,?,?,?,?,?,?,?)
            """;

//         et si entity.address = "); DROP TABLE northwind; SELECT(''"
//         -> requete preparee

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getAddress());
            stmt.setString(3, entity.getCity());
            stmt.setString(4, entity.getCompanyName());
            stmt.setString(5, entity.getContactName());
            stmt.setString(6, entity.getContactTitle());
            stmt.setString(7, entity.getCountry());
            stmt.setString(8, entity.getFax());
            stmt.setString(9, entity.getPhone());
            stmt.setString(10, entity.getPostalCode());
            stmt.setString(11, entity.getRegion());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insert impossible", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        String sql = """
                SELECT *
                FROM Customers
                """;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            List<Customer> l = new ArrayList<>();
            while (rs.next()) {
                l.add(Converter.convert(rs, Customer.class));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public Optional<Customer> getOne(String id) {
        String sql = """
                SELECT *
                FROM Customers
                WHERE Customer_id = ?
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
                return Optional.of(Converter.convert(rs, Customer.class));
            else
                return Optional.empty();
        }catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public void update(String id, Customer entity) {
        String sql = """
                UPDATE table 
                SET address = ?
                , city = ?
                , company_name = ?
                , contact_name = ?
                , contact_title = ?
                , country = ?
                , fax = ?
                , phone = ?
                , postal_code = ?
                , region = ?
                WHERE Customer_id = ?
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getAddress());
            stmt.setString(2, entity.getCity());
            stmt.setString(3, entity.getCompanyName());
            stmt.setString(4, entity.getContactName());
            stmt.setString(5, entity.getContactTitle());
            stmt.setString(6, entity.getCountry());
            stmt.setString(7, entity.getFax());
            stmt.setString(8, entity.getPhone());
            stmt.setString(9, entity.getPostalCode());
            stmt.setString(10, entity.getRegion());
            stmt.setString(11, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("update impossible", e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = """
                DELETE FROM Customers 
                WHERE Customer_id = ? 
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setString(1, id);

            stmt.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException("delete impossible", ex);
        }
    }
}
