package com.example.sahibindendev.repository;

import com.example.sahibindendev.model.CategoryType;
import com.example.sahibindendev.model.Classified;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassifiedRepository extends JpaRepository<Classified,String> {


    Optional<List<Classified>> findAllByUsersId(String userId);

}
