package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private Logger logger = Logger.getLogger(ProductDaoJDBC.class);

    @Override
    public List<Product> getAll() {
        List<Product> productList = new LinkedList<>();
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM prodycts_test");
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            logger.error("Failed get list of Products", e);
        }
        return productList;
    }

    @Override
    public void addProduct(Product product) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    "INSERT INTO prodycts_test (name, description, price) VALUES ('%s','%s','%s')",
                    product.getName(), product.getDescription(), product.getPrice()));
            logger.info(String.format("Added new product with name = %s",product.getName()));
        } catch (SQLException e) {
            logger.error("Failed adding new product to DB", e);
        }
    }

    @Override
    public Product getById(long id) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM prodycts_test WHERE id='%s'", id));
            resultSet.next();
            return new Product(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"));
        } catch (SQLException e) {
            logger.error(String.format("Failed getting product with id = %s", id), e);
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(
                    "UPDATE prodycts_test SET name='%s', description='%s', price='%s' WHERE id='%s'",
                    product.getName(), product.getDescription(),
                    product.getPrice(), product.getId()));
            logger.info(String.format("Updated new product with id = %s", product.getId()));
        } catch (SQLException e) {
            logger.error(String.format("Failed updating product with id = %s", product.getId()), e);
        }
    }

    @Override
    public void removeProduct(Product product) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    "DELETE FROM prodycts_test WHERE id='%s'", product.getId()));
            logger.info(String.format("Deleted new product with id = %s", product.getId()));
    } catch (SQLException e) {
            logger.error(String.format("Failed removing product with id = %s", product.getId()), e);
        }
    }
}
