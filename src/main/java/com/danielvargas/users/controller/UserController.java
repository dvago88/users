/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.controller;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import com.danielvargas.users.entity.User;
import com.danielvargas.users.service.UserService;
import com.danielvargas.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlloler class use for manages users.
 *
 * @author Daniel Vargas dvago1988@gmail.com
 */
@RestController
public class UserController {

  /**
   * Field use to connect the controller with the bussines logic of the users.
   */
  private final UserService userService;

  /**
   * Constructor use to autowired beans.
   *
   * @param userService connects the controller with the bussines logic of the users.
   */
  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  /**
   * Manages the creation of new users. If the user ID already exists it returns that user, if not, it creates it.
   *
   * @param user User to check existence or to create.
   * @return The newly created user or the old user.
   */
  @PostMapping(value = "/ciclobosque/createUser",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.saveAsUser(user), HttpStatus.OK);
  }

  /**
   * Manages the update of users. If the user exists is uptaded, if not, sends 406 status code. It also checks if the user is authorized to perform the change.
   *
   * @param user User to check existence or to create.
   * @return The newly created user, the old user or forbidden excaption.
   */
  @PutMapping(value = "/ciclobosque/updateUser",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    if (((UserDetails) getContext().getAuthentication().getPrincipal()).getUsername().equals(user.getUsername())) {
      return new ResponseEntity<>(userService.update(user), HttpStatus.ACCEPTED);
    }
    throw new AccessDeniedException("The logged user is not authorized to change the information of another user");
  }

  /**
   * Get user by its uuid. Only admin role can use this function.
   *
   * @param uuid Unique identifier of the user.
   * @return User.
   */
  @PreAuthorize("hasAuthority('admin')")
  @GetMapping(value = "/ciclobosque/getUserByUuid",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> getUserByUuid(@RequestParam Long uuid) {
    return new ResponseEntity<>(userService.findUserByUuid(uuid), HttpStatus.OK);
  }

  /**
   * Get logged user.
   *
   * @return Logged User.
   */
  @GetMapping(value = "/ciclobosque/getUser",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> getUser() {
    UserDetails user = (UserDetails) getContext().getAuthentication().getPrincipal();
    return new ResponseEntity<>(userService.findUserByUsername(user.getUsername()), HttpStatus.OK);
  }
}
