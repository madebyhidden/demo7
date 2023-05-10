package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {


    @RequestMapping("exp/error")
    public String error(){
        return "Admin/error";
    }

}

