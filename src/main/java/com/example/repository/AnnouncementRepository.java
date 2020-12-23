package com.example.repository;
/*
 * Created by Laptev Egor 12/21/2020
 * */

import com.example.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
