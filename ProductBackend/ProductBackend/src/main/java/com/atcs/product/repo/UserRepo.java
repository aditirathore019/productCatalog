package com.atcs.product.repo;

import com.atcs.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    public User findByUsername(String username);
}
