package dao.impl.localStorage;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import service.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class BasketDaoImpl implements BasketDao {
    @Override
    public void addBasket(User user) {
        Database.BASKETS.add(new Basket(user, new ArrayList<>()));
    }

    @Override
    public Optional<Basket> getBasket(User user) {
        return Database.BASKETS.stream().filter(a -> a.getUser().equals(user)).findFirst();
    }

    @Override
    public void addProduct(User user, Product product) {
        getBasket(user).get().getProducts().add(product);
    }

    @Override
    public void removeProducts(User user) {
        getBasket(user).get().setProducts(Collections.emptyList());
    }
}
