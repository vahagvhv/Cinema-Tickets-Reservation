package com.example.demo.service;

import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

     Optional<UserDTO> addNewUser(UserCreateDTO userCreateDTO);

     Optional<UserDTO> updateExistingUser(UserDTO user);

     Optional<UserDTO> getUserById(Integer id);

     List<UserDTO> getUsers();

     boolean deleteUserById(Integer id);
}
