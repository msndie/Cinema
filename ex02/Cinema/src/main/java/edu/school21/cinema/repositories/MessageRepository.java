package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findLast20ByFilmId(Long filmId);
}
