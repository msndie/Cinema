package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message> {
    List<Message> findAllByFilmId(Long filmId);
    List<Message> findLast20ByFilmId(Long filmId);
}
