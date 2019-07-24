package dao.impl.JDBC;

import dao.OrderDao;
import model.Code;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoJDBC implements OrderDao {

    private Logger logger = Logger.getLogger(OrderDaoJDBC.class);

    @Override
    public void addOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO order_test (address, payment, code, products, userid) "
                            + "VALUES (?,?,?,?,?)");
            statement.setString(1, order.getAddress());
            statement.setString(2, order.getPayment());
            statement.setInt(3, order.getCode().getCodeValue());
            statement.setString(4, order.getListOfProductsString());
            statement.setLong(5, order.getCode().getUser().getId());
            statement.execute();
            logger.info(String.format("Added order for userID='%s'",
                    order.getCode().getUser().getId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }

    @Override
    public Optional<Order> getById(long id) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT order_test.id, address, payment, code, products, userid, email, password, role"
                            + " FROM order_test INNER JOIN users_test ON userid = users_test.id"
                            + " WHERE order_test.id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return getOrderFromResultset(resultSet);
        } catch (SQLException e) {
            logger.error(String.format("Failed getting order with id='%s'", id), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getByCode(Code code) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT order_test.id, address, payment, code, products, userid, email, password, role "
                            + "FROM order_test INNER JOIN users_test "
                            + "ON userid = users_test.id WHERE code=? AND userid=?");
            statement.setString(1, Integer.toString(code.getCodeValue()));
            statement.setLong(2, code.getUser().getId());
            ResultSet resultSet = statement.executeQuery();
            return getOrderFromResultset(resultSet);
        } catch (SQLException e) {
            logger.error(String.format("Failed getting order where userid='%s'",
                    code.getUser().getId()), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, address, payment, code, products, userid, email, password, role"
                            + " FROM order_test INNER JOIN users_test ON userid = users_test.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getOrderFromResultset(resultSet).isPresent()) {
                    orders.add(getOrderFromResultset(resultSet).get());
                }
            }
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE order_test SET address=?, payment=?,"
                            + " code=? , products=?, userid=? WHERE id=?");
            statement.setString(1, order.getAddress());
            statement.setString(2, order.getPayment());
            statement.setInt(3, order.getCode().getCodeValue());
            statement.setString(4, order.getListOfProductsString());
            statement.setLong(5, order.getCode().getUser().getId());
            statement.setLong(6, order.getId());
            statement.executeUpdate();
            logger.info(String.format("Updated order with id = '%s'", order.getId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }

    @Override
    public void removeOrder(Order order) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM order_test WHERE id=?");
            statement.setLong(1, order.getId());
            statement.execute();
            logger.info(String.format("Order with id = '%s' was deleted", order.getId()));
        } catch (SQLException e) {
            logger.error("Failed adding order", e);
        }
    }

    private Optional<Order> getOrderFromResultset(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Order order = new Order(
                        resultSet.getString("address"),
                        resultSet.getString("payment"),
                        new Code(Integer.valueOf(resultSet.getString("code")),
                                new User(resultSet.getLong("userid"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("role"))));
                order.setId(resultSet.getLong("id"));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
