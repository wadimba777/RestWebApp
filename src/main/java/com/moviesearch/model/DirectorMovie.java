package com.moviesearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс предоставляющий связь между Director и Movie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorMovie {
    private int id;
    private int directorId;
    private int movieId;
}
