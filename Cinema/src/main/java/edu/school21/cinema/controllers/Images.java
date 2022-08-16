package edu.school21.cinema.controllers;

import edu.school21.cinema.services.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/images")
public class Images {

    private String path;
    private PosterService posterService;

    @Autowired
    public void setPath(String path) {
        this.path = path;
    }

    @Autowired
    public void setPosterService(PosterService posterService) {
        this.posterService = posterService;
    }

    @PostMapping
    public String submit(@RequestParam("file") CommonsMultipartFile file) {
        System.out.println("test\n\n\n");
        String name = file.getOriginalFilename();
        String extension = file.getContentType();
        System.out.println(extension);
//        UUID uuid = UUID.randomUUID();
//        try {
//            byte barr[] = file.getBytes();
//            BufferedOutputStream = new BufferedOutputStream((new FileOutputStream(path + "/" + uuid)))
//        }
        return "redirect:/admin/panel/films";
    }
}
