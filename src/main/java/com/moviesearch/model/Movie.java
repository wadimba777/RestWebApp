package com.moviesearch.model;

import java.util.Objects;

public class Movie {

    private int id;
    private String title;
    private int year;
    private int directorId;

    public Movie() {
    }

    public Movie(int id, String title, int year, int directorId) {
        this.id = id;
        this.title = title;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return id == movie.id && year == movie.year && directorId == movie.directorId && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, directorId);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", directorId=" + directorId +
                '}';
    }
}
