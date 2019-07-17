package service;

import model.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    void addUser(String email, String password, String passwordAgain, String role) throws LoginException;

    List<User> getAll();

    User getByEmail(String email);

    User getById(Long id);

    void updateUser(Long id, String email, String password, String passwordAgain) throws LoginException;

    void removeUser(Long id);
}
