package dao;

import model.Order;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    Order getById(long id);

    List<Order> getAll();

    void updateOrder(Order order);

    void removeOrder(Order order);
}
