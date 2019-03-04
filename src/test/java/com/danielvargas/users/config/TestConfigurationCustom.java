/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.config;

import com.danielvargas.users.entity.Role;
import com.danielvargas.users.entity.User;
import com.danielvargas.users.entity.UserBuilder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@org.springframework.boot.test.context.TestConfiguration
public class TestConfigurationCustom {

  @Bean
  @Primary
  public UserDetailsService userDetailsService() {
    Role userRole = new Role();
    userRole.setName("USER");
    userRole.setUuid(1);
    Role adminRole = new Role();
    adminRole.setName("admin");
    adminRole.setUuid(2);
    Set<Role> userRoles = new HashSet<>();
    Set<Role> adminRoles = new HashSet<>();
    userRoles.add(userRole);
    adminRoles.add(userRole);
    adminRoles.add(adminRole);
    User basicUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("test")
        .withPassword("test")
        .withCity(1)
        .withEmail("test@test.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .withRoles(userRoles)
        .build();

    User basicAdmin = new UserBuilder(2L)
        .withName("Daniel")
        .withUsername("admin")
        .withPassword("admin")
        .withCity(1)
        .withEmail("test@test.com")
        .withPhoneNumber("3622220")
        .withId("987654321")
        .withBirthday(new Date())
        .withRoles(adminRoles)
        .build();

    return new InMemoryUserDetailsManager(Arrays.asList(basicUser, basicAdmin));
  }
}
