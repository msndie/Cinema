package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> findAll();
    void save(Film film);
}
