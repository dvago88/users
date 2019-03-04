/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.danielvargas.users.config.TestConfigurationCustom;
import com.danielvargas.users.entity.User;
import com.danielvargas.users.entity.UserBuilder;
import com.danielvargas.users.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 * Tests for the UserController class.
 *
 * @author Daniel dvago1988@gmail.com.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfigurationCustom.class)
@WebMvcTest
public class UserControllerTest {

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
  public void createUserControllerCallsSaveMethodFromService() throws Exception {
    when(userServiceImpl.saveAsUser(new User())).thenReturn(new User());
    mockMvc.perform(
        post("/ciclobosque/createUser")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(new User()))
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .with(csrf()))
        .andExpect(status().isOk())
        .andReturn();
    ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
    verify(userServiceImpl).saveAsUser(argument.capture());
  }

  /**
   * Converts a java object to a JSON string.
   *
   * @param obj any java object.
   * @return JSON string representation of the object.
   */
  private String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      System.out.println(jsonContent);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @WithUserDetails("test")
  public void updateUser() throws Exception {
    when(userServiceImpl.update(new User())).thenReturn(new User());
    mockMvc.perform(
        MockMvcRequestBuilders.put("/ciclobosque/updateUser")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(new UserBuilder(1L).withUsername("test").build()))
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .with(csrf()))
        .andExpect(status().isAccepted())
        .andReturn();
    ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
    verify(userServiceImpl).update(argument.capture());
  }

  @Test
  @WithUserDetails("test")
  public void userCannotUpdateAnotherUser() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.put("/ciclobosque/updateUser")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(new UserBuilder(2L).withUsername("fake").build()))
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .with(csrf()))
        .andExpect(status().isForbidden())
        .andReturn();
    ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
    verify(userServiceImpl, never()).update(argument.capture());
  }

  @Test
  @WithUserDetails("admin")
  public void getUserByUuidWorks() throws Exception {
    when(userServiceImpl.findUserByUuid(1L)).thenReturn(new User());
    mockMvc.perform(
        MockMvcRequestBuilders.get("/ciclobosque/getUserByUuid")
            .param("uuid", "1")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andReturn();
    ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
    verify(userServiceImpl).findUserByUuid(argument.capture());
  }

  @Test
  @WithUserDetails("test")
  public void nonAdminUserCannotSeeOtherUsers() throws Exception {
    when(userServiceImpl.findUserByUuid(1L)).thenReturn(new User());
    mockMvc.perform(
        MockMvcRequestBuilders.get("/ciclobosque/getUserByUuid")
            .param("uuid", "1")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isForbidden())
        .andReturn();
    ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
    verify(userServiceImpl, never()).findUserByUuid(argument.capture());
  }

  @Test
  @WithUserDetails("test")
  public void getUserWorks() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/ciclobosque/getUser")
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andReturn();
    ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
    verify(userServiceImpl).findUserByUsername(argument.capture());
  }
}