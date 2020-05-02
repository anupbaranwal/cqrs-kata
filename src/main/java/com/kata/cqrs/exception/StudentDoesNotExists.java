package com.kata.cqrs.exception;

public class StudentDoesNotExists extends RuntimeException {

  private String errorMessage;

  public StudentDoesNotExists(String errorMessage) {
    super(errorMessage);
  }
}
