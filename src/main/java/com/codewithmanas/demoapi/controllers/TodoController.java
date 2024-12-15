package com.codewithmanas.demoapi.controllers;

import com.codewithmanas.demoapi.entities.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        System.out.println("Received Todo: " + todo.getTitle() + ", Completed: " + todo.isCompleted());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todo);
    }
}
