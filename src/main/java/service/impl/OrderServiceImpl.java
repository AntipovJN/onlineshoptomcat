package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Code;
import model.Order;
import model.User;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public Code addOrder(String address, String payment, User user, String basket) {
        Code code = new Code((int) (Math.random() * 10000), user.getId());
        Order order = new Order(address, payment, code, basket);
        orderDao.addOrder(order);
        return code;
    }

    @Override
    public Order getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public Order getByCode(Code code) {
        return orderDao.getByCode(code);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void removeOrder(Order order) {

    }
}
