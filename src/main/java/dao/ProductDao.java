package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    void addProduct(Product item);

    Product getById(long id);

    void updateProduct(Product product);

    void removeProduct(Product product);
}
