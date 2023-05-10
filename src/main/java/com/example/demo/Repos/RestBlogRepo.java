package com.example.demo.Repos;

import com.example.demo.Entity.RestBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestBlogRepo extends JpaRepository<RestBlog, Integer> {
    Optional<RestBlog> findByName(String username);

}
