package com.example.demo.controller;
// контроллеры
import java.util.List;

import com.example.demo.Gruz;
import com.example.demo.GruzService;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired; // внедряет зависимости
import org.springframework.data.repository.query.Param; // привязываем данные к sql запросу
import org.springframework.stereotype.Controller; // распознает класс как управляющий
import org.springframework.ui.Model; // интерфейс для взаимодействия конфигуратора и контролера и для добавляние всех жлементов в веб интерфейс
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView; // название html страниц


@Controller
public class AppController {

    @Autowired
    private GruzService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Gruz> listGruz = service.listAll(keyword);
        model.addAttribute("listGruz", listGruz);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewGruzForm(Model model) {
        Gruz gruz = new Gruz();
        model.addAttribute("gruz", gruz);
        return "new_gruz";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveGruz(@ModelAttribute("gruz") Gruz gruz) {
        service.save(gruz);
        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView showEditGruzForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_gruz");
        Gruz gruz = service.get(id);
        mav.addObject("gruz", gruz);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteGruz(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/sort")
    public String sort(Model model) {
        List<Gruz> listGruzsort = service.listordered();
        model.addAttribute("listGruzsort", listGruzsort);
        return "sort";
    }
    @RequestMapping("/meow")
    public String search(Model model) {
        List<Gruz> listGruz = service.listofall();
        model.addAttribute("listGruz", listGruz);
        return "meow";
    }



}


