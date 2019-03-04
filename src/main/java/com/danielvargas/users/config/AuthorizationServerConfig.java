/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * Class used for the configurations related with the authorization process. It is resposible for recive the authorization grant and return an access token.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  /**
   * Field use to store the information of how is the application managing the authentication.
   */
  private final AuthenticationManager authenticationManager;

  /**
   * Field use to store the information of how is the application encoding the passwords.
   */
  private final PasswordEncoder passwordEncoder;

  /**
   * Field use to store the information of how is the application storing the tokens for the authentication.
   */
  private final TokenStore tokenStore;

  /**
   * Constructor use to autowired beans.
   *
   * @param authenticationManager stores the information of how is the application managing the authentication.
   * @param passwordEncoder stores the information of how is the application encoding the passwords.
   * @param tokenStore stores the information of how is the application storing the tokens for the authentication.
   */
  @Autowired
  public AuthorizationServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenStore tokenStore) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.tokenStore = tokenStore;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //This can also be hasRole("RoleToCheck").
    security.checkTokenAccess("isAuthenticated()"); //isAuthenticated() returns true if the user is not anonymous.
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    final int validityTokenTime = 5000;
    clients
        .inMemory()
        .withClient("my-trusted-client")
        .authorizedGrantTypes("client_credentials", "password", "refresh_token")
        .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_USER")
        .scopes("read", "write", "trust")
        .resourceIds("oauth2-resource")
        .accessTokenValiditySeconds(validityTokenTime)
        .secret(passwordEncoder.encode("secret"));
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
    endpoints.tokenStore(tokenStore);
  }
}
