package com.ecommerce.ptcommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ptcommerce.model.UserRegistration;

@Repository
public interface UserInterface extends JpaRepository<UserRegistration, String>{
    Optional<UserRegistration> findByEmail(String email);
    

}
