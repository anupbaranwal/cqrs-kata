package com.kata.cqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryDto {

  private Long registrationNumber;
  private String name;
  private String email;
  private String yearOfRegistration;
  private String section;
  private Address address;
}
