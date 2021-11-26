package com.codegym.model;

import javax.persistence.*;

@Entity
@Table
public class Song implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String type;
    private String path;

    public Song() {
    }

    public Song(String name, String author, String type, String path) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.path = path;
    }

    public Song(Long id, String name, String author, String type, String path) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @Override
    public Song clone() {
        Song song = new Song();
        song.setId(id);
        song.setName(name);
        song.setAuthor(author);
        song.setType(type);
        song.setPath(path);

        return song;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
