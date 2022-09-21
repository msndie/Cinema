package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;

public interface FilmRepository extends CrudRepository<Film> {
    boolean existsByTitleAndYearAndDescription(String title, int year, String description);
    Film findByPosterId(Long id);
}
