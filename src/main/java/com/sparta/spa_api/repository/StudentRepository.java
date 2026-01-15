package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
