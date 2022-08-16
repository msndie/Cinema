package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;
import edu.school21.cinema.repositories.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PosterServiceImpl implements PosterService {

    private PosterRepository posterRepository;

    @Autowired
    public void setPosterRepository(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    public List<Poster> findAll() {
        List<Poster> list = new LinkedList<>();
        posterRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean add(Poster entity) {
        posterRepository.save(entity);
        return true;
    }
}
