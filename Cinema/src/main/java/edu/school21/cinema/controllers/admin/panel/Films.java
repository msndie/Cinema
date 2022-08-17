package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin/panel/films")
public class Films {

    private static final int FIRST_FILM = 1895;
    private static final int CURRENT_YEAR = 2022;
    private final FilmService filmService;
    private final PosterService posterService;
    private final String path;

    @Autowired
    public Films(FilmService filmService, PosterService posterService, String path) {
        this.filmService = filmService;
        this.posterService = posterService;
        this.path = path;
    }

    @GetMapping
    public String get(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("FilmsList", filmService.findAll());
        return "admin/panel/films";
    }

    @PostMapping
    public String post(@ModelAttribute("model") ModelMap model,
                       HttpServletRequest request,
                       @RequestParam(value = "file") MultipartFile file) {
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
                System.out.println(file);
                if (file != null) {
                    imageHelper(file, film);
                }
            }
        }
        return "redirect:/admin/panel/films";
    }

    private void imageHelper(MultipartFile file, Film film) {
        String name =file.getOriginalFilename();
        String[] extension = file.getContentType().split("/");
        UUID uuid = UUID.randomUUID();
        if (Utils.createFile(file, path, extension[1], uuid)) {
            Poster poster = new Poster();
            poster.setName(name);
            poster.setUuid(uuid);
            poster.setExtension(extension[1]);
            posterService.add(poster);
            film.setPoster(poster);
            filmService.update(film);
        }
    }
}
