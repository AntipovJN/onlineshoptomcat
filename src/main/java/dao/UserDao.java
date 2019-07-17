package dao;

import model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getAll();

    User getByEmail(String email);

    User getById(Long id);

    void updateUser(User user);

    void removeUser(User user);
}
