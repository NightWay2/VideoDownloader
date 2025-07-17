package com.example.VideoDownloader.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller("search")
public class SearchController {

    @GetMapping("search")
    public String getUrl(Model model) {
        String url = String.valueOf(model.getAttribute("video.url"));

        if (computing(url).contains("ERROR")) {
            return "ERROR: video unplayable.";
        }

        return "redirect:preview";
    }

    public String computing(String url) {
        ProcessBuilder pb = new ProcessBuilder("yt-dlp.exe",
                "-g", url);
        pb.redirectErrorStream(true);

        StringBuilder lines = new StringBuilder();
        try {
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.append(line);
                    //System.out.println("URL: " + line);
                }
            }
        } catch (IOException ignore) {}

        return lines.toString();
    }
}
