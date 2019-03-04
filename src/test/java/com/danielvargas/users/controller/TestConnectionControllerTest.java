/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.danielvargas.users.config.TestConfigurationCustom;
import com.danielvargas.users.service.UserServiceImpl;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Tests for the UserController class.
 *
 * @author Daniel dvago1988@gmail.com.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfigurationCustom.class)
@WebMvcTest
public class TestConnectionControllerTest {

  /**
   * This field is used to mock the spring MVC. It's importan to mock all the necesary beans.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * With the annotation, this is a UserServiceImpl Mock. It's used to validate the calls from the controller to the service.
   */
  @MockBean
  private UserServiceImpl userServiceImpl;

  /**
   * With the annotation, this is a dataSource Mock. It's used by the security context mock to perform the authentication.
   */
  @MockBean
  private DataSource dataSource;

  @Test
  @WithUserDetails("test")
  public void getDummyUser() throws Exception {
    mockMvc.perform(get("/users/probarlaconexion").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
  }
}