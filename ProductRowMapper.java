package com.example.conductor.rowMapper;

import com.example.conductor.model.Product;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setUrl(rs.getString("url"));
        product.setPayload(rs.getString("payload"));
        product.setResponse(rs.getString("response"));

        return product;
    }
}
