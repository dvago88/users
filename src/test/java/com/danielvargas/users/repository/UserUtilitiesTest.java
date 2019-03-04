/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.entity.User;
import com.danielvargas.users.entity.UserBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;


public class UserUtilitiesTest {

  /**
   * Field for a dummy user used to simulate the user found in the database.
   */
  private User oldUser;

  @Before
  public void initializeOldUser() {
    Role role = new Role();
    role.setName("USER");
    role.setUuid(1);
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    oldUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("test")
        .withPassword("test")
        .withCity(1)
        .withEmail("test@test.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .withRoles(roles)
        .build();
  }

  @Test
  public void nullValuesDoNotUpdateTheUser() {
    User user = UserUtilities.merge(new UserBuilder(1L).build(), oldUser);
    assertEquals(user.getName(), oldUser.getName());
    assertEquals(user.getCity(), oldUser.getCity());
    assertEquals(user.getEmail(), oldUser.getEmail());
    assertEquals(user.getPhoneNumber(), oldUser.getPhoneNumber());
    assertEquals(user.getId(), oldUser.getId());
    assertEquals(user.getBirthday(), oldUser.getBirthday());
    assertEquals(user.getUsername(), oldUser.getUsername());
    assertEquals(user.getPassword(), oldUser.getPassword());
  }

  @Test
  public void rolesNeverGetUpdated() {
    Role role = new Role();
    role.setName("ADMIN");
    role.setUuid(1);
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    User user = UserUtilities.merge(new UserBuilder(1L).withRoles(roles).build(), oldUser);
    assertTrue(user.getRoles().add(role));
  }

  @Test
  public void fullUserUpdatesAllFieldsOfUser() {
    User newUser = new UserBuilder(1L)
        .withName("Pedro")
        .withUsername("pedrito")
        .withPassword("petaa")
        .withCity(1)
        .withEmail("pedro@pedrito.com")
        .withPhoneNumber("1010101010")
        .withId("111111111")
        .withBirthday(new Date())
        .build();
    User user = UserUtilities.merge(newUser, oldUser);
    assertEquals(newUser.getName(), user.getName());
    assertEquals(newUser.getCity(), user.getCity());
    assertEquals(newUser.getEmail(), user.getEmail());
    assertEquals(newUser.getPhoneNumber(), user.getPhoneNumber());
    assertEquals(newUser.getId(), user.getId());
    assertEquals(newUser.getBirthday(), user.getBirthday());
    assertEquals(newUser.getUsername(), user.getUsername());
    assertEquals(newUser.getPassword(), user.getPassword());
  }
}