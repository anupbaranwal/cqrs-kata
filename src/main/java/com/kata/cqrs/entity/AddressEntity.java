package com.kata.cqrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ADDRESS")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  private Long id;

  @Column(name = "ADDRESS_1")
  private String addressLine1;

  @Column(name = "STREET")
  private String street;

  @Column(name = "STATE")
  private String state;

  @OneToOne(mappedBy = "address")
  private StudentEntity student;
}
