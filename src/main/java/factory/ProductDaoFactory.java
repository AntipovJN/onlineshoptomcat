package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import utils.IdGenerator;

public class ProductDaoFactory {

    private static ProductDao instance;

    public static synchronized ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
            instance.addProduct(new Product(IdGenerator.getItemId(), "initial product", "intitial product", 100.2));
        }
        return instance;
    }
}
