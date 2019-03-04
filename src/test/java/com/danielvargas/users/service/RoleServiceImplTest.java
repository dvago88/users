/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.repository.RoleDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Class use to test the RoleServiceImpl class.
 *
 * @author Daniel dvago1988@gmail.com.
 */
public class RoleServiceImplTest {
  /**
   * With the annotation, this is a RoleDao Mock. It's used to validate the calls from the service to the repository.
   */
  @Mock
  RoleDao roleDao;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findByUuidCallsTheRepository() {
    Role role = new Role();
    role.setUuid(1);
    role.setName("USER");
    role.setDescpription("Application user");
    Mockito.when(roleDao.findByUuid(1)).thenReturn(role);
    RoleServiceImpl roleService = new RoleServiceImpl(roleDao);
    roleService.findByUuid(1);
    verify(roleDao).findByUuid(1);
    verify(roleDao, never()).findByUuid(2);
  }
}