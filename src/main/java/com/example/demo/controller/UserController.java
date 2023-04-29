package com.example.demo.controller;

import com.example.demo.Role;
import com.example.demo.User;
import com.example.demo.UserInfoRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserInfoRepository repo;

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
        System.out.println("Govno");
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(Role.USER));
        repo.save(user);
        return "redirect:/login";
    }
}
