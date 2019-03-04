/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * Entity use to map the role table with java.
 *
 * @author Daniel dvago188@gmail.com.
 */
@Entity
public class Role implements Serializable {

  /**
   * Serial uid for serialization of the class.
   */
  private static final long serialVersionUID = 4512638528498773524L;

  /**
   * Field for the unique identifier for the role. It's generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer uuid;

  /**
   * Fiel for the name of the role.
   */
  @Column(updatable = false)
  private String name;

  /**
   * Field for the description of the role.
   */
  @Column(updatable = false)
  private String descpription;

  /**
   * Field for the set of users associated with the users of the roles.
   */
  @ManyToMany(mappedBy = "roles")
  @JsonIgnore
  private Set<User> users = new HashSet<>();

  /**
   * Default constructor. This is needed for hibernate.
   */
  public Role() {
    super();
  }

  /**
   * Getter for the uuid field.
   *
   * @return The uuid field.
   */
  public Integer getUuid() {
    return uuid;
  }

  /**
   * Setter for the uuid field.
   *
   * @param uuid New uuid field.
   */
  public void setUuid(Integer uuid) {
    this.uuid = uuid;
  }

  /**
   * Getter for de name field.
   *
   * @return The name field.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for the name field.
   *
   * @param name New name field.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for the description field.
   *
   * @return The description field.
   */
  public String getDescpription() {
    return descpription;
  }

  /**
   * Setter for the description field.
   *
   * @param descpription New description field.
   */
  public void setDescpription(String descpription) {
    this.descpription = descpription;
  }

  /**
   * Getter for the field of set of users that have this role.
   *
   * @return The set of users.
   */
  public Set<User> getUsers() {
    return users;
  }

  /**
   * Setter for the set o users that have this role.
   *
   * @param users Set of users.
   */
  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
