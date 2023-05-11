package com.example.demo.controller;
// контроллеры
import java.util.List;

import com.example.demo.Entity.RestBlog;
import com.example.demo.Entity.RestMenu;
import com.example.demo.Services.RestService;
import org.springframework.beans.factory.annotation.Autowired; // внедряет зависимости
import org.springframework.data.repository.query.Param; // привязываем данные к sql запросу
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller; // распознает класс как управляющий
import org.springframework.ui.Model; // интерфейс для взаимодействия конфигуратора и контролера и для добавляние всех жлементов в веб интерфейс
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView; // название html страниц


@Controller
public class AppController {

    @Autowired
    private RestService service;

    @RequestMapping("/")
    public String viewHomePage() {
        return "Restorun/index";
    }

    @RequestMapping("/news")
    public String viewHomePage2(Model model, @Param("keyword") String keyword) {
        List<RestBlog> listRestBlog = service.listAllRestblog();
        model.addAttribute("listRestBlog", listRestBlog);
        model.addAttribute("keyword", keyword);
        return "Restorun/news";
    }

    @RequestMapping("/new")
    @PreAuthorize("hasAuthority('admin')")
    public String showNewGruzForm(Model model) {
        RestMenu restMenu = new RestMenu();
        model.addAttribute("restMenu", restMenu);
        return "new_gruz";
    }
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveGruz(@ModelAttribute("restMenu") RestMenu restMenu) {
        service.save(restMenu);
        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ModelAndView showEditRestForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("Restorun/edit_menu");
        RestMenu restMenu = service.get(id);
        mav.addObject("restMenu", restMenu);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteRest(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @RequestMapping("/sort")
    public String sort(Model model) {
        List<RestMenu> listRestSort = service.listordered();
        model.addAttribute("listRestSort", listRestSort);
        return "sort";
    }
    @RequestMapping("/meow")
    public String search(Model model) {
        List<RestMenu> listRestMenu = service.listofall();
        model.addAttribute("listRestMenu", listRestMenu);
        return "meow";
    }

}


