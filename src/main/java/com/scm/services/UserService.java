package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {

    /**
     * Save a new user to the database.
     * 
     * @param user the user entity to save
     * @return the saved user entity
     */
    User saveUser(User user);

    /**
     * Retrieve a user by their ID.
     * 
     * @param id the unique ID of the user
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<User> getUserById(String id);

    /**
     * Update an existing user.
     * 
     * @param user the user entity with updated information
     * @return an Optional containing the updated user if successful, or empty if not
     */
    Optional<User> updateUser(User user);

    /**
     * Delete a user by their ID.
     * 
     * @param id the unique ID of the user to delete
     */
    void deleteUser(String id);

    /**
     * Check if a user exists by their ID.
     * 
     * @param userId the unique ID of the user
     * @return true if the user exists, false otherwise
     */
    boolean isUserExist(String userId);

    /**
     * Check if a user exists by their email.
     * 
     * @param email the email of the user
     * @return true if the user exists, false otherwise
     */
    boolean isUserExistByEmail(String email);

    /**
     * Retrieve all users from the database.
     * 
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Retrieve a user by their email.
     * 
     * @param email the email of the user
     * @return the user entity if found, or null if not
     */
    User getUserByEmail(String email);

    // Add more methods here related to user service logic as needed

}
