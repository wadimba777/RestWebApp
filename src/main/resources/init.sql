CREATE TABLE IF NOT EXISTS directors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director_id INT,
    FOREIGN KEY (director_id) REFERENCES directors(id)
);

CREATE TABLE IF NOT EXISTS directors_movies (
    id SERIAL PRIMARY KEY,
    director_id INT,
    movie_id INT,
    FOREIGN KEY (director_id) REFERENCES directors(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);
