package com.devmare.woolly_wonders.data.repository;

import com.devmare.woolly_wonders.data.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Optional<Farmer> findByEmail(String email);
}
