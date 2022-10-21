package com.example.demo.service;

import com.example.demo.dao.TicketDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private Converter converter;

    @Override
    public Optional<UserDTO> addNewUser(final UserCreateDTO userCreateDTO) {

        final boolean isValidEmail = patternMatches(userCreateDTO.getEmail(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        if (!isValidEmail) {
            System.out.println("Email is not valid");
            return Optional.empty();
        }

        final boolean isInValidPassword = userCreateDTO.getPasswordHash().length() < 8 || userCreateDTO.getPasswordHash().length() > 15;
        if (isInValidPassword) {
            System.out.println("Password is not valid");
            return Optional.empty();
        }

        final User user = converter.userCreateDTOtoUser(userCreateDTO);
        final User newUser = userDAO.save(user);
        if (newUser.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(converter.userToUserDTO(newUser));
    }

    @Override
    public Optional<UserDTO> updateExistingUser(final UserDTO userDTO) {

        if (!userDAO.existsById(userDTO.getId())) {
            return Optional.empty();
        }
        final boolean isValidEmail = patternMatches(userDTO.getEmail(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        if (!isValidEmail) {
            System.out.println("Email is not valid");
            return Optional.empty();
        }

        final User user = converter.userDTOtoUser(userDTO);
        final User updatedUser = userDAO.save(user);
        final UserDTO response = converter.userToUserDTO(updatedUser);
        return Optional.of(response);
    }

    @Override
    public Optional<UserDTO> getUserById(final Integer id) {

        final Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        final UserDTO userDTO = converter.userToUserDTO(user.get());
        return Optional.of(userDTO);
    }

    @Override
    public List<UserDTO> getUsers() {
        final List<User> usersList = userDAO.findAll();
        if (usersList.isEmpty()) {
            return new ArrayList<>();
        }

        final List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : usersList) {
            userDTOList.add(converter.userToUserDTO(user));
        }
        return userDTOList;
    }

    @Override
    public boolean deleteUserById(final Integer userId) {
        if (ticketDAO.existsByUserId(userId)) {
            System.out.println("Unable to delete user as ticket with given user id exists");
            return false;
        }

        userDAO.deleteById(userId);
        return true;
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
