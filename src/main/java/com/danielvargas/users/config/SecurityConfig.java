/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.config;

import com.danielvargas.users.service.UserService;
import com.danielvargas.users.service.UserServiceImpl;
import com.danielvargas.users.utility.JdbcTokenStoreCustom;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  /**
   * UsetService Field, use for connecting with the services.
   */
  private final UserService userService;

  /**
   * Field for a factory for connections to the physical data source that this object represents.
   */
  private final DataSource dataSource;

  /**
   * Constructor to autowired the fields with this class.
   *
   * @param userService used for connecting with the services.
   * @param dataSource A factory for connections to the physical data source that this object represents.
   */
  @Autowired
  public SecurityConfig(UserServiceImpl userService, DataSource dataSource) {
    this.userService = userService;
    this.dataSource = dataSource;
  }

  /**
   * Method to create a new password enryptor..
   *
   * @return a bcrypt password encoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  /**
   * Method to configure our userDetailsService in the project.
   *
   * @param auth use for user authentication.
   * @throws Exception if there's a problem with the authentication.
   */
  @Autowired
  public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  /**
   * Bean used for the token store.
   *
   * @return A JDBC token store.
   */
  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStoreCustom(dataSource);
  }

  @Override
  @Order(Ordered.HIGHEST_PRECEDENCE)
  protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/users/createUser").permitAll()
        .antMatchers("//users/probarlaconexion").permitAll()
        .anyRequest().authenticated();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
