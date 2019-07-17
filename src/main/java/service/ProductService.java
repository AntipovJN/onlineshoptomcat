package service;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void add(String name, String description, double price);

    Product getById(Long id);

    void updateProduct(Long id, String name, String description, double price);

    void  removeProduct(Long id);
}
