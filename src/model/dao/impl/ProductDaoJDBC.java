package model.dao.impl;

import db.DB;
import db.DbException;
import model.Category;
import model.Product;
import model.dao.ProductDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoJDBC implements ProductDao {
    private Connection connection;

    public ProductDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product product) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO product (Name, Price, Quantity, CategoryId) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getQuantity());
            preparedStatement.setInt(4, product.getCategory().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    product.setId(id);
                }
            } else {
                throw new DbException("No rows were inserted!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Product findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT p.Id, p.Name, p.Price, p.Quantity, p.CategoryId, c.Name as CategoryName " +
                            "FROM product_db.product p " +
                            "LEFT JOIN category c on c.Id = p.CategoryId " +
                            "WHERE p.Id = ?"
            );

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(5));
                category.setName(resultSet.getString(6));

                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getDouble(4));
                product.setCategory(category);

                return product;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Product> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT p.Id, p.Name, p.Price, p.Quantity, p.CategoryId, c.Name as CategoryName " +
                            "FROM product_db.product p " +
                            "LEFT JOIN category c on c.Id = p.CategoryId"
            );

            resultSet = preparedStatement.executeQuery();

            List<Product> listProduct = new ArrayList<>();
            Map<Integer, Category> categoryMap = new HashMap<>();

            while (resultSet.next()) {
                int categoryId = resultSet.getInt(5);

                Category category = categoryMap.get(categoryId);

                if (category == null) {
                    category = new Category();
                    category.setId(categoryId);
                    category.setName(resultSet.getString(6));
                    categoryMap.put(categoryId, category);
                }

                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getDouble(4));
                product.setCategory(category);

                listProduct.add(product);
            }
            return listProduct;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public void update(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE product " +
                            "SET Name = ?, Price = ?, Quantity = ? " +
                            "WHERE Id = ?"
            );

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getQuantity());
            preparedStatement.setInt(4, product.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0){
                throw new DbException("No product found with the given ID!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM product WHERE Id = ?"
            );

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }
}
