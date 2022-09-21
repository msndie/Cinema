package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;
import edu.school21.cinema.repositories.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PosterServiceImpl implements PosterService {

    private PosterRepository posterRepository;

    @Autowired
    public void setPosterRepository(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    public List<Poster> findAll() {
        return posterRepository.findAll();
    }

    public boolean add(Poster entity) {
        posterRepository.save(entity);
        return true;
    }

    public void delete(Poster entity) {
        posterRepository.delete(entity);
    }

    public void update(Poster entity) {
        posterRepository.update(entity);
    }

    public Poster findByUuid(UUID uuid) {
        return posterRepository.findByUuid(uuid);
    }
}
