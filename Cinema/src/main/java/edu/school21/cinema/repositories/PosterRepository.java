package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;

import java.util.List;

public interface PosterRepository {
    List<Poster> findAll();
    void save(Poster poster);
}
