package com.example.model;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    @NotEmpty(message = "Name shouldn't be empty!")
    @Size(min = 2)
    private String author;

    @Column(name = "email")
    @Email
    private String authorEmail;

    @Column(name = "content")
    @NotEmpty(message = "Content shouldn't be empty!")
    @Size(min = 3)
    private String content;

    public Announcement(String author, String authorEmail, String content) {
        this.author = author;
        this.authorEmail = authorEmail;
        this.content = content;
    }

    public Announcement() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
