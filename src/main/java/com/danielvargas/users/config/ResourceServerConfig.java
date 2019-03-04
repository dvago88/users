/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * Class used for the configurations related with the authorization process. It recives an access token and then return the proper resource.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    //-- define URL patterns to enable OAuth2 security
    http
        .authorizeRequests()
        .antMatchers("/users/createUser").permitAll()
        .antMatchers("//users/probarlaconexion").permitAll()
        .anyRequest().authenticated();
  }
}
