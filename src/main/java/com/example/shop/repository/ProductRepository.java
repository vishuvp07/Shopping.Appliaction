package com.example.shop.repository;

import com.example.shop.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) ->
            new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("stock")
            );

    public List<Product> findAll() {
        String sql = "SELECT id, name, price, stock FROM products ORDER BY id";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public void save(Product product) {
        String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStock());
    }
}
