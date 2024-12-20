package com.codewithmanas.demoapi.controllers;

import com.codewithmanas.demoapi.dtos.UpdateTodoStatusDto;
import com.codewithmanas.demoapi.entities.Todo;
import com.codewithmanas.demoapi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // Method to Create Todo
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        // Add the new todo to the list or database
        Todo savedTodo = todoService.createTodo(todo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTodo);
    }


    // Method to Get All Todos
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // Fetch a Single Todo by ID
    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);

        return ResponseEntity.ok(todo);
    }

    // Fetch Todos by Completed Status
    @GetMapping(value = "/todos", params = "status")
    public ResponseEntity<List<Todo>> getTodosByStatus(@RequestParam String status) {

        if(!(status.equals("completed") || status.equals("incomplete"))) {
            // Invalid status, return bad request
            return ResponseEntity.badRequest().body(null);
        }

        List<Todo> todos = todoService.getTodosByStatus(status);

        return ResponseEntity.ok(todos);

    }

    // Update an existing Todo
    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Integer id, @RequestBody Todo updatedTodo) {
       Todo todo = todoService.updateTodoById(id, updatedTodo);

       return ResponseEntity.ok(todo);
    }

    // Update Completed Status of existing Todo
    @PutMapping("/todos/{id}/status")
    public ResponseEntity<Todo> updateTodoStatusById(@PathVariable Integer id, @RequestBody UpdateTodoStatusDto updateTodoStatusDto) {
        Todo todo = todoService.updateTodoStatusById(id, updateTodoStatusDto);

        return ResponseEntity.ok(todo);
    }

    // Delete an existing Todo
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Integer id) {

        boolean isDeleted = todoService.deleteTodoById(id);

        if(isDeleted) {
            return ResponseEntity.ok("Todo Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
