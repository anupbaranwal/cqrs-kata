package com.kata.cqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationCommandDto {

  private String name;
  private String email;
  private String yearOfRegistration;
  private String section;
}
