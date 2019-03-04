/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Entity use to map the usuarios table with the project.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Entity(name = "usuario") //Postgres pone problema si se llama 'User'
public class User implements UserDetails {

  /**
   * Serial uid for serialization of the class.
   */
  private static final long serialVersionUID = -5291770225545985230L;

  /**
   * Field for the unique identifier for the user. It's generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uuid;

  /**
   * Field use to map the column name with the name of the user.
   */
  @NotNull
  @Size(min = 3, max = 50)
  @Pattern(regexp = "([a-zA-ZáéíóúÁÉÍÓÚüÜ ]+)\\w")
  private String name;

  /**
   * Field use to map the column city with the city of the user.
   */
  @NotNull
  private int city;

  /**
   * Field use to map the column email with the email of the user.
   */
  @NotNull
  @Email
  @Size(max = 80)
  private String email;

  /**
   * Field use to map the column phoneNumber with the phoneNumber of the user.
   */
  @NotNull
  @Pattern(regexp = "([0-9()])+\\w")
  @Size(min = 10, max = 15)
  private String phoneNumber;

  /**
   * Field use to map the column id with the id of the user. This is the legal identification of the user.
   */
  @NotNull
  @Column(unique = true)
  @Size(max = 20)
  private String id;

  /**
   * Field use to map the column birthday with the birthday of the user.
   */
  @NotNull
  private Date birthday;

  /**
   * Field for the user username.
   */
  @NotNull
  @Column(unique = true)
  @Size(min = 3, max = 30)
  @Pattern(regexp = "([a-zA-Z])+\\w")
  private String username;

  /**
   * Field use to map the column password with the password of the user. This field it's encrypted and it should never benn shown in the json response.
   */
  @NotNull
  private String password;

  /**
   * Field for the set of roles.
   */
  @Column(updatable = false)
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
  @JoinTable(joinColumns = @JoinColumn(name = "user_uuid"), inverseJoinColumns = @JoinColumn(name = "role_uuid"))
  private Set<Role> roles = new HashSet<>();

  /**
   * Default constructor has to be explicit for the use of hibernate.
   */
  public User() {
  }

  /**
   * Constructor to create new users. This uses a builder to avoit having multiples constructors with a lot of parameters.
   *
   * @param userBuilder Builder used to create new users.
   */
  public User(UserBuilder userBuilder) {
    this.uuid = userBuilder.getUuid();
    this.name = userBuilder.getName();
    this.city = userBuilder.getCity();
    this.email = userBuilder.getEmail();
    this.phoneNumber = userBuilder.getPhoneNumber();
    this.id = userBuilder.getId();
    this.birthday = userBuilder.getBirthday();
    this.username = userBuilder.getUsername();
    this.password = userBuilder.getPassword();
    this.roles = userBuilder.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Getter of the uuid.
   *
   * @return The uuid.
   */
  public Long getUuid() {
    return uuid;
  }

  /**
   * Setter of the uuid.
   *
   * @param uuid The new uuid.
   */
  public void setUuid(Long uuid) {
    this.uuid = uuid;
  }

  /**
   * Setter for the username field.
   *
   * @param username New username field.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Getter of the name field.
   *
   * @return The name field.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter of the name field.
   *
   * @param name The new name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter of the city field.
   *
   * @return The city field.
   */
  public int getCity() {
    return city;
  }

  /**
   * Setter for the city field.
   *
   * @param city The new city field.
   */
  public void setCity(int city) {
    this.city = city;
  }

  /**
   * Getter for the email field.
   *
   * @return The email field.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for the email field.
   *
   * @param email The email field.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter for the phoneNumber field.
   *
   * @return The phoneNumber field.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Setter for the phoneNumber field.
   *
   * @param phoneNumber new phoneNumber field.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Getter for the id field.
   *
   * @return The id field.
   */
  public String getId() {
    return id;
  }

  /**
   * Setter for the id field.
   *
   * @param id new id field.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Getter for the birthday field.
   *
   * @return The birthday field.
   */
  public Date getBirthday() {
    return birthday;
  }

  /**
   * Setter for the birthday field.
   *
   * @param birthday new birthday field.
   */
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  /**
   * Setter for the password field.
   *
   * @param password new password field.
   */
  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Getter for the list of roles field.
   *
   * @return The list of roles field.
   */
  @JsonProperty
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * Setter for the list of roles field.
   *
   * @param roles New list of roles.
   */
  @JsonIgnore
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * Adds a role to the user.
   *
   * @param role Is the role to add to the user.
   */
  public void addRole(Role role) {
    this.roles.add(role);
  }
}
