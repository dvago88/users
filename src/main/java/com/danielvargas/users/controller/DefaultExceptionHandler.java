/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.controller;

import com.danielvargas.users.entity.ErrorEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Class use for controlling exceptions.
 *
 * @author Daniel dvago1988@gmail.com
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Controls the contrains violations and send the proper response message.
   *
   * @param exception ConstraintViolationException.
   * @param request HttpServletRequest sent by the client.
   * @return ResponseEntity with the proper 400 code, list of erros, message and path.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<ErrorEntity> constrainViolationThrown(ConstraintViolationException exception, HttpServletRequest request) {
    Iterator<ConstraintViolation<?>> errorIterator = exception.getConstraintViolations().iterator();
    ArrayList<String> listOfErrors = new ArrayList<>();
    String message = "Validation error, pleas verify your input";
    while (errorIterator.hasNext()) {
      ConstraintViolation<?> constraintViolation = errorIterator.next();
      listOfErrors.add("The field: " + constraintViolation.getPropertyPath() + " has this problem: " + constraintViolation.getMessage());
    }
    ErrorEntity errorEntity = new ErrorEntity(new Date(), "400", listOfErrors, message, request.getRequestURL().toString());
    return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param exception ForbiddenTargetException.
   * @param request HttpServletRequest sent by the client.
   * @return ResponseEntity with the proper 403 code, list of erros, message and path.
   */
  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<ErrorEntity> accessDeniedExceptionThrown(AccessDeniedException exception, HttpServletRequest request) {
    ErrorEntity errorEntity = new ErrorEntity(new Date(), "403", new ArrayList<>(), exception.getMessage(), request.getRequestURL().toString());
    return new ResponseEntity<>(errorEntity, HttpStatus.FORBIDDEN);
  }
}
