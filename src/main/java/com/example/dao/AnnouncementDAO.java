package com.example.dao;
/*
 * Created by Laptev Egor 12/14/2020
 * */

import com.example.model.Announcement;
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

    private static final String URL = "jdbc:postgresql://localhost:5432/crudtask1";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Announcement> index() {
        ArrayList<Announcement> announcements = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ANNOUNCEMENTS");
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
        String req = "select * from announcements where id=" + id;
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
        return announcement;
    }

    public void save(Announcement announcement) {
        String req = "INSERT INTO ANNOUNCEMENTS (author, email, content) values ("
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
        String req= "update announcements set author='"
                + announcement.getAuthor() + "', email='"
                + announcement.getAuthorEmail() + "', content='" + announcement.getContent()
                + "' where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        String req = "delete from announcements where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
