/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.controller;

import com.danielvargas.users.entity.User;
import com.danielvargas.users.entity.UserBuilder;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Clase controlador dummy que solo sirve para probar la conexión con la API durante pruebas.
 *
 * @author Daniel dvago1988@gmail.com.
 */
@RestController
public class TestConnectionController {

  /**
   * Método que retorna un usuario dummy probar que los datos lleguen correctamente.
   *
   * @return Usuario dummy para probar que los datos lleguen correctamente.
   */
  @GetMapping(value = "/ciclobosque/probarlaconexion")
  public ResponseEntity<User> getDummyUser() {
    User mockedUser = new UserBuilder(1L)
        .withName("Daniel")
        .withUsername("test")
        .withPassword("12345678")
        .withCity(1)
        .withEmail("test@test.com")
        .withPhoneNumber("3622220")
        .withId("123456789")
        .withBirthday(new Date())
        .build();
    return new ResponseEntity<>(mockedUser, HttpStatus.OK);
  }
}
