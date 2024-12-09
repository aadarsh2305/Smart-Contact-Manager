package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {
    // Save the user
    User save(User user);
    // Get user by Id
    Optional<User> getById(String Id);
    // Update user
    Optional<User> updateUser(User user);
    // Delete user
    void DeleteUser(String Id);
    // Check user exists or not 
    boolean isUserExist(String userId);
    // Check by email that whether the user exusts or not
    boolean isUserExistByEmail(String email);
    // Get all users
    List<User> getAllUsers();

    // Getting user by email
    User getUserByEmail(String email);
}
