package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll() {
        List<Session> list = new LinkedList<>();
        sessionRepository.findAll().forEach(list::add);
        return list;
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

    public List<Session> findByFilmNameContains(String filmName) {
        return sessionRepository.findByFilm_TitleContainsIgnoreCase(filmName);
    }

    public Optional<Session> findById(Long id) {
        return sessionRepository.findById(id);
    }
}
