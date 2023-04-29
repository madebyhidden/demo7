package com.example.demo;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired; // для связей зависимостей из всех классов
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service; // анотация для логики
@Service
@RequiredArgsConstructor
public class GruzService  {

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

}
