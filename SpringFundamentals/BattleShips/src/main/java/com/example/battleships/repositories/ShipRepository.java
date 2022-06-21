package com.example.battleships.repositories;

import com.example.battleships.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findAllByUserId(Long user_id);

    List<Ship> findAllByUserIdIsNot(Long id);


    @Query("select s from Ship s ORDER BY s.name,s.health,s.power")
    List<Ship> findAllOrderByNameHealthPower();

}
