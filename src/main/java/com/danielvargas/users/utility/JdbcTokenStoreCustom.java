/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.utility;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


/**
 * Class used as a wrapper of the JdbcTokenStore, so It can be overridden. This is a custom implementation of token services that stores tokens in a database.
 *
 * @author Daniel dvago1988@gmail.com.
 */
public class JdbcTokenStoreCustom extends JdbcTokenStore {

  /**
   * Field use to log exceptions.
   */
  private static final Log LOG = LogFactory.getLog(JdbcTokenStore.class);

  /**
   * Constructor. Is only use to call it's parent contructor.
   *
   * @param dataSource factory for connections to the physical data source. Basically the connection to the database.
   */
  public JdbcTokenStoreCustom(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String tokenValue) {
    OAuth2AccessToken accessToken = null;
    try {
      accessToken = new DefaultOAuth2AccessToken(tokenValue);
    } catch (EmptyResultDataAccessException e) {
      if (LOG.isInfoEnabled()) {
        LOG.info("Failed to find access token for token " + tokenValue);
      }
    } catch (IllegalArgumentException e) {
      LOG.warn("Failed to deserialize access token for " + tokenValue, e);
      removeAccessToken(tokenValue);
    }
    return accessToken;
  }
}
