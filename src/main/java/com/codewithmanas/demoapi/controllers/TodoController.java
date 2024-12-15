package com.codewithmanas.demoapi.controllers;

import com.codewithmanas.demoapi.entities.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    // Simulating a database of 3 Todos
    // Constructor to preload some data
    public TodoController() {
        todos.add(new Todo(1L, "Buy groceries", false));
        todos.add(new Todo(2L, "Study Java", true));
        todos.add(new Todo(3L, "Clean the house", false));
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        System.out.println("Received Todo: " + todo.getTitle() + ", Completed: " + todo.isCompleted());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todo);
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        System.out.println("Todo Id: " + id);

        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
