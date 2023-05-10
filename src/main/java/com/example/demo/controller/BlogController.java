package com.example.demo.controller;

import com.example.demo.Entity.RestBlog;
import com.example.demo.Services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private RestService service;

    @RequestMapping("/autoblog")
    public String viewAutoblogPage(Model model) {
        List<RestBlog> listRestBlog = service.listAllRestblog();
        model.addAttribute("listRestBlog", listRestBlog);
        return "autoblog";
    }

    @RequestMapping("/addblog")
//    @PreAuthorize("hasAuthority('admin')")
    public String showNewBlog(Model model) {
        RestBlog restBlog = new RestBlog();
        model.addAttribute("restBlog", restBlog);
        return "addAB";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("restBlog") RestBlog restBlog) {
        service.saveBlog(restBlog);
        return "redirect:/autoblog";
    }

    @RequestMapping("/editblog/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ModelAndView showEditBlogForm(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("editAB");
        RestBlog restBlog = service.getblog(id);
        mav.addObject("restBlog", restBlog);
        return mav;
    }

    @RequestMapping("/deleteblog/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteblog(@PathVariable(name = "id") Integer id) {
        service.deleteBlog(id);
        return "redirect:/autoblog";
    }
}
