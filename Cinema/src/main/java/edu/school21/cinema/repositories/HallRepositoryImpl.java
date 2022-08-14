package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HallRepositoryImpl implements HallRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Hall> findAll() {
        return entityManager.createQuery("SELECT h FROM Hall h").getResultList();
    }

    public void save(Hall hall) {
        entityManager.getTransaction().begin();
        entityManager.persist(hall);
        entityManager.getTransaction().commit();
    }
}
