package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("songs")
public class SongController {
    @Autowired
    private ISongService songService;
    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("song/list");
        modelAndView.addObject("songs",songService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("song/create");
        modelAndView.addObject("song",new Song());
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("song/edit");
        Song song = songService.findOne(id);
        modelAndView.addObject("song",song);
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(Song song, RedirectAttributes redirectAttributes){
        songService.update(song);
        redirectAttributes.addFlashAttribute("success","Song was updated!");
        return "redirect:/songs";
        }
    @PostMapping("/create")
    public String create(@ModelAttribute Song song, RedirectAttributes redirectAttributes){
        songService.save(song);
        redirectAttributes.addFlashAttribute("success","New song was created!");
        return "redirect:/songs";
    }
    @PostMapping("/{id}/delete")
    public String deleteForm(@PathVariable Long id,RedirectAttributes redirectAttributes){
        songService.delete(id);
        redirectAttributes.addFlashAttribute("success","Song was deleted!");
        return "redirect:/songs";
    }
}
