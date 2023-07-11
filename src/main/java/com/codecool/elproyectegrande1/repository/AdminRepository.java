package com.codecool.elproyectegrande1.repository;

import com.codecool.elproyectegrande1.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional<Admin> findByUserId(Long user_id);
//    Optional<Admin> findByNickname(String name);

    Boolean existsByNickname(String Nickname);

    Boolean existsByEmail(String email);
}
