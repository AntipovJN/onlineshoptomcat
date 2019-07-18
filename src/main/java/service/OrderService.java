package service;

import model.Order;
import model.User;

import java.util.List;

public interface OrderService {

    void addOrder(long id, String address, String payment, User user, String basket);

    Order getById(long id);

    List<Order> getAll();


    void updateOrder(Order order);

    void removeOrder(Order order);
}
