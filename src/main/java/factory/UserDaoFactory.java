package factory;

import dao.UserDao;
import dao.impl.JDBC.UserDaoJDBC;

public class UserDaoFactory {

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }
}
