package com.example.demo.controller;

import com.example.demo.Role;
import com.example.demo.User;
import com.example.demo.RestService;
import com.example.demo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserInfoRepository repo;

    @Autowired
    private RestService service;

    @GetMapping("/registration")
    public String reg(){
        return "registration_form";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Map<String, Object> model){
        Optional<User> users = repo.findByName(user.getName());

        if (users.isPresent()){
            model.put("message", "User exists");
            return "registration_form";

        }
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(Role.USER));
        repo.save(user);
        return "redirect:auth/login";
    }

    @RequestMapping("/users")
    @PreAuthorize("hasAuthority('admin')")
    public String users(Model model){
        try {
            List<User> listUsers = service.listAllUsers();
            model.addAttribute("listUsers", listUsers);
            return "users";
        } catch (NoSuchElementException e) {
            return "error";
        }
    }

    @RequestMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/editroleuser/{id}")
    public String addRoleUser(@PathVariable(name = "id") Integer id) {
        service.addRoleUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/editroleadmin/{id}")
    public String addRoleAdmin(@PathVariable(name = "id") Integer id) {
        service.addRoleAdmin(id);
        return "redirect:/users";
    }
}
