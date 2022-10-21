package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    public User save(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(final Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(final Integer id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(final Integer id){
        return userRepository.existsById(id);
    }
}
