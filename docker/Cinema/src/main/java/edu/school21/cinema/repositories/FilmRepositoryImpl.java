package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Film entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Film> findById(Long id) {
        try {
            return Optional.of(
                    entityManager.createQuery("SELECT f FROM Film f LEFT JOIN FETCH f.poster WHERE f.id = ?1", Film.class)
                    .setParameter(1, id)
                    .getSingleResult());
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findAll() {
        return entityManager.createQuery(
                "SELECT f FROM Film f LEFT JOIN FETCH f.poster", Film.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Film entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void update(Film entity) {
        entityManager.merge(entity);
    }

    @Override
    public boolean existsByTitleAndYearAndDescription(String title, int year, String description) {
        try {
            return entityManager.createQuery("SELECT f FROM Film f WHERE f.title = ?1 AND f.year = ?2 AND f.description = ?3")
                    .setParameter(1, title)
                    .setParameter(2, year)
                    .setParameter(3, description)
                    .getSingleResult() != null;
        } catch (NoResultException ignored) {
            return false;
        }
    }

    @Override
    public Film findByPosterId(Long id) {
        try {
            return entityManager.createQuery("SELECT f FROM Film f LEFT JOIN FETCH f.poster WHERE f.poster.id = ?1", Film.class)
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }
}
