CREATE SCHEMA IF NOT EXISTS ex01;

CREATE SEQUENCE IF NOT EXISTS ex01.halls_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex01.films_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex01.sessions_id AS BIGINT START WITH 4;

CREATE TABLE IF NOT EXISTS ex01.halls (
    id BIGINT DEFAULT nextval('ex01.halls_id') PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ex01.posters (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    name TEXT NOT NULL,
    extension text NOT NULL
);

CREATE TABLE IF NOT EXISTS ex01.films (
    id BIGINT DEFAULT nextval('ex01.films_id') PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    poster_id BIGINT,
    CONSTRAINT posters
        FOREIGN KEY (poster_id)
            REFERENCES ex01.posters(id)
);

CREATE TABLE IF NOT EXISTS ex01.sessions (
    id BIGINT DEFAULT nextval('ex01.sessions_id') PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES ex01.halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex01.films(id)
);

ALTER SEQUENCE ex01.sessions_id OWNED BY ex01.sessions.id;
ALTER SEQUENCE ex01.films_id OWNED BY ex01.films.id;
ALTER SEQUENCE ex01.halls_id OWNED BY ex01.halls.id;