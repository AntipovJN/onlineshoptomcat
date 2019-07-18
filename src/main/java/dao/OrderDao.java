package dao;

import model.Code;
import model.Order;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    Order getById(long id);

    Order getByCode(Code code);

    List<Order> getAll();

    void updateOrder(Order order);

    void removeOrder(Order order);
}
