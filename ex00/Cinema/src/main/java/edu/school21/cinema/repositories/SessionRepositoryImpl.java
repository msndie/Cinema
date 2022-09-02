package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Session entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Session> findById(Long id) {
        try {
            return Optional.of(entityManager.createQuery("SELECT s FROM Session s WHERE s.id = ?1", Session.class)
                    .setParameter(1, id)
                    .getSingleResult());
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public List<Session> findAll() {
        return entityManager.createQuery("SELECT s FROM Session s JOIN FETCH s.film f LEFT JOIN FETCH f.poster JOIN FETCH s.hall",
                        Session.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Session entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void update(Session entity) {
        entityManager.merge(entity);
    }
}
