package com.example.demo.Services;
import java.util.List;

import com.example.demo.Entity.RestBlog;
import com.example.demo.Entity.RestMenu;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repos.RestBlogRepo;
import com.example.demo.Repos.RestRepository;
import com.example.demo.Repos.UserInfoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired; // для связей зависимостей из всех классов
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service; // анотация для логики
@Service
@RequiredArgsConstructor
public class RestService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private RestBlogRepo Autorepository;

    @Autowired
    private RestRepository repo;

// поиск и фильтр в системе
    public List<RestMenu> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);

        }
        return repo.findAll(Sort.by("ingredients").descending());
    }
    public void save(RestMenu restMenu) {
        repo.save(restMenu);
    }
    public void saveBlog(RestBlog restBlog) {
        Autorepository.save(restBlog);
    }
    public RestMenu get(Long id) {
        return repo.findById(id).get();
    }
    public RestBlog getblog(Integer id) {
        return Autorepository.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public List<RestMenu> listordered() {
        return repo.findAll(Sort.by("ingredients").descending());
    }
    public List<RestMenu> listofall() {return repo.findAll();}

    public void addUser(User user) {

        user.setPassword(user.getPassword());
        repository.save(user);

    }

    public List<User> listAllUsers() {
        return repository.findAll();
    }

    public List<RestBlog> listAllRestblog() {
        return Autorepository.findAll();
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    public void deleteBlog(Integer id) {
        Autorepository.deleteById(id);
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
