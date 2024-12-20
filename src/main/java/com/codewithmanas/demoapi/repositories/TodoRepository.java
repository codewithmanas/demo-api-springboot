package com.codewithmanas.demoapi.repositories;

import com.codewithmanas.demoapi.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    // Custom query method to find Todos by completed status (true/false)
    List<Todo> findByCompleted(boolean completed);

}
