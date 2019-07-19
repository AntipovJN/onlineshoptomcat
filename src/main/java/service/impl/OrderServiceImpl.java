package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Code;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);

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
        if (orderDao.getById(id).isPresent()) {
            return orderDao.getById(id).get();
        }
        logger.error(String.format("Order with id = '%d' is not exist", id));
        return null;
    }

    @Override
    public Order getByCode(Code code) {
        if (orderDao.getByCode(code).isPresent()) {
            return orderDao.getByCode(code).get();
        }
        logger.error(String.format("Order with userID = '%s' and code = '%d' is not exist",
                code.getUserId(), code.getCode()));
        return null;
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
