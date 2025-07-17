package com.example.VideoDownloader.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String getView() {
        return "search";
    }

    @PostMapping("/search")
    @ResponseBody
    public String checkUrl(@RequestParam("videoUrl") String videoUrl) {

        String links = computing(videoUrl.trim());

        if (links.contains("ERROR")) {
            return "ERROR: video unplayable.";
        }

        //return "redirect:preview";
        return links;
    }

    public String computing(String url) {

        ProcessBuilder pb = new ProcessBuilder("yt-dlp.exe", "-g", url);
        pb.redirectErrorStream(true);

        StringBuilder lines = new StringBuilder();
        try {
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.append(line);
                }
            }
        } catch (IOException ignore) {}

        return lines.toString();
    }
}
