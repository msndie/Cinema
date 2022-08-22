package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByFilm_TitleContainsIgnoreCase(String title);
}