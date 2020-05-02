package com.kata.cqrs.resource;

import com.kata.cqrs.exception.StudentDoesNotExists;
import com.kata.cqrs.model.StudentAddressUpdateCommandDto;
import com.kata.cqrs.model.StudentRegistrationCommandDto;
import com.kata.cqrs.service.StudentCommandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class StudentCommandController {

  private StudentCommandService studentCommandService;

  @Autowired
  public StudentCommandController(StudentCommandService studentCommandService) {
    this.studentCommandService = studentCommandService;
  }

  @PostMapping("/registration")
  @ApiOperation(value = "Accepts the registration of a new student")
  public ResponseEntity registerStudent(
      @RequestBody StudentRegistrationCommandDto studentRegistrationCommandDto) {
    studentCommandService.register(studentRegistrationCommandDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/addresses")
  @ApiOperation(value = "Modifies the address of a new student")
  public ResponseEntity updateAddress(
      @RequestBody StudentAddressUpdateCommandDto studentAddressUpdateCommandDto) {
    try {
      studentCommandService.updateAddress(studentAddressUpdateCommandDto);
    } catch (StudentDoesNotExists studentDoesNotExists) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentDoesNotExists.getMessage());
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
