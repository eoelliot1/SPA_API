package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Spartan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface SpartanRepository extends JpaRepository<Spartan, Integer> {
    @Query("SELECT s FROM Spartan s WHERE s.username = :username")
    Spartan findByUsername(@Param("username") String username);

    List<Spartan> findByRole(String role);

}
