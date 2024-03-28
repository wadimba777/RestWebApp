package com.moviesearch.model;

import java.util.Objects;

/**
 * Класс, представляющий фильм.
 */
public class Movie {

    /**
     * Уникальный идентификатор фильма.
     */
    private int id;

    /**
     * Название фильма.
     */
    private String title;

    /**
     * Идентификатор режиссера фильма.
     */
    private int directorId;

    /**
     * Конструктор по умолчанию.
     */
    public Movie() {
    }

    /**
     * Конструктор для создания экземпляра фильма с указанным идентификатором, названием и идентификатором режиссера.
     *
     * @param id         Уникальный идентификатор фильма
     * @param title      Название фильма
     * @param directorId Идентификатор режиссера фильма
     */
    public Movie(int id, String title, int directorId) {
        this.id = id;
        this.title = title;
        this.directorId = directorId;
    }

    /**
     * Возвращает уникальный идентификатор фильма.
     *
     * @return Уникальный идентификатор фильма
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор фильма.
     *
     * @param id Уникальный идентификатор фильма
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает название фильма.
     *
     * @return Название фильма
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название фильма.
     *
     * @param title Название фильма
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает идентификатор режиссера фильма.
     *
     * @return Идентификатор режиссера фильма
     */
    public int getDirectorId() {
        return directorId;
    }

    /**
     * Устанавливает идентификатор режиссера фильма.
     *
     * @param directorId Идентификатор режиссера фильма
     */
    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    /**
     * Переопределение метода equals для сравнения двух объектов класса Movie.
     *
     * @param o Объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && directorId == movie.directorId && Objects.equals(title, movie.title);
    }

    /**
     * Переопределение метода hashCode для вычисления хеш-кода объекта.
     *
     * @return Хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, directorId);
    }

    /**
     * Переопределение метода toString для получения строкового представления объекта.
     *
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", directorId=" + directorId +
                '}';
    }
}
