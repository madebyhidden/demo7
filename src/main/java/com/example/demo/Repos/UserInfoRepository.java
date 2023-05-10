package com.example.demo.Repos;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);

}
