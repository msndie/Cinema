package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallRepositoryImpl implements HallRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Hall> findAll() {
        return entityManager.createQuery("SELECT h FROM Hall h").getResultList();
    }

    @Transactional
    public void save(Hall hall) {
//        entityManager.getTransaction().begin();
        entityManager.persist(hall);
//        entityManager.getTransaction().commit();
    }
}
