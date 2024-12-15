package com.codewithmanas.demoapi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TodoController {
    @PostMapping("/todos")
    public Map<String, Object>  createTodo(@RequestBody Map<String, Object> todoData) {
        System.out.println("todoData: " + todoData);
        System.out.println("typeof completed: " + todoData.get("completed").getClass().getSimpleName());

//        return "Todo created successfully";
        return todoData;
    }
}
