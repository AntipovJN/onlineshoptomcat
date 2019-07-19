package factory;

import dao.ProductDao;
import dao.impl.ProductDaoJDBC;

public class ProductDaoFactory {

    private static ProductDao instance;

    public static synchronized ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }
}
