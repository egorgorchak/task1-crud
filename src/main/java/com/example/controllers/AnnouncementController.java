package com.example.controllers;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import com.example.dao.AnnouncementDAO;
import com.example.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class AnnouncementController {
    private AnnouncementDAO announcementDAO;

    @Autowired
    public AnnouncementController(AnnouncementDAO announcementDAO) {
        this.announcementDAO = announcementDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<Announcement> index = announcementDAO.index();
        model.addAttribute("announcements", index);
        return "board/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Announcement announcement = announcementDAO.show(id);
        model.addAttribute("ann", announcement);
        return "board/show";
    }

    @GetMapping("/new")
    public String newAnnouncement(Model model, Announcement announcement) {
        model.addAttribute("announcement", announcement);
        return "board/new";
    }

    @PostMapping
    public String create(Announcement announcement) {
        announcementDAO.save(announcement);
        return "redirect:/board";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("ann", announcementDAO.show(id));
        return "board/edit";
    }

    @PatchMapping("/{id}")
    public String update(Announcement announcement,
                         @PathVariable("id") int id,
                         Model model) {
        model.addAttribute("ann", announcement);
        announcementDAO.update(announcement, id);
        return "redirect:/board";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        announcementDAO.delete(id);
        return "redirect:/board";
    }
}
