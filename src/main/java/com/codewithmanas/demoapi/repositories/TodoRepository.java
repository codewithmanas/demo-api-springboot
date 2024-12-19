package com.codewithmanas.demoapi.repositories;

import com.codewithmanas.demoapi.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    // Custom query method to find Todos by completed status (true/false)
    List<Todo> findByCompleted(boolean completed);

}
