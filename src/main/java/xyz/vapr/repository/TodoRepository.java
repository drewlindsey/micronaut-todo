package xyz.vapr.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import xyz.vapr.repository.entities.Todo;

import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Optional<Todo> find(String title);
}
