package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Session> findAll() {
        return entityManager.createQuery("SELECT s FROM Session s").getResultList();
    }

    public void save(Session session) {
        entityManager.getTransaction().begin();
        entityManager.persist(session);
        entityManager.getTransaction().commit();
    }
}
