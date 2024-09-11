package com.example.buah.repository;

import com.example.buah.entity.Buah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BuahRepository extends JpaRepository<Buah, Long> {

    @Query("SELECT b FROM Buah b WHERE b.deletedAt IS NULL")
    List<Buah> findAllActiveBuah();

    @Query("SELECT b FROM Buah b WHERE b.id = :id AND b.deletedAt IS NULL")
    Optional<Buah> findByIdAndActive(Long id);
}


