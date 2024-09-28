package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.User;
import com.codecool.samu.codecoolinterview.dbTarget.repository.UserRepository;
import com.codecool.samu.codecoolinterview.exception.NoSuchUserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long addUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new NoSuchUserException(name));
    }
}
