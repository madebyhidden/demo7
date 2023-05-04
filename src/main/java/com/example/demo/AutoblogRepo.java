package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoblogRepo extends JpaRepository<Autoblog, Integer> {
    Optional<Autoblog> findByName(String username);

}
