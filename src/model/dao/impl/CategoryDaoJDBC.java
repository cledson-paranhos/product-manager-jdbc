package model.dao.impl;

import db.DB;
import db.DbException;
import model.Category;
import model.Product;
import model.dao.CategoryDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJDBC implements CategoryDao {
    private Connection connection;

    public CategoryDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Category category) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT into category (Id, Name) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    category.setId(id);
                }
            } else {
                throw new DbException("No rows were inserted!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Category findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT c.id, c.Name " +
                            "FROM category c " +
                            "WHERE c.Id = ?"
            );

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                return category;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Category> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT c.id, c.Name " +
                            "FROM category c " +
                            "ORDER BY c.id"
            );

            resultSet = preparedStatement.executeQuery();

            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                categories.add(category);
            }

            return categories;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Category category) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE category  " +
                            "SET name = ? " +
                            "WHERE id = ?"
            );

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new DbException("No category found with the given ID!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM category WHERE Id = ?"
            );

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("No category found with the given ID!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }
}
