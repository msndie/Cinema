package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/panel/halls")
public class Admin {

    private HallService hallService;

    @Autowired
    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public String getHalls(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("HallsList", hallService.findAll());
        return "admin";
    }

    @PostMapping
    public String postHall(@ModelAttribute("model") ModelMap model, HttpServletRequest request) {
        Long serialNumber = null;
        Integer numberOfSeats = null;
        try {
            serialNumber = Long.parseLong(request.getParameter("serialNumber"));
        } catch (NumberFormatException ignored) {
            System.out.println(request.getParameter("serialNumber"));
            System.out.println("EXCEPTION");
        }
        try {
            numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
        } catch (NumberFormatException ignored) {
            System.out.println(request.getParameter("numberOfSeats"));
            System.out.println("EXCEPTION");
        }
        if (serialNumber != null && numberOfSeats != null
            && serialNumber > 0 && numberOfSeats > 0) {
            Hall hall = new Hall();
            hall.setSerialNumber(serialNumber);
            hall.setNumberOfSeats(numberOfSeats);
            hallService.addNewHall(hall);
            System.out.println("Hall added");
            System.out.println(hall);
        }
        return "redirect:/admin/panel/halls";
    }
}
