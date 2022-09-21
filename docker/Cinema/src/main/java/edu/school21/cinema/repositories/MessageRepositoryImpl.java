package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            return Optional.of(entityManager.createQuery("SELECT m FROM Message m WHERE m.id = ?1", Message.class)
                    .setParameter(1, id)
                    .getSingleResult());
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createQuery("SELECT m FROM Message m", Message.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Message entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void update(Message entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<Message> findAllByFilmId(Long filmId) {
        return entityManager.createQuery("SELECT m FROM Message m WHERE m.filmId = ?1", Message.class)
                .setParameter(1, filmId)
                .getResultList();
    }

    @Override
    public List<Message> findLast20ByFilmId(Long filmId) {
        List<Message> messages = entityManager.createQuery(
                        "SELECT m FROM Message m WHERE m.filmId = ?1 ORDER BY m.id DESC",
                        Message.class)
                .setParameter(1, filmId)
                .setMaxResults(20)
                .getResultList();
        Collections.reverse(messages);
        return messages;
    }
}
