package com.example.sahibindendev.repository;

import com.example.sahibindendev.model.AccessLogDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccessLogRepository extends JpaRepository<AccessLogDTO,Long> {
    Optional<List<AccessLogDTO>> findAllByUsersId(Long userId);
}
