package com.movingapp.service;

import com.movingapp.dao.UserRepo;
import com.movingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserService {

    private UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User findById(long id) {
        Optional<User> user =  userRepository.findById(id);
        return user.get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}