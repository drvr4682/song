package com.example.song.controller;

import com.example.song.model.Song;
import com.example.song.service.SongH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private SongH2Service songService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("songs", songService.getSongs());
        model.addAttribute("song", new Song()); // for form binding
        return "index";
    }

    @PostMapping("/addSong")
    public String addSong(@ModelAttribute Song song) {
        songService.addSong(song);
        return "redirect:/";
    }

    @GetMapping("/editSong/{id}")
    public String editSong(@PathVariable("id") int id, Model model) {
        Song song = songService.getSongById(id);
        model.addAttribute("song", song);
        return "edit-song"; // loads edit-song.html
    }

    @PostMapping("/updateSong/{id}")
    public String updateSong(@PathVariable("id") int id, @ModelAttribute Song song) {
        songService.updateSong(id, song);
        return "redirect:/";
    }

    @GetMapping("/deleteSong/{id}")
    public String deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
        return "redirect:/";
    }
}