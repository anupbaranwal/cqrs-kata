package com.kata.cqrs.resource;

import com.kata.cqrs.model.Student;
import com.kata.cqrs.model.StudentQueryDto;
import com.kata.cqrs.service.StudentQueryService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentQueryController {

  private StudentQueryService studentQueryService;

  @Autowired
  public StudentQueryController(StudentQueryService studentQueryService) {
    this.studentQueryService = studentQueryService;
  }

  @GetMapping
  @ApiOperation(value = "provides the list of all students in the university")
  public ResponseEntity<List<StudentQueryDto>> getStudents() {
    List<Student> students = studentQueryService.getStudents();
    return ResponseEntity.ok(students.stream().map(this::convertToStudentQueryDto).collect(
        Collectors.toList()));
  }

  @GetMapping(path = "/{id}")
  @ApiOperation(value = "provides the list of all students in the university")
  public ResponseEntity<StudentQueryDto> getStudent(@PathVariable Long id) {
    Student student = studentQueryService.getStudent(id);
    return ResponseEntity.ok(convertToStudentQueryDto(student));
  }

  private StudentQueryDto convertToStudentQueryDto(Student student) {
    return StudentQueryDto.builder().name(student.getName()).email(student.getEmail())
        .registrationNumber(student.getRegistrationNumber()).section(student.getSection())
        .yearOfRegistration(student.getYearOfRegistration()).address(student.getAddress()).build();
  }
}
