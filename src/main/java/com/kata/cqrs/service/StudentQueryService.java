package com.kata.cqrs.service;

import com.kata.cqrs.entity.AddressEntity;
import com.kata.cqrs.entity.StudentEntity;
import com.kata.cqrs.exception.StudentDoesNotExists;
import com.kata.cqrs.model.Address;
import com.kata.cqrs.model.Student;
import com.kata.cqrs.respository.StudentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentQueryService {

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getStudents() {
    List<StudentEntity> students = studentRepository.findAll();
    return students.stream().map(this::convertStudentEntityToStudent).collect(
        Collectors.toList());
  }

  public Student getStudent(final Long registrationId) throws StudentDoesNotExists {
    Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(registrationId);
    if (optionalStudentEntity.isPresent()) {
      StudentEntity studentEntity = optionalStudentEntity.get();
      return convertStudentEntityToStudent(studentEntity);
    } else {
      throw new StudentDoesNotExists("Student record does not exists in the system");
    }
  }

  private Student convertStudentEntityToStudent(StudentEntity student) {
    return Student.builder().name(student.getName()).email(student.getEmail())
        .registrationNumber(student.getId()).section(student.getSection())
        .yearOfRegistration(student.getYearOfRegistration())
        .address(convertAddressEntityToAddress(student.getAddress())).build();
  }

  private Address convertAddressEntityToAddress(AddressEntity addressEntity) {
    return Objects.nonNull(addressEntity) ? Address.builder()
        .addressLine1(addressEntity.getAddressLine1())
        .state(addressEntity.getState()).street(addressEntity.getStreet()).build() : null;
  }
}
