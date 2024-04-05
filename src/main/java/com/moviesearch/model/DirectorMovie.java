package com.moviesearch.model;

import java.util.Objects;

public class DirectorMovie {
    private int id;
    private int directorId;
    private int movieId;

    public DirectorMovie() {
    }

    public DirectorMovie(int id, int directorId, int movieId) {
        this.id = id;
        this.directorId = directorId;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorMovie that = (DirectorMovie) o;
        return id == that.id && directorId == that.directorId && movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, directorId, movieId);
    }

    @Override
    public String toString() {
        return "DirectorMovie{" +
                "id=" + id +
                ", directorId=" + directorId +
                ", movieId=" + movieId +
                '}';
    }
}
