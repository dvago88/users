/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.repository;

import com.danielvargas.users.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface use to connect with the role table in the data base.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
  /**
   * Find a role by its uuid.
   *
   * @param uuid unique identifier of the role.
   * @return The role corresponding to the uuid or null if not found.
   */
  Role findByUuid(Integer uuid);
}
