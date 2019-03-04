/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.repository;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.entity.User;
import java.util.Set;
import org.springframework.stereotype.Component;


/**
 * Utility class for the user.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Component
public abstract class UserUtilities {

  /**
   * Utility classes, which are collections of static members, are not meant to be instantiated so this  private constructor hides the implicit public one.
   */
  private UserUtilities() {
    super();
  }

  /**
   * This method checks during the update if a field of the incoming information of the User is null so it can be ignore in the update.
   *
   * @param newUser The User with the new properties to save in the database.
   * @param oldUser The User with the old values stored in the database.
   * @return The newUser with its fields updated.
   */
  public static User merge(User newUser, User oldUser) {
    newUser.setName(newUser.getName() != null ? newUser.getName() : oldUser.getName());
    newUser.setCity(newUser.getCity() > 0 ? newUser.getCity() : oldUser.getCity());
    newUser.setEmail(newUser.getEmail() != null ? newUser.getEmail() : oldUser.getEmail());
    newUser.setPhoneNumber(newUser.getPhoneNumber() != null ? newUser.getPhoneNumber() : oldUser.getPhoneNumber());
    newUser.setId(newUser.getId() != null ? newUser.getId() : oldUser.getId());
    newUser.setBirthday(newUser.getBirthday() != null ? newUser.getBirthday() : oldUser.getBirthday());
    newUser.setUsername(newUser.getUsername() != null ? newUser.getUsername() : oldUser.getUsername());
    newUser.setPassword(newUser.getPassword() != null ? newUser.getPassword() : oldUser.getPassword());
    setSameRolesToUser(newUser, oldUser.getRoles());
    return newUser;
  }

  /**
   * Set old roles to the user to avoid accidental injection of roles during the update. Basically it doesn't allow to change roles during the update.
   *
   * @param user New User the roles are going to be setted.
   * @param roles Set of roles to set to the user.
   */
  private static void setSameRolesToUser(User user, Set<Role> roles) {
    user.setRoles(roles);
  }
}
