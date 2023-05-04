package com.example.demo.controller;
// контроллеры
import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.Autoblog;
import com.example.demo.Gruz;
import com.example.demo.GruzService;
import com.example.demo.User;
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
    private GruzService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Gruz> listGruz = service.listAll(keyword);
        model.addAttribute("listGruz", listGruz);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    @PreAuthorize("hasAuthority('admin')")
    public String showNewGruzForm(Model model) {
        Gruz gruz = new Gruz();
        model.addAttribute("gruz", gruz);
        return "new_gruz";
    }
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveGruz(@ModelAttribute("gruz") Gruz gruz) {
        service.save(gruz);
        return "redirect:/";
    }


    @RequestMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ModelAndView showEditGruzForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_gruz");
        Gruz gruz = service.get(id);
        mav.addObject("gruz", gruz);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
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
    @RequestMapping("exp/error")
    public String error(){
        return "error";
    }



    @RequestMapping("/autoblog")
    public String viewAutoblogPage(Model model) {
        List<Autoblog> listAutoblog = service.listAllAutoblog();
        model.addAttribute("listAutoblog", listAutoblog);
        return "autoblog";
    }

    @RequestMapping("/addblog")
//    @PreAuthorize("hasAuthority('admin')")
    public String showNewBlog(Model model) {
        Autoblog autoblog = new Autoblog();
        model.addAttribute("autoblog", autoblog);
        return "addAB";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("autoblog") Autoblog autoblog) {
        service.saveBlog(autoblog);
        return "redirect:/autoblog";
    }

    @RequestMapping("/editblog/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ModelAndView showEditBlogForm(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("editAB");
        Autoblog autoblog = service.getblog(id);
        mav.addObject("autoblog", autoblog);
        return mav;
    }

    @RequestMapping("/deleteblog/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteblog(@PathVariable(name = "id") Integer id) {
        service.deleteBlog(id);
        return "redirect:/autoblog";
    }

}


