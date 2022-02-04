package com.example.sahibindendev.repository;

import com.example.sahibindendev.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccessLogRepository extends JpaRepository<AccessLog,String> {
    Optional<List<AccessLog>> findAllByUsersId(String userId);
}
