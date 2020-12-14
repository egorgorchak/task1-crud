package com.example.model;
/*
 * Created by Laptev Egor 12/14/2020
 * */

public class Announcement {
    private int id;
    private String author;
    private String authorEmail;
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
