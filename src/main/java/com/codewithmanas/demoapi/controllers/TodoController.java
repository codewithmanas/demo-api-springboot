package com.codewithmanas.demoapi.controllers;

import com.codewithmanas.demoapi.entities.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TodoController {
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        System.out.println("Received Todo: " + todo.getTitle() + ", Completed: " + todo.isCompleted());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todo);
    }

    // Simulating a database of 3 Todos
    private List<Todo> todos = Arrays.asList(
            new Todo(1L, "Buy Groceries", false),
            new Todo(2L, "Clean the house", false),
            new Todo(3L, "Read a book", false)
    );


    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todos);
    }

}
