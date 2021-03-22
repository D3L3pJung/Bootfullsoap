package com.service.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.entities.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	StudentEntity save(Optional<StudentEntity> entity);

	public StudentEntity findByEmail(String email);

}
