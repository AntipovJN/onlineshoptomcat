package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import factory.UserDaoFactory;
import factory.UserServiceFactory;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
