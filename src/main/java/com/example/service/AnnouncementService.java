package com.example.service;
/*
 * Created by Laptev Egor 12/21/2020
 * */

import com.example.model.Announcement;
import com.example.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Announcement getAnnouncementById(Long id) {
        return repository.findById(id).get();
    }

    public Announcement saveAnnouncement(Announcement announcement) {
        return repository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        repository.deleteById(id);
    }

    public void updateAnnouncement(Announcement announcement, Long id) {
        Announcement announcementToUpdate = repository.getOne(id);
        announcementToUpdate.setContent(announcement.getContent());
        repository.save(announcementToUpdate);
    }
}
