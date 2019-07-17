package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import utils.IdGenerator;

public class UserDaoFactory {

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
            instance.addUser(new User(IdGenerator.getUserID(), "admin@a", "admin"));
        }
        return instance;
    }
}
