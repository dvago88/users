/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.danielvargas.users.entity.User;
import com.danielvargas.users.entity.UserBuilder;
import com.danielvargas.users.repository.UserDao;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Class use to test the UserServiceImpl class.
 *
 * @author Daniel dvago1988@gmail.com.
 */
public class UserServiceImplTest {

  /**
   * With the annotation, this is a userDao Mock. It's used to validate the calls from the service to the repository.
   */
  @Mock
  UserDao userDao;

  /**
   * With the annotation, this is a roleService Mock. It's used to validate the calls from the service to the roleService.
   */
  @Mock
  RoleService roleService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void saveCreatesNewUser() {
    User mockedUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("lapapitasaladita")
        .withPassword("12345678")
        .withCity(1)
        .withEmail("dvago@hotmail.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .build();
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    when(userDao.save(any(User.class))).thenReturn(mockedUser);
    when(userDao.findUserById(any(String.class))).thenReturn(null);
    userServiceImpl.saveAsUser(mockedUser);
    verify(userDao).save(mockedUser);
  }

  @Test
  public void saveReturnsOldUserIfTheUserExistsAlready() {
    User mockedUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("lapapitasaladita")
        .withPassword("12345678")
        .withCity(1)
        .withEmail("dvago@hotmail.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .build();
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    when(userDao.save(any(User.class))).thenReturn(mockedUser);
    when(userDao.findUserById(any(String.class))).thenReturn(mockedUser);
    userServiceImpl.saveAsUser(mockedUser);
    verify(userDao, never()).save(mockedUser);
  }

  @Test
  public void updateOldUserSuccesfully() {
    User mockedUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("lapapitasaladita")
        .withPassword("12345678")
        .withCity(1)
        .withEmail("dvago@hotmail.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .build();
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    when(userDao.save(any(User.class))).thenReturn(mockedUser);
    when(userDao.findUserByUuid(any(Long.class))).thenReturn(mockedUser);
    userServiceImpl.update(mockedUser);
    verify(userDao).save(mockedUser);
  }

  @Test(expected = RuntimeException.class)
  public void ifUserDoesNotExistsThrowRunException() {
    User mockedUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("lapapitasaladita")
        .withPassword("12345678")
        .withCity(1)
        .withEmail("dvago@hotmail.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .build();
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    when(userDao.save(any(User.class))).thenReturn(mockedUser);
    when(userDao.findUserByUuid(any(Long.class))).thenReturn(null);
    userServiceImpl.update(mockedUser);
    verify(userDao, never()).save(mockedUser);
  }

  @Test
  public void loadByUserNameLoadsAUserIfExists() {
    when(userDao.findUserByUsername("")).thenReturn(new User());
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    userServiceImpl.loadUserByUsername("");
    verify(userDao).findUserByUsername("");
  }

  @Test(expected = RuntimeException.class)
  public void loadByUserNameThrowsExceptionIfUserDoesNotExists() {
    when(userDao.findUserByUsername("")).thenReturn(null);
    UserServiceImpl userServiceImpl = new UserServiceImpl(userDao, roleService);
    userServiceImpl.loadUserByUsername("");
  }
}