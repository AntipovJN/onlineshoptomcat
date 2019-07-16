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
}
