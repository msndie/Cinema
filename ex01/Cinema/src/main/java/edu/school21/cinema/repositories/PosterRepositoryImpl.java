package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PosterRepositoryImpl implements PosterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Poster entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Poster> findById(Long id) {
        try {
            return Optional.of(entityManager.createQuery("SELECT p FROM Poster p WHERE p.id = ?1", Poster.class)
                    .setParameter(1, id)
                    .getSingleResult());
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public List<Poster> findAll() {
        return entityManager.createQuery("SELECT p FROM Poster p", Poster.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Poster entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void update(Poster entity) {
        entityManager.merge(entity);
    }

    @Override
    public Poster findByUuid(UUID uuid) {
        try {
            return entityManager.createQuery("SELECT p FROM Poster p WHERE p.uuid = ?1", Poster.class)
                    .setParameter(1, uuid)
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }
}
