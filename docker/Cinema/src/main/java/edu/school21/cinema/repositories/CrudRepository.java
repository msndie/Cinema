package edu.school21.cinema.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(T entity);
    void update(T entity);
}
