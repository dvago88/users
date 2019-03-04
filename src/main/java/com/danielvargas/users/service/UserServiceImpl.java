/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import static com.danielvargas.users.repository.UserUtilities.merge;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.entity.User;
import com.danielvargas.users.repository.UserDao;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Class use for the bussines logic of the users.
 *
 * @author Daniel dvago1988@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * Field used to connect with the user table in the data base.
   */
  private final UserDao userDao;

  /**
   * Field used to connect the roleServiceImpl.
   */
  private final RoleService roleService;

  /**
   * Constructor use to autowired beans.
   *
   * @param userDao use to connect with the user table in the data base.
   * @param roleService used to connecto with bussines class of role.
   */
  @Autowired
  public UserServiceImpl(UserDao userDao, RoleService roleService) {
    this.userDao = userDao;
    this.roleService = roleService;
  }

  @Override
  public User saveAsUser(User user) {
    User oldUser = findUserById(user.getId());
    if (oldUser != null) {
      return oldUser;
    }
    Set<Role> roles = new HashSet<>();
    Role role = roleService.findByUuid(1);
    roles.add(role);
    user.setRoles(roles);
    return userDao.save(user);
  }

  @Override
  public User findUserById(String id) {
    return userDao.findUserById(id);
  }

  @Override
  public User update(User user) {
    return userDao.save(merge(user, findUserByUuid(user.getUuid())));
  }

  @Override
  public User findUserByUuid(Long uuid) {
    User user = userDao.findUserByUuid(uuid);
    if (user == null) {
      throw new UsernameNotFoundException("The User doesn't exists");
    }
    return user;
  }

  @Override
  public User findUserByUsername(String username) {
    return userDao.findUserByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }
}
