package org.example.ecommerceapi.Service;

import org.example.ecommerceapi.DAO.UserRepository;
import org.example.ecommerceapi.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> res = userRepository.findById(id);

        User user = null;

        if(res.isPresent()){
            user = res.get();
        }
        return user;
    }

    @Override
    public int saveUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
