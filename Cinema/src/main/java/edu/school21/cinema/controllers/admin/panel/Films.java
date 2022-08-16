package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Film;
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

    private static final int FIRST_FILM = 1895;
    private static final int CURRENT_YEAR = 2022;
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
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String description = request.getParameter("description");
        String age = request.getParameter("age");
        Integer ageRestrictions = null;
        Integer yearOfRelease = null;
        try {
            ageRestrictions = Integer.parseInt(age);
            yearOfRelease = Integer.parseInt(year);
        } catch (NumberFormatException ignored) {}
        if (ageRestrictions != null && yearOfRelease != null
            && yearOfRelease >= FIRST_FILM && yearOfRelease <= CURRENT_YEAR) {
            Film film = new Film();
            film.setDescription(description);
            film.setTitle(title);
            film.setYear(yearOfRelease);
            film.setAgeRestrictions(ageRestrictions);
            if (filmService.add(film)) {
                System.out.println("Film added");
                System.out.println(film);
            }
        }
        return "redirect:/admin/panel/films";
    }
}
