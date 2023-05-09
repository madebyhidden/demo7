package com.example.demo;

import jakarta.persistence.Entity; // класс является сущностью и относится к орм jpa
import jakarta.persistence.GeneratedValue; // указывает для автозапомлениния айдишника
import jakarta.persistence.GenerationType; // тип данных перечисления
import jakarta.persistence.Id; // первичный ключ

@Entity
public class RestMenu {
    private Long id;
    private String bludo;
    private String timemake;
    private String ingredients;
    private String recipe;
    private String price;
    private String bl_image;

    public RestMenu() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBludo() {
        return bludo;
    }

    public void setBludo(String bludo) {
        this.bludo = bludo;
    }

    public String getTimeMake() {
        return timemake;
    }

    public void setTimeMake(String timemake) {
        this.timemake = timemake;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBl_image() {
        return bl_image;
    }

    public void setBl_image(String bl_image) {
        this.bl_image = bl_image;
    }




}


