CREATE SCHEMA IF NOT EXISTS ex00;

CREATE SEQUENCE IF NOT EXISTS ex00.halls_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex00.films_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex00.sessions_id AS BIGINT START WITH 4;

CREATE TABLE IF NOT EXISTS ex00.halls (
    id BIGINT DEFAULT nextval('ex00.halls_id') PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ex00.posters (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    name TEXT NOT NULL,
    extension text NOT NULL
);

CREATE TABLE IF NOT EXISTS ex00.films (
    id BIGINT DEFAULT nextval('ex00.films_id') PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    poster_id BIGINT,
    CONSTRAINT posters
        FOREIGN KEY (poster_id)
            REFERENCES ex00.posters(id)
);

CREATE TABLE IF NOT EXISTS ex00.sessions (
    id BIGINT DEFAULT nextval('ex00.sessions_id') PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES ex00.halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex00.films(id)
);

ALTER SEQUENCE ex00.sessions_id OWNED BY ex00.sessions.id;
ALTER SEQUENCE ex00.films_id OWNED BY ex00.films.id;
ALTER SEQUENCE ex00.halls_id OWNED BY ex00.halls.id;