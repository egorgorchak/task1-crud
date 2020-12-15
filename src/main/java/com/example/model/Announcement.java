package com.example.model;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Announcement {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 25, message = "Name should be between 2 and 30 characters")
    private String author;

    @Email(message = "Not valid email!")
    @NotEmpty(message = "Email shouldn't be empty")
    private String authorEmail;

    @Size(min = 2, message = "Announcement is too short!")
    @NotEmpty(message = "Announcement shouldn't be empty")
    private String content;

    public Announcement(int id, String author, String authorEmail, String content) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
