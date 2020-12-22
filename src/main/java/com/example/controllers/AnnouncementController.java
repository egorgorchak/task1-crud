package com.example.controllers;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import com.example.model.Announcement;
import com.example.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnnouncementController {
    private final AnnouncementService service;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.service = announcementService;
    }

    @GetMapping
    public String index(Model model) {
        List<Announcement> index = (List<Announcement>) service.getAllAnnouncements();
        model.addAttribute("announcements", index);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Announcement announcement = service.getAnnouncementById(id).get();
        model.addAttribute("ann", announcement);
        return "show";
    }

    @GetMapping("/new")
    public String newAnnouncement(@ModelAttribute("announcement") Announcement announcement) {
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("announcement") @Valid Announcement announcement,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        service.saveAnnouncement(announcement);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("ann", service.getAnnouncementById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ann") @Valid Announcement announcement,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasFieldErrors("content")) {
            return "board/edit";
        }
//        announcementDAO.update(announcement, id);
        return "redirect:/board";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteAnnouncement(id);
        return "redirect:/board";
    }
}
