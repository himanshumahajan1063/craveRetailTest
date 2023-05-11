package com.example.craveRetail.repository;

import com.example.craveRetail.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
