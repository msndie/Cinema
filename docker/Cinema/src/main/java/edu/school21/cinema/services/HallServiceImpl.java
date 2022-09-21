package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    private HallRepository hallRepository;

    @Autowired
    public void setHallRepository(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public boolean add(Hall hall) {
        if (!hallRepository.existsBySerialNumber(hall.getSerialNumber())) {
            hallRepository.save(hall);
            return true;
        }
        return false;
    }

    public void delete(Hall entity) {
        hallRepository.delete(entity);
    }

    public void update(Hall entity) {
        hallRepository.update(entity);
    }

    public Optional<Hall> findById(Long id) {
        return hallRepository.findById(id);
    }
}
