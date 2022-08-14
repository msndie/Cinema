package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;

import java.util.List;

public interface HallRepository {
    List<Hall> findAll();
    void save(Hall hall);
}
