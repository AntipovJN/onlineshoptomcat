package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;
import utils.IdGenerator;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public void addUser(String email, String password, String passwordAgain, String role)
            throws IllegalArgumentException, LoginException {
        validateUserData(email, password, passwordAgain);
        if (!Objects.isNull(getByEmail(email))) {
            throw new LoginException("Try another login");
        }
        userDao.addUser(new User(IdGenerator.getUserID(), email, password, role));
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void updateUser(Long id, String newEmail, String newPassword, String newPasswordAgain)
            throws IllegalArgumentException, LoginException {
        validateUserData(newEmail, newPassword, newPasswordAgain);
        if (!Objects.isNull(userDao.getByEmail(newEmail)) && !userDao.getByEmail(newEmail).getId().equals(id)) {
            throw new LoginException("Use another email");
        }
        userDao.updateUser(new User(id, newEmail, newPassword, userDao.getById(id).getRole()));
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(userDao.getById(id));
    }

    private void validateUserData(String email, String password, String passwordAgain)
            throws IllegalArgumentException {
        if (Objects.isNull(email) || email.isEmpty()) {
            throw new IllegalArgumentException("You must use email for registration");
        }
        if (Objects.isNull(password) || Objects.isNull(passwordAgain)
                || password.isEmpty() || passwordAgain.isEmpty()) {
            throw new IllegalArgumentException("You must use password for registration");
        }
        if (!password.equals(passwordAgain)) {
            throw new IllegalArgumentException("Passwords not equals");
        }
    }
}
