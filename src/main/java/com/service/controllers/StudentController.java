package com.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.dtos.StudentDto;
import com.service.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentservice;

	@GetMapping(value = "/{id}")
	public StudentDto showStudent(@PathVariable int id) {
		return studentservice.getStudent(id);

	}

	@PostMapping
	public void createStudent(@RequestBody StudentDto dto) {
		studentservice.createStudent(dto);
	}

	@PutMapping(value = "/update/{id}")
	public void updateById(@PathVariable int id, @RequestBody StudentDto dto) {
		studentservice.updateStudent(id, dto);

	}

	@GetMapping(value = "/allstudent")
	public List<StudentDto> displayStudent() {
		return studentservice.getAllStudent();
	}

	@GetMapping(value = "/email/{email}")
	public StudentDto showStudentByEmail(@PathVariable String email) {
		return studentservice.getStudentByEmail(email);

	}
}
