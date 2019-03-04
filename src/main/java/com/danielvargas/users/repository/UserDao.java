/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.repository;

import com.danielvargas.users.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface use to connect with the user table in the data base.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

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
   * @return The user corresponding to the uuid or null if not found.
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
