package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll() {
        return sessionRepository.getAll();
    }

    public boolean add(Session entity) {
        sessionRepository.save(entity);
        return true;
    }

    public void delete(Session entity) {
        sessionRepository.delete(entity);
    }

    public void update(Session entity) {
        sessionRepository.save(entity);
    }
}
