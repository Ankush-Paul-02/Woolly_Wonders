package com.devmare.woolly_wonders.data.repository;

import com.devmare.woolly_wonders.data.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
}
