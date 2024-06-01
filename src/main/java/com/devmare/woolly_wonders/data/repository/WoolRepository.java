package com.devmare.woolly_wonders.data.repository;

import com.devmare.woolly_wonders.data.entity.Wool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WoolRepository extends JpaRepository<Wool, Long> {

    Optional<List<Wool>> findAllByWoolType(String woolType);
}
