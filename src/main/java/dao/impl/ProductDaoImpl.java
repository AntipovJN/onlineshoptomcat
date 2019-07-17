package dao.impl;

import dao.ProductDao;
import model.Product;
import service.Database;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> getAll() {
        return Database.PRODUCTS;
    }

    @Override
    public void addProduct(Product item) {
        Database.PRODUCTS.add(item);
    }

    @Override
    public Product getById(long id) {
        for (Product product : Database.PRODUCTS) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        Database.PRODUCTS.set(Database.PRODUCTS.indexOf(getById(product.getId())), product);
    }

    @Override
    public void removeProduct(Product product) {
        Database.PRODUCTS.remove(product);
    }
}
