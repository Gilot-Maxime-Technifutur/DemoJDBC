package be.technifutur.dao.impl;

import be.technifutur.model.Customer;
import be.technifutur.model.Product;
import be.technifutur.model.Shipper;
import be.technifutur.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Converter {
    static <T> T convert(ResultSet rs, Class<T> resultClass) throws SQLException {
        if(resultClass.equals(Product.class))
            return (T)convertProduct(rs);
        if(resultClass.equals(Supplier.class))
            return (T)convertSupplier(rs);
        if(resultClass.equals(Shipper.class))
            return (T)convertShipper(rs);
        if(resultClass.equals(Customer.class))
            return (T)convertCustomer(rs);
        
        throw new IllegalArgumentException("resultClass invalid");
    }
    
    private static Product convertProduct(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setId(rs.getLong("product_id"));
        p.setDiscontinued(rs.getBoolean("discontinued"));
        p.setName(rs.getString("product_name"));
        p.setQttPerUnit(rs.getString("quantity_per_unit"));
        p.setUnitPrice(rs.getDouble("unit_price"));
        p.setStock(rs.getInt("units_in_stock"));

        if(rs.getObject("supplier_id") != null)
            p.setSupplier(convertSupplier(rs));

        return p;
    }

    private static Supplier convertSupplier(ResultSet rs) throws SQLException {
        Supplier s = new Supplier();

        s.setId(rs.getLong("supplier_id"));
        s.setAddress(rs.getString("address"));
        s.setCity(rs.getString("city"));
        s.setCompanyName(rs.getString("company_name"));
        s.setContactName(rs.getString("contact_name"));
        s.setContactTitle(rs.getString("contact_title"));
        s.setCountry(rs.getString("country"));
        s.setFax(rs.getString("fax"));
        s.setHomepage(rs.getString("homepage"));
        s.setPhone(rs.getString("phone"));
        s.setPostalCode(rs.getString("postal_code"));
        s.setRegion(rs.getString("region"));

        return s;
    }

    private static Shipper convertShipper(ResultSet rs) throws SQLException {
        Shipper s = new Shipper();

        s.setId(rs.getLong("shipper_id"));
        s.setCompanyName(rs.getString("company_name"));
        s.setPhone(rs.getString("phone"));

        return s;
    }

    private static Customer convertCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();

        c.setId(rs.getLong("customer_id"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setCompanyName(rs.getString("company_name"));
        c.setContactName(rs.getString("contact_name"));
        c.setContactTitle(rs.getString("contact_title"));
        c.setCountry(rs.getString("country"));
        c.setFax(rs.getString("fax"));
        c.setPhone(rs.getString("phone"));
        c.setPostalCode(rs.getString("postal_code"));
        c.setRegion(rs.getString("region"));

        return c;
    }
}
