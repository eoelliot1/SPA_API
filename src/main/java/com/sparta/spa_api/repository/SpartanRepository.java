package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Spartan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpartanRepository extends JpaRepository<Spartan, Integer> {
    @Query("SELECT s FROM Spartan s WHERE s.username = :username")
    Spartan findByUsername(@Param("username") String username);

    List<Spartan> findByRole(String role);

}
