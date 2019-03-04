/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.entity;

import java.util.Date;
import java.util.List;


/**
 * Class for constructing errors messages to by send as JSON to the client.
 *
 * @author Daniel dvago1988@gmail.com
 */
public class ErrorEntity {

  /**
   * Field for saving the time of the errors.
   */
  private Date timestamp;

  /**
   * Field for the HTTP code of the errors.
   */
  private String status;

  /**
   * Field for the list of errors.
   */
  private List<String> errors;

  /**
   * Fiedl for the message associate with the errors.
   */
  private String message;

  /**
   * Route where the errors ocurred.
   */
  private String path;

  /**
   * Constructor.
   *
   * @param timestamp Date when the error ocurred.
   * @param status HTTP status code for the error.
   * @param errors List of errors.
   * @param message Appropiate error message for the client.
   * @param path URL where the error ocurred.
   */
  public ErrorEntity(Date timestamp, String status, List<String> errors, String message, String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.errors = errors;
    this.message = message;
    this.path = path;
  }

  /**
   * Getter for the timestamp field.
   *
   * @return timestamp field.
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Setter for the timestamp field.
   *
   * @param timestamp new timestamp.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Getter for the status field.
   *
   * @return the status field.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Setter for the status field.
   *
   * @param status new status field.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Getter for the list of errors field.
   *
   * @return Listo of errors field.
   */
  public List<String> getErrors() {
    return errors;
  }

  /**
   * Setter for the list of errors field.
   *
   * @param errors new list of errors field.
   */
  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  /**
   * Getter for the message field.
   *
   * @return message field.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Setter for the message field.
   *
   * @param message new message field-
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Getter for the path field.
   *
   * @return path field.
   */
  public String getPath() {
    return path;
  }

  /**
   * Setter for the path field.
   *
   * @param path new path field.
   */
  public void setPath(String path) {
    this.path = path;
  }
}
