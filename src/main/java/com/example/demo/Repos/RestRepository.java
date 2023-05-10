package com.example.demo.Repos;
import java.util.List;

import com.example.demo.Entity.RestMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // для sql запросов

// p - это параметр

public interface RestRepository extends JpaRepository<RestMenu, Long> {
    @Query("SELECT p FROM RestMenu p where  CONCAT(p.bludo,'', p.ingredients,'',p.price,'', p.recipe,'',p.timeMake) LIKE %?1% ")
    List<RestMenu> search(String keyword);

    @Query("SELECT p FROM RestMenu p order by p.ingredients ")
    List<RestMenu> sort();



}
