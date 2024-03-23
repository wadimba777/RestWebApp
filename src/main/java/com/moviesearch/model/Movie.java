package com.moviesearch.model;

import java.util.Objects;

public class Movie {

    private int id;
    private String title;
    private int directorId;

    public Movie() {
    }

    public Movie(int id, String title, int directorId) {
        this.id = id;
        this.title = title;
        this.directorId = directorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && directorId == movie.directorId && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, directorId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", directorId=" + directorId +
                '}';
    }
}
