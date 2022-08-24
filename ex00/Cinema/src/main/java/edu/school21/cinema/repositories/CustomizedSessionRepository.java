package edu.school21.cinema.repositories;

import java.util.List;

public interface CustomizedSessionRepository<T> {
    List<T> getAll();
}
