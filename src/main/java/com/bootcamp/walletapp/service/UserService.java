package com.bootcamp.walletapp.service;

import com.bootcamp.walletapp.exception.UserAlreadyExistsException;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(String username, String password) {
        User user = new User(username,password);
        if(userRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        return userRepository.save(user);
    }




//
//    public User findByUsername(String username) {
//        User returnedUser = userRepository.findByUsername(username);
//        System.out.println(returnedUser.toString());
//        return returnedUser;
//    }

}
