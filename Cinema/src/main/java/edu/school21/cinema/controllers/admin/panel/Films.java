package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/panel/films")
public class Films {

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String get(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("FilmsList", filmService.findAll());
        return "admin/panel/films";
    }

    @PostMapping
    public String post(@ModelAttribute("model") ModelMap model, HttpServletRequest request) {
        return "redirect:/admin/panel/films";
    }
}
