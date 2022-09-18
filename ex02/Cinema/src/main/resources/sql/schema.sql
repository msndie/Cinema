CREATE SCHEMA IF NOT EXISTS ex02;

CREATE SEQUENCE IF NOT EXISTS ex02.halls_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex02.films_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex02.sessions_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex02.messages_id AS BIGINT START WITH 7;

CREATE TABLE IF NOT EXISTS ex02.halls (
    id BIGINT DEFAULT nextval('ex02.halls_id') PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ex02.posters (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    name TEXT NOT NULL,
    extension text NOT NULL
);

CREATE TABLE IF NOT EXISTS ex02.films (
    id BIGINT DEFAULT nextval('ex02.films_id') PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    poster_id BIGINT,
    CONSTRAINT posters
        FOREIGN KEY (poster_id)
            REFERENCES ex02.posters(id)
);

CREATE TABLE IF NOT EXISTS ex02.sessions (
    id BIGINT DEFAULT nextval('ex02.sessions_id') PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES ex02.halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex02.films(id)
);

CREATE TABLE IF NOT EXISTS ex02.messages (
    id BIGINT DEFAULT nextval('ex02.messages_id') PRIMARY KEY,
    user_name TEXT NOT NULL,
    message TEXT NOT NULL,
    film_id BIGINT NOT NULL,
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex02.films(id)
);

ALTER SEQUENCE ex02.messages_id OWNED BY ex02.messages.id;
ALTER SEQUENCE ex02.sessions_id OWNED BY ex02.sessions.id;
ALTER SEQUENCE ex02.films_id OWNED BY ex02.films.id;
ALTER SEQUENCE ex02.halls_id OWNED BY ex02.halls.id;