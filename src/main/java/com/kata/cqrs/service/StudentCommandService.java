package com.kata.cqrs.service;

import com.kata.cqrs.entity.AddressEntity;
import com.kata.cqrs.entity.StudentEntity;
import com.kata.cqrs.exception.StudentDoesNotExists;
import com.kata.cqrs.model.Address;
import com.kata.cqrs.model.StudentAddressUpdateCommandDto;
import com.kata.cqrs.model.StudentRegistrationCommandDto;
import com.kata.cqrs.respository.AddressRepository;
import com.kata.cqrs.respository.StudentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private AddressRepository addressRepository;

  public void register(StudentRegistrationCommandDto studentRegistrationCommandDto) {
    studentRepository.save(StudentEntity.builder().name(studentRegistrationCommandDto.getName())
        .email(studentRegistrationCommandDto.getEmail())
        .section(studentRegistrationCommandDto.getSection())
        .yearOfRegistration(studentRegistrationCommandDto.getYearOfRegistration()).build());
  }

  public void updateAddress(StudentAddressUpdateCommandDto studentAddressUpdateCommandDto)
      throws StudentDoesNotExists {
    Optional<StudentEntity> optionalStudentEntity = studentRepository
        .findById(studentAddressUpdateCommandDto.getRegistrationNumber());
    if (optionalStudentEntity.isPresent()) {
      StudentEntity studentEntity = optionalStudentEntity.get();
      AddressEntity addressEntity = createAddress(studentAddressUpdateCommandDto.getAddress());
      addressEntity = addressRepository.save(addressEntity);
      studentEntity.setAddress(addressEntity);
      studentRepository.save(studentEntity);
    } else {
      throw new StudentDoesNotExists("Student record does not exists in the system");
    }
  }

  private AddressEntity createAddress(final Address address) {
    return AddressEntity.builder().addressLine1(address.getAddressLine1()).state(address.getState())
        .street(address.getStreet())
        .build();
  }
}
