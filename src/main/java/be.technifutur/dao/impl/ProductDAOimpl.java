package be.technifutur.dao.impl;

import be.technifutur.ConnectionFactory;
import be.technifutur.dao.ProductDAO;
import be.technifutur.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOimpl implements ProductDAO {
    @Override
    public void insert(Product entity) {
        String sql = """
            INSERT INTO Products (Product_id, discontinued, product_name, quantity_per_unit, supplier_id, unit_price, units_in_stock)
            VALUES (?,?,?,?,?,?,?)
            """;

//         et si entity.address = "); DROP TABLE northwind; SELECT(''"
//         -> requete preparee

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setLong(1, entity.getId());
            stmt.setBoolean(2, entity.isDiscontinued());
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getQttPerUnit());
            stmt.setLong(5, entity.getSupplier().getId());
            stmt.setDouble(6, entity.getUnitPrice());
            stmt.setInt(7, entity.getStock());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("insert impossible", e);
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = """
                SELECT *
                FROM Products
                """;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            List<Product> l = new ArrayList<>();
            while (rs.next()) {
                l.add(Converter.convert(rs, Product.class));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public Optional<Product> getOne(Long id) {
        String sql = """
                SELECT *
                FROM Products
                WHERE Product_id = 
                """ + id;

        try (
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            if(rs.next())
                return Optional.of(Converter.convert(rs, Product.class));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("error in data access", e);
        }
    }

    @Override
    public void update(Long id, Product entity) {
        String sql = """
                UPDATE table 
                SET discontinued = ?
                , product_name = ?
                , quantity_per_unit = ?
                , supplier_id = ?
                , unit_price = ?
                , units_in_stock = ?
                WHERE Product_id = ?
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ) {
            stmt.setBoolean(1, entity.isDiscontinued());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getQttPerUnit());
            stmt.setLong(4, entity.getSupplier().getId());
            stmt.setDouble(5, entity.getUnitPrice());
            stmt.setInt(6, entity.getStock());
            stmt.setLong(7, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("update impossible", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = """
                DELETE FROM Products 
                WHERE Product_id = ? 
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
