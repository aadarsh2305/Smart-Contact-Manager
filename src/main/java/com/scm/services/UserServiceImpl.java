package com.scm.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.exception.ResourceNotFoundException;
import com.scm.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    // Logger for logging
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        System.out.println("=======================================" + userId +"===========================================");
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getById(String Id) {
        return userRepo.findById(Id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User updateUser = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setAbout(user.getAbout());
        updateUser.setPhoneNumber(user.getPhoneNumber());
        updateUser.setProfilePic(user.getProfilePic());
        updateUser.setEnabled(user.isEnabled());
        updateUser.setEmailVerified(user.isEmailVerified());
        updateUser.setPhoneVerified(user.isPhoneVerified());
        updateUser.setProvider(user.getProvider());
        updateUser.setProviderUserId(user.getProviderUserId());

        // Save the user in database
        User save = userRepo.save(updateUser);
        return Optional.ofNullable(save);
    }

    @Override
    public void DeleteUser(String Id) {
        User user = userRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String userId) {
        User userCheck = userRepo.findById(userId).orElse(null);
        return userCheck!= null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User emailCheck = userRepo.findByEmail(email).orElse(null);
        return emailCheck != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

}
