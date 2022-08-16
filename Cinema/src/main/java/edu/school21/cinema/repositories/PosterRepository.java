package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends CrudRepository<Poster, Long> {
}
