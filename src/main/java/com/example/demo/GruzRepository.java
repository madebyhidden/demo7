package com.example.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // для sql запросов

// p - это параметр

public interface GruzRepository extends JpaRepository<Gruz, Long> {
    @Query("SELECT p FROM Gruz p where  CONCAT(p.name,'', p.namesod,'',p.vivozgorod,'', p.vivozdata,'',p.privozgorod,'',p.privozdata) LIKE %?1% ")
    List<Gruz> search(String keyword);

    @Query("SELECT p FROM Gruz p order by p.privozdata ")
    List<Gruz> sort();



}
