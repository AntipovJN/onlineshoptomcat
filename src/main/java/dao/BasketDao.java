package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BasketDao {

    void addBasket(User user);

    Optional<Basket> getBasket(User user);

    void addProduct(User user, Product product);

    void removeProducts(User user);

}
