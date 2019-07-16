package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao itemDao = ProductDaoFactory.getInstance();

    @Override
    public List<Product> getAll() {
        return itemDao.getAll();
    }

    @Override
    public void add(Product item) {
        itemDao.addItem(item);
    }
}
