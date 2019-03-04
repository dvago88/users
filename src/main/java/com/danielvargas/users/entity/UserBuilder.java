/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Class use to build new users, it helps to create users passing diferent parametes to de "constructor".
 *
 * @author Daniel dvago1988@gmail.com
 */
public class UserBuilder {

  /**
   * Field for the unique identifier for the user.
   */
  private Long uuid;

  /**
   * Field use to map the column name with the name of the user.
   */
  private String name;

  /**
   * Field use to map the column city with the city of the user.
   */
  private int city;

  /**
   * Field use to map the column email with the email of the user.
   */
  private String email;

  /**
   * Field use to map the column phoneNumber with the phoneNumber of the user.
   */
  private String phoneNumber;

  /**
   * Field use to map the column id with the id of the user. This is the legal identification of the user.
   */
  private String id;

  /**
   * Field use to map the column birthday with the birthday of the user.
   */
  private Date birthday;

  /**
   * Field for the user username.
   */
  private String username;

  /**
   * Field use to map the column password with the password of the user. This field it's encrypted and it should never benn shown in the json response.
   */
  private String password;

  /**
   * Field for the set of roles.
   */
  private Set<Role> roles = new HashSet<>();

  /**
   * Constructor.
   *
   * @param uuid Unique identifier for the user.
   */
  public UserBuilder(Long uuid) {
    this.uuid = uuid;
  }

  /**
   * Method used to build a user.
   *
   * @return Built user.
   */
  public User build() {
    return new User(this);
  }

  /**
   * Builder to add name to the User.
   *
   * @param mName name of the user;
   * @return UserBuilder.
   */
  public UserBuilder withName(String mName) {
    this.name = mName;
    return this;
  }

  /**
   * Builder to add city to the User.
   *
   * @param mCity city of the user.
   * @return UserBuilder.
   */
  public UserBuilder withCity(int mCity) {
    this.city = mCity;
    return this;
  }

  /**
   * Builder to add email to the User.
   *
   * @param mEmail email of the user.
   * @return UserBuilder.
   */
  public UserBuilder withEmail(String mEmail) {
    this.email = mEmail;
    return this;
  }

  /**
   * Builder to add phoneNumber to the User.
   *
   * @param mPhoneNumber phoneNumber of the user.
   * @return UserBuilder.
   */
  public UserBuilder withPhoneNumber(String mPhoneNumber) {
    this.phoneNumber = mPhoneNumber;
    return this;
  }

  /**
   * Builder to add id to the User.
   *
   * @param mId id of the user.
   * @return UserBuilder.
   */
  public UserBuilder withId(String mId) {
    this.id = mId;
    return this;
  }

  /**
   * Builder to add birthday to the User.
   *
   * @param mBirthday birthday of the user.
   * @return UserBuilder.
   */
  public UserBuilder withBirthday(Date mBirthday) {
    this.birthday = mBirthday;
    return this;
  }

  /**
   * Builder to add username to the User.
   *
   * @param mUsername username of the user.
   * @return UserBuilder.
   */
  public UserBuilder withUsername(String mUsername) {
    this.username = mUsername;
    return this;
  }

  /**
   * Builder to add password to the User.
   *
   * @param mPassword password of the user.
   * @return UserBuilder.
   */
  public UserBuilder withPassword(String mPassword) {
    this.password = mPassword;
    return this;
  }

  /**
   * Builder to add set of roles to the User.
   *
   * @param mRoles Set of roles of the user.
   * @return UserBuilder.
   */
  public UserBuilder withRoles(Set<Role> mRoles) {
    this.roles = mRoles;
    return this;
  }

  /**
   * Getter of the uuid.
   *
   * @return The uuid.
   */
  Long getUuid() {
    return uuid;
  }

  /**
   * Getter of the name field.
   *
   * @return The name field.
   */
  String getName() {
    return name;
  }

  /**
   * Getter of the username field.
   *
   * @return The username field.
   */
  String getUsername() {
    return username;
  }

  /**
   * Getter of the city field.
   *
   * @return The city field.
   */
  int getCity() {
    return city;
  }

  /**
   * Getter for the email field.
   *
   * @return The email field.
   */
  String getEmail() {
    return email;
  }

  /**
   * Getter for the phoneNumber field.
   *
   * @return The phoneNumber field.
   */
  String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Getter for the id field.
   *
   * @return The id field.
   */
  String getId() {
    return id;
  }

  /**
   * Getter for the birthday field.
   *
   * @return The birthday field.
   */
  Date getBirthday() {
    return birthday;
  }

  /**
   * Getter for the password field.
   *
   * @return The password field.
   */
  String getPassword() {
    return password;
  }

  /**
   * Getter for the list of roles field.
   *
   * @return The list of roles field.
   */
  Set<Role> getRoles() {
    return roles;
  }
}
