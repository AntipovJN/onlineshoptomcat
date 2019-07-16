package dao.impl;

import dao.ProductDao;
import model.Product;
import service.Database;
import java.util.List;

public class ProductDaoImpl implements ProductDao {


    @Override
    public List<Product> getAll() {
        return Database.ITEMS;
    }

    @Override
    public void addItem(Product item) {
        Database.ITEMS.add(item);
    }
}
