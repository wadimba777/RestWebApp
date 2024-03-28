package com.moviesearch.model;

import java.util.Objects;

/**
 * Класс, представляющий режиссера фильма.
 */
public class Director {

    /**
     * Уникальный идентификатор режиссера.
     */
    private int id;

    /**
     * Имя режиссера.
     */
    private String name;

    /**
     * Конструктор по умолчанию.
     */
    public Director() {
    }

    /**
     * Конструктор для создания экземпляра режиссера с указанным идентификатором и именем.
     *
     * @param id   Уникальный идентификатор режиссера
     * @param name Имя режиссера
     */
    public Director(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает уникальный идентификатор режиссера.
     *
     * @return Уникальный идентификатор режиссера
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор режиссера.
     *
     * @param id Уникальный идентификатор режиссера
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает имя режиссера.
     *
     * @return Имя режиссера
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя режиссера.
     *
     * @param name Имя режиссера
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Переопределение метода equals для сравнения двух объектов класса Director.
     *
     * @param o Объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return id == director.id && Objects.equals(name, director.name);
    }

    /**
     * Переопределение метода hashCode для вычисления хеш-кода объекта.
     *
     * @return Хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Переопределение метода toString для получения строкового представления объекта.
     *
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
