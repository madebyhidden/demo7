package com.example.demo;

import jakarta.persistence.Entity; // класс является сущностью и относится к орм jpa
import jakarta.persistence.GeneratedValue; // указывает для автозапомлениния айдишника
import jakarta.persistence.GenerationType; // тип данных перечисления
import jakarta.persistence.Id; // первичный ключ

@Entity
public class Gruz {
    private Long id;
    private String name;
    private String namesod;
    private String vivozgorod;
    private String vivozdata;
    private String privozgorod;
    private String privozdata;

    public Gruz() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamesod() {
        return namesod;
    }

    public void setNamesod(String namesod) {
        this.namesod = namesod;
    }

    public String getVivozgorod() {
        return vivozgorod;
    }

    public void setVivozgorod(String vivozgorod) {
        this.vivozgorod = vivozgorod;
    }

    public String getVivozdata() {
        return vivozdata;
    }

    public void setVivozdata(String vivozdata) {
        this.vivozdata = vivozdata;
    }

    public String getPrivozgorod() {
        return privozgorod;
    }

    public void setPrivozgorod(String privozgorod) {
        this.privozgorod = privozgorod;
    }

    public String getPrivozdata() {
        return privozdata;
    }

    public void setPrivozdata(String privozdata) {
        this.privozdata = privozdata;
    }
}


