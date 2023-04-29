package com.example.demo;
// для запуска всей программы
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AutoManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AutoManagerApplication.class, args);
    }

}