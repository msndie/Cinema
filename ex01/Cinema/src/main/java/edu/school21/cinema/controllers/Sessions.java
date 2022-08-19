package edu.school21.cinema.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sessions/search")
public class Sessions {

    @GetMapping
    public ResponseEntity get(HttpServletRequest request) {
        
    }
}
