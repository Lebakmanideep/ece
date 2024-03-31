package org.example.ecommerceapi.Service;

import org.example.ecommerceapi.Entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    int saveUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
