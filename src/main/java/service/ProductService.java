package service;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void add(Product item);
}
