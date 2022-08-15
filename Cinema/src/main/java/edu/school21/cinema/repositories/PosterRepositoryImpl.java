package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PosterRepositoryImpl implements PosterRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Poster> findAll() {
        return entityManager.createQuery("SELECT p FROM Poster p").getResultList();
    }

    public void save(Poster poster) {
        entityManager.getTransaction().begin();
        entityManager.persist(poster);
        entityManager.getTransaction().commit();
    }
}
