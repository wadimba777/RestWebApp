package com.moviesearch.util;

//import io.github.cdimascio.dotenv.Dotenv;

public class VariableLoader {
//    static Dotenv env = Dotenv.load();

    static final String URL = "jdbc:postgresql://localhost:5432/movielist_db"; //env.get("DB_URL");
    static final String USER = "postgres"; //env.get("DB_USER");
    static final String PASSWORD = "y2k" ;//env.get("DB_PASSWORD");
}
