package com.codewithmanas.demoapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;

    // No-argument constructor required by Hibernate
    // Hibernate needs to create instances of your `Todo` entity when querying the database (e.g., when fetching data, updating entities, etc.).
    //  For this, Hibernate relies on a default constructor (a constructor with no parameters) to instantiate the entity before it populates its fields.
    public Todo() {
    }

    // Constructor
    public Todo(Long id, String title, boolean completed) {
        this.id = id;
        this.title =  title;
        this.completed = completed;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
