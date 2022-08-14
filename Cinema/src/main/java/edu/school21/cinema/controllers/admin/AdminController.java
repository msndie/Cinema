package edu.school21.cinema.controllers.admin;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mvc.Controller;

@RestController
@RequestMapping("/")
public class AdminController {

//    @RequestMapping(value = "/admin/panel/halls", method = RequestMethod.GET)
//    public ModelAndView getHalls() {
//        return new ModelAndView("test");
//    }
//    @RequestMapping(value = "admin/panel/halls", method = RequestMethod.GET)
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHalls() {
        return "Kek";
    }
}
