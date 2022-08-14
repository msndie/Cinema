package edu.school21.cinema.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mvc.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Admin {

//    @RequestMapping(value = "/admin/panel/halls", method = RequestMethod.GET)
//    public ModelAndView getHalls() {
//        return new ModelAndView("test");
//    }
    @GetMapping
    public String getHalls(@ModelAttribute("model") ModelMap model) {
        List<String> kek = new ArrayList<>();
        kek.add("lol");
        kek.add("cheburek");
        model.addAttribute("carList", kek);
        return "admin";
    }
}
