/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.service;

import com.danielvargas.users.entity.Role;


/**
 * Interface used for the bussines logic of the roles.
 *
 * @author Daniel dvago1988@gmail.com.
 */
public interface RoleService {
  /**
   * Find a role by its uuid.
   *
   * @param uuid unique identifier of the role.
   * @return The role corresponding to the uuid or null if not found.
   */
  Role findByUuid(Integer uuid);
}
