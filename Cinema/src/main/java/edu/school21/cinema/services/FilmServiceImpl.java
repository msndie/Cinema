package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public void setFilmRepository(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll() {
        List<Film> list = new LinkedList<>();
        filmRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean add(Film entity) {
        if (!filmRepository.existsByTitleAndYearAndDescription(entity.getTitle(),
                entity.getYear(), entity.getDescription())) {
            filmRepository.save(entity);
        }
        return true;
    }
}
