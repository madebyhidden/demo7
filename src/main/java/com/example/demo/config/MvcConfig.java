package com.example.demo.config;
// для сборки проекта
import org.springframework.context.annotation.Configuration; // объединение классов в один спринг бут приложениие
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // объединение классов в один спринг бут при
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}
