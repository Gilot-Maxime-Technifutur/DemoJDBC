package be.technifutur.dao.impl;

import be.technifutur.ConnectionFactory;
import be.technifutur.dao.ShipperDAO;
import be.technifutur.model.Shipper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShipperDAOimpl implements ShipperDAO {
    @Override
    public void insert(Shipper entity) {
        String sql = """
            INSERT INTO Shippers (Shipper_id, company_name, phone)
            VALUES (?,?,?)
            """;

//         et si entity.address = "); DROP TABLE northwind; SELECT(''"
//         -> requete preparee

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setString(2, entity.getCompanyName());
            stmt.setString(3, entity.getPhone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insert impossible", e);
        }
    }

    @Override
    public List<Shipper> getAll() {
        String sql = """
                SELECT *
                FROM Shippers
                """;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            List<Shipper> l = new ArrayList<>();
            while (rs.next()) {
                l.add(Converter.convert(rs, Shipper.class));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public Optional<Shipper> getOne(Long id) {
        String sql = """
                SELECT *
                FROM Shippers
                WHERE Shipper_id = 
                """ + id;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            if(rs.next())
                return Optional.of(Converter.convert(rs, Shipper.class));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public void update(Long id, Shipper entity) {
        String sql = """
                UPDATE table 
                SET company_name = ?
                , phone = ?
                WHERE Shipper_id = ?
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setString(1, entity.getCompanyName());
            stmt.setString(2, entity.getPhone());
            stmt.setLong(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("update impossible", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = """
                DELETE FROM Shippers 
                WHERE Shipper_id = ? 
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);

            stmt.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException("delete impossible", ex);
        }
    }
}
