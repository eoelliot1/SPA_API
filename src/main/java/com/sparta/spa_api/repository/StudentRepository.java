package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Spartan;
import com.sparta.spa_api.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findBySpartanEmail(String email);

    Optional<Student> findBySpartan(Spartan spartan);

    Optional<Student> findBySpartan_Email(String name);
}