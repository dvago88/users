/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import com.danielvargas.users.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Interface use for the bussines logic of the users.
 *
 * @author Daniel dvago1988@gmail.com.
 */
public interface UserService extends UserDetailsService {
  /**
   * Saves a new user to the database with the role "user".
   *
   * @param user user to saveAsUser into de the database.
   */
  User saveAsUser(User user);

  /**
   * Updates the user to the database.
   *
   * @param user user to saveAsUser into de the database.
   */
  User update(User user);

  /**
   * Find a user by its id.
   *
   * @param id identification of the user.
   * @return The user corresponding to the id or null if not found.
   */
  User findUserById(String id);

  /**
   * Find a user by its uuid.
   *
   * @param uuid unique identifier of the user.
   * @return The user corresponding to the id or null if not found.
   */
  User findUserByUuid(Long uuid);

  /**
   * Find a user by its username.
   *
   * @param username must be unique for every user.
   * @return The user corresponding to the username or null if not found.
   */
  User findUserByUsername(String username);
}
