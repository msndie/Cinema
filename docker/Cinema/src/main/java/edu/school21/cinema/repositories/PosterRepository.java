package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;

import java.util.UUID;

public interface PosterRepository extends CrudRepository<Poster> {
    Poster findByUuid(UUID uuid);
}
