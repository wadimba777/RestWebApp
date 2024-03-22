package com.moviesearch.util;

import io.github.cdimascio.dotenv.Dotenv;

public class VariableLoader {
    static Dotenv env = Dotenv.load();

    static final String URL = env.get("DB_URL");
    static final String USER = env.get("DB_USER");
    static final String PASSWORD = env.get("DB_PASSWORD");
}
