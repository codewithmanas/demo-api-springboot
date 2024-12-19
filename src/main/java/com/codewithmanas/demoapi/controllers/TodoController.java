package com.codewithmanas.demoapi.controllers;

import com.codewithmanas.demoapi.entities.Todo;
import com.codewithmanas.demoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // Method to Create Todo
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        // Add the new todo to the list or database
        Todo savedTodo = todoRepository.save(todo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTodo);
    }


    // Method to Get All Todos
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    // Fetch a Single Todo by ID
    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {

        return todoRepository.findById(id)
                .map(todo -> ResponseEntity.ok(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    // Fetch Todos by Completed Status
    @GetMapping(value = "/todos", params = "status")
    public ResponseEntity<List<Todo>> getTodosByStatus(@RequestParam String status) {

        boolean completed;

        if("completed".equalsIgnoreCase(status)) {
            completed = true;
        } else if ("incomplete".equalsIgnoreCase(status)) {
            completed = false;
        } else {
            // Invalid status, return bad request
            return ResponseEntity.badRequest().body(null);
        }

        // Call the repository method to fetch the Todos
        List<Todo> todos = todoRepository.findByCompleted(completed);

        return ResponseEntity.ok(todos);

    }

    // Update an existing Todo
    @PutMapping("/todos/{id}")
    public ResponseEntity<String> updateTodoById(@PathVariable Integer id) {
       return ResponseEntity.ok("We will implement later");
    }

    // Delete an existing Todo
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Integer id) {

        if(todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
