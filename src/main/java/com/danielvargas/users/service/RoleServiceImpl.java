/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class used for the bussines logic of the roles.
 *
 * @author Daniel dvago1988@gmail.com
 */
@Service
public class RoleServiceImpl implements RoleService {

  /**
   * Field use to connect with the repository.
   */
  private final RoleDao roleDao;

  /**
   * Constructor use to autowired beans.
   *
   * @param roleDao use to connect with the role table in the data base.
   */
  @Autowired
  public RoleServiceImpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  @Override
  public Role findByUuid(Integer uuid) {
    return roleDao.findByUuid(uuid);
  }
}
