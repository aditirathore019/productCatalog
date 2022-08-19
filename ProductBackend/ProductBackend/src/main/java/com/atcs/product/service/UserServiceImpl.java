package com.atcs.product.service;

import com.atcs.product.model.User;
import com.atcs.product.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public void addUser(User user) {
        userRepo.save(user);
    }
}
