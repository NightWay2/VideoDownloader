package com.example.VideoDownloader.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class DefaultController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/search";
    }
}
