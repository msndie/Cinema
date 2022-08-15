package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> findAll() {
        return entityManager.createQuery("from Film", Film.class).getResultList();
//        return entityManager.createQuery("SELECT e FROM Film e").getResultList();
    }

    public void save(Film film) {
        entityManager.getTransaction().begin();
        entityManager.persist(film);
        entityManager.getTransaction().commit();
    }
}
