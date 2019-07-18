package service;

import model.Code;
import model.Order;
import model.User;

import java.util.List;

public interface OrderService {

    Code addOrder(String address, String payment, User user, String basket);

    Order getById(long id);

    Order getByCode(Code code);

    List<Order> getAll();

    void updateOrder(Order order);

    void removeOrder(Order order);
}
