//package com.sparta.spa_api.repository;
//
//import com.sparta.spa_api.entities.Spartan;
//import com.sparta.spa_api.entities.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//@RepositoryRestResource(exported = false)
//
//public interface StudentRepository extends JpaRepository<Student,Integer> {
//    boolean hasGraduated(boolean hasGraduated);
//
//    // In StudentRepository
//    Optional<Student> findByEmail(String email);
//
//    Optional<Object> findBySpartan(Spartan spartan);
//}

package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Spartan;
import com.sparta.spa_api.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findBySpartanEmail(String email);

    Optional<Student> findBySpartan(Spartan spartan);
}

