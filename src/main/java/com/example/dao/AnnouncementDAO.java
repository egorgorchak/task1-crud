package com.example.dao;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import com.example.model.Announcement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementDAO {
    private Connection connection;

    public AnnouncementDAO(@Value("${database.url}") String url,
                           @Value("${database.username}") String username,
                           @Value("${database.password}") String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Announcement> index() {
        ArrayList<Announcement> announcements = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM announcements");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String email = resultSet.getString("email");
                String content = resultSet.getString("content");
                Announcement announcement = new Announcement(id, author, email, content);
                announcements.add(announcement);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return announcements;
    }

    public Announcement show(int id) {
        String req = "SELECT * FROM announcements WHERE id=" + id;
        Announcement announcement = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            announcement = null;
            while (resultSet.next()) {
                int annId = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String email = resultSet.getString("email");
                String content = resultSet.getString("content");
                announcement = new Announcement(annId, author, email, content);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (announcement == null) {
            throw new RuntimeException("Specific announcement is not find!");
        }
        return announcement;
    }

    public void save(Announcement announcement) {
        String req = "INSERT INTO announcements (author, email, content) VALUES ("
                + "'"+ announcement.getAuthor() + "', '"
                + announcement.getAuthorEmail() + "', '"
                + announcement.getContent() + "')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Announcement announcement,
                       int id) {
        String req= "UPDATE announcements SET author='"
                + announcement.getAuthor() + "', email='"
                + announcement.getAuthorEmail() + "', content='" + announcement.getContent()
                + "' WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        String req = "DELETE FROM announcements WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
