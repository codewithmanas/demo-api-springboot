package com.codewithmanas.demoapi.services;

import com.codewithmanas.demoapi.entities.Todo;
import com.codewithmanas.demoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Create a new todo
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get todo by id
    public Todo getTodoById(Integer id) {
         Optional<Todo> todo =  todoRepository.findById(id);
         if(todo.isPresent()) {
             return todo.get();
         } else {
             throw new RuntimeException("Todo with ID " + id + " not found");
         }
    }

    // Get Todos by Completed Status
    public List<Todo> getTodosByStatus(String status) {

        boolean completed = false;

        if("completed".equalsIgnoreCase(status)) {
            completed = true;
        } else if ("incomplete".equalsIgnoreCase(status)) {
            completed = false;
        }

        // Call the repository method to fetch the Todos
        List<Todo> todos = todoRepository.findByCompleted(completed);

        return todos;
    }

    // Update an existing Todo
    public Todo updateTodoById(Integer id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(updatedTodo.getTitle());
                    existingTodo.setCompleted(updatedTodo.isCompleted());
                    Todo savedTodo = todoRepository.save(existingTodo);
                    return savedTodo;
                }).orElse(null);
    }


    // Delete todo by id
    public boolean deleteTodoById(Integer id) {
        Optional<Todo> todo = todoRepository.findById(id);

        if(todo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }

        return false;
    }


}
