package model.dao.impl;

import db.DB;
import db.DbException;
import model.Category;
import model.Product;
import model.dao.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
