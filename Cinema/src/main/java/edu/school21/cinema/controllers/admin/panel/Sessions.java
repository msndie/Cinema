package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/admin/panel/sessions")
public class Sessions {

    private final SessionService sessionService;
    private final HallService hallService;
    private final FilmService filmService;

    @Autowired
    public Sessions(SessionService sessionService, HallService hallService, FilmService filmService) {
        this.sessionService = sessionService;
        this.hallService = hallService;
        this.filmService = filmService;
    }

    @GetMapping
    public String get(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("SessionsList", sessionService.findAll());
        model.addAttribute("HallsList", hallService.findAll());
        model.addAttribute("FilmsList", filmService.findAll());
        return "admin/panel/sessions";
    }

    @PostMapping
    public String post(@ModelAttribute("model") ModelMap model, HttpServletRequest request) {
        System.out.println(request.getParameter("film"));
        System.out.println(request.getParameter("hall"));
        System.out.println(request.getParameter("date"));
        System.out.println(request.getParameter("price"));
        try {
            Session session = new Session();
            Optional<Hall> hall = hallService.findById(Long.parseLong(request.getParameter("hall")));
            Optional<Film> film = filmService.findById(Long.parseLong(request.getParameter("film")));
            if (hall.isPresent() && film.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                session.setPrice(BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))));
                session.setDateTime(LocalDateTime.parse(request.getParameter("date"), formatter));
                session.setHall(hall.get());
                session.setFilm(film.get());
                sessionService.add(session);
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/admin/panel/sessions";
    }
}
