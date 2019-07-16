package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    void addItem(Product item);
}
