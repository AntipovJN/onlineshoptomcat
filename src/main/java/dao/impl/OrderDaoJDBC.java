package dao.impl;

import dao.OrderDao;
import model.Code;
import model.Order;
import org.apache.log4j.Logger;
import utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoJDBC implements OrderDao {

    Logger logger = Logger.getLogger(OrderDaoJDBC.class);

    @Override
    public void addOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    "INSERT INTO order_test (address, payment, code, products, userid) "
                            + "VALUES ('%s','%s','%s','%s','%s')",
                    order.getAddress(), order.getPayment(), order.getCode().getCode(),
                    order.getListOfProducts(), order.getCode().getUserId()));
            logger.info(String.format("Added order for userID='%s'", order.getCode().getUserId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }

    @Override
    public Optional<Order> getById(long id) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM order_test WHERE id='%s'", id));
            resultSet.next();
            return Optional.of(new Order(resultSet.getLong("id"),
                    resultSet.getString("address"),
                    resultSet.getString("payment"),
                    new Code(Integer.valueOf(resultSet.getString("code")),
                            resultSet.getLong("userid")),
                    resultSet.getString("products")));
        } catch (SQLException e) {
            logger.error(String.format("Failed getting order with id='%s'", id), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getByCode(Code code) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    String.format("SELECT * FROM order_test WHERE code='%s' AND userid='%s'"
                            , code.getCode(), code.getUserId()));
            resultSet.next();
            return Optional.of(new Order(resultSet.getLong("id"),
                    resultSet.getString("address"),
                    resultSet.getString("payment"),
                    new Code(Integer.valueOf(resultSet.getString("code")),
                            resultSet.getLong("userid")),
                    resultSet.getString("products")));
        } catch (SQLException e) {
            logger.error(String.format("Failed getting order where userid='%s'", code.getUserId()), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM order_test");
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong("id"),
                        resultSet.getString("address"),
                        resultSet.getString("payment"),
                        new Code(Integer.valueOf(resultSet.getString("code")),
                                resultSet.getLong("userid")),
                        resultSet.getString("products")));
            }
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE order_test SET"
                            + " address='%s', payment='%s', code='%s',"
                            + " products='%s', userid='%s' WHERE id='%s'",
                    order.getAddress(), order.getPayment(),
                    order.getCode().getCode(), order.getListOfProducts(),
                    order.getCode().getUserId()));
            logger.info(String.format("Updated order with id = '%s'", order.getId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }

    @Override
    public void removeOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM order_test WHERE id='%s'", order.getId()));
            logger.info(String.format("Order with id = '%s' was deleted", order.getId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }
}
