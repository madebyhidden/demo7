package com.example.demo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired; // для связей зависимостей из всех классов
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service; // анотация для логики
@Service
@RequiredArgsConstructor
public class GruzService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private GruzRepository repo;

// поиск и фильтр в системе
    public List<Gruz> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);

        }
        return repo.findAll(Sort.by("privozdata").descending());
    }
    public void save(Gruz gruz) {
        repo.save(gruz);
    }
    public Gruz get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public List<Gruz> listordered() {
        return repo.findAll(Sort.by("privozdata").descending());
    }
    public List<Gruz> listofall() {return repo.findAll();}

    public void addUser(User user) {

        user.setPassword(user.getPassword());
        repository.save(user);

    }

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public void addRoleAdmin(Integer id) {
        User user = repository.findById(id).get();
        user.getRoles().clear();
        user.getRoles().add(Role.admin);
        repository.save(user);

    }

    @Transactional
    public void addRoleUser(Integer id) {
        User user = repository.findById(id).get();
        user.getRoles().clear();
        user.getRoles().add(Role.USER);
        repository.save(user);

    }
}
