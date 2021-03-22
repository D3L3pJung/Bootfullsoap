package com.service.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.service.dtos.StudentDto;
import com.service.entities.StudentEntity;
import com.service.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentrepository;

	public void createStudent(StudentDto dto) {
		StudentEntity entity = new StudentEntity();

		entity.setFirstName(dto.getName().substring(0, dto.getName().indexOf(" ")));
		entity.setLastName(dto.getName().substring(dto.getName().indexOf(" ")));
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setCreatedAt(new Date());
		entity.setUpdatedAt(new Date());
		entity.setUuid(UUID.randomUUID().toString());

		studentrepository.save(entity);

	}

	public StudentDto getStudent(int id) {
		Optional<StudentEntity> student = studentrepository.findById(id);
		StudentDto dto = null;
		if (student.isPresent()) {
			dto = new StudentDto();
			String firstName = StringUtils.isEmpty(student.get().getFirstName()) ? "" : student.get().getFirstName();
			String lastName = StringUtils.isEmpty(student.get().getLastName()) ? "" : "" + student.get().getLastName();
			dto.setId(student.get().getId());
			dto.setName(firstName + " " + lastName);
			dto.setEmail(student.get().getEmail());
			dto.setPhone(student.get().getPhone());

		}
		return dto;

	}

	public List<StudentDto> getAllStudent() {
		Iterable<StudentEntity> entity = studentrepository.findAll();
		List<StudentDto> studentdto = new ArrayList<StudentDto>();

		for (StudentEntity student : entity) {
			StudentDto dto = new StudentDto();
			dto.setId(student.getId());
			dto.setName(student.getFirstName() + " " + student.getLastName());
			dto.setEmail(student.getEmail());
			dto.setPhone(student.getPhone());

			studentdto.add(dto);

		}
		return studentdto;

	}

	public void updateStudent(int id, StudentDto dto) {
		Optional<StudentEntity> entity = studentrepository.findById(id);

		if (entity.isPresent()) {
			StudentEntity studententity = entity.get();
			studententity.setFirstName(dto.getName().substring(0, dto.getName().indexOf(" ")));
			studententity.setLastName(dto.getName().substring(dto.getName().indexOf(" ")));
			studententity.setEmail(dto.getEmail());
			studententity.setPhone(dto.getPhone());
			studententity.setUpdatedAt(new Date());
			studententity.setUuid(UUID.randomUUID().toString());

			studentrepository.save(studententity);

		}

	}

	public StudentDto getStudentByEmail(String email) {
		StudentEntity entity = studentrepository.findByEmail(email);
		StudentDto dto = new StudentDto();
		dto.setName(entity.getFirstName() + " " + entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());

		return dto;
	}

}
