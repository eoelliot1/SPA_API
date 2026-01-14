package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainersRepository extends JpaRepository<Trainers,Integer> {
}
