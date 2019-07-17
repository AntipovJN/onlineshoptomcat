package dao.impl;

import dao.UserDao;
import model.User;
import service.Database;

import java.util.List;

public class UserDaoImpl implements UserDao {

    public void addUser(User user) {
        Database.USERS.add(user);
    }

    public List<User> getAll() {
        return Database.USERS;
    }

    @Override
    public User getByEmail(String email) {
        for (User user : Database.USERS) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        for (User user : Database.USERS) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        Database.USERS.set(Database.USERS.indexOf(getById(user.getId())), user);
    }

    @Override
    public void removeUser(User user) {
        Database.USERS.remove(user);
    }
}
