package factory;

import dao.OrderDao;
import dao.impl.JDBC.OrderDaoJDBC;

public class OrderDaoFactory {

    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }
}
