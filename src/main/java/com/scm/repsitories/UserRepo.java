package com.scm.repsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByUemail(String email);
    
    Optional<User> findByUemailAndUpassword(String email, String password);

    Optional<User> findByEmailToken(String id);

    // If you later add emailToken:
    // Optional<User> findByEmailToken(String id);
}
