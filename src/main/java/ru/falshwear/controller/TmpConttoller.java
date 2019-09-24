package ru.falshwear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.falshwear.service.ImageService;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/photoPage")
public class TmpConttoller {

    @Autowired
    private ImageService imageService;

    @RequestMapping
    public String photoPage(Model model) {
        model.addAttribute("title", imageService.getImage("Photo21").getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(imageService.getImage("Photo21").getImage().getData()));
        return "photo";
    }

    @PostMapping("/submit")
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = imageService.addImage(title, image);
        return "redirect:../";
    }
}
