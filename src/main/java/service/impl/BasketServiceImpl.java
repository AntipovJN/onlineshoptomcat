package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public Basket getBasket(User user) {
        checkBasketIsPresent(user);
        return basketDao.getBasket(user).get();
    }

    @Override
    public void addProduct(User user, Product product) {
        checkBasketIsPresent(user);
        basketDao.addProduct(user, product);
    }

    @Override
    public void removeProduct(User user) {
        basketDao.removeProducts(user);
    }

    private void checkBasketIsPresent(User user) {
        if (!basketDao.getBasket(user).isPresent()) {
            basketDao.addBasket(user);
        }
    }
}
