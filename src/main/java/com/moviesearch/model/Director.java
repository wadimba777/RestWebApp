package com.moviesearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий режиссера фильма.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    private int id;
    private String name;
}
