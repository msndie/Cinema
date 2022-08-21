CREATE TABLE IF NOT EXISTS halls (
    id BIGSERIAL PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS posters (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    name TEXT NOT NULL,
    extension text NOT NULL
);

CREATE TABLE IF NOT EXISTS films (
    id BIGSERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    poster_id BIGINT,
    CONSTRAINT posters
        FOREIGN KEY (poster_id)
            REFERENCES posters(id)
);

CREATE TABLE IF NOT EXISTS sessions (
    id BIGSERIAL PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES films(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGSERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    message TEXT NOT NULL,
    film_id BIGINT NOT NULL,
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES films(id)
);