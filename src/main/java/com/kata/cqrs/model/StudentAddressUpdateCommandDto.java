package com.kata.cqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAddressUpdateCommandDto {

  private Long registrationNumber;
  private String name;
  private Address address;
}
