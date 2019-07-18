package dao.impl;

import dao.OrderDao;
import model.Order;
import service.Database;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void addOrder(Order order) {
        Database.ORDERS.add(order);
    }

    @Override
    public Order getById(long id) {
        for (Order order : Database.ORDERS) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return Database.ORDERS;
    }

    @Override
    public void updateOrder(Order order) {
        Database.ORDERS.set(Database.ORDERS.indexOf(getById(order.getId())), order);
    }

    @Override
    public void removeOrder(Order order) {
        Database.ORDERS.remove(order);
    }
}
