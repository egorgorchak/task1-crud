package com.example.service;
/*
 * Created by Laptev Egor 12/21/2020
 * */

import com.example.model.Announcement;
import com.example.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnouncementService {
    private final AnnouncementRepository repository;

    @Autowired
    public AnnouncementService(AnnouncementRepository repository) {
        this.repository = repository;
    }

    public Iterable<Announcement> getAllAnnouncements() {
        return repository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(Long id) {
        return repository.findById(id);
    }

    public Announcement saveAnnouncement(Announcement announcement) {
        return repository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        repository.deleteById(id);
    }

    public void updateAnnouncement(Long id) {
        Announcement announcementToUpdate = repository.getOne(id);

    }
}
