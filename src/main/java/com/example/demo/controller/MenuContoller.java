package com.example.demo.controller;

import org.springframework.ui.Model;
import com.example.demo.RestMenu;
import com.example.demo.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MenuContoller {

    @Autowired
    private RestService service;
    @RequestMapping("/menu")
    public String menu(Model model, @Param("keyword") String keyword){
        List<RestMenu> listRestMenu = service.listAll(keyword);
        model.addAttribute("listRestMenu", listRestMenu);
        model.addAttribute("keyword", keyword);
        return "Restorun/menu";
    }
    @RequestMapping("/contacts")
    public String contacts(){
        return "Restorun/contacts";
    }


}
