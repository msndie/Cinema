package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class HallRepositoryImpl implements HallRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Hall entity) {
        entityManager.persist(entity);
    }

    @Override
    public Optional<Hall> findById(Long id) {
        try {
            return Optional.of(
                    entityManager.createQuery("SELECT h FROM Hall h WHERE h.id = ?1", Hall.class)
                            .setParameter(1, id)
                            .getSingleResult());
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public List<Hall> findAll() {
        return entityManager.createQuery(
                        "SELECT h FROM Hall h", Hall.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Hall entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void update(Hall entity) {
        entityManager.merge(entity);
    }

    @Override
    public Hall findHallBySerialNumber(Long serialNumber) {
        try {
            return entityManager.createQuery("SELECT h FROM Hall h WHERE h.serialNumber = ?1", Hall.class)
                    .setParameter(1, serialNumber)
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    @Override
    public boolean existsBySerialNumber(Long serialNumber) {
        return findHallBySerialNumber(serialNumber) != null;
    }
}
